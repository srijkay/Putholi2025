import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BsModalRef, ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-invoive-payment-view',
  templateUrl: './invoive-payment-view.component.html',
  styleUrls: ['./invoive-payment-view.component.css']
})
export class InvoivePaymentViewComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.callStatusApi();
    this.getBankName()
    this.getCategoryList()
  }
  expensesId: any
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.expensesId = params['id']
      this.viewUploadedImages(this.expensesId)
      this.onExpensesView(this.expensesId)
    })
  }


  /********************************************************************
   @PURPOSE      : open popup for images
   @PARAMETERS   : template id
   @RETURN       : NA
 *******************************************************************/
  imageModalRef: BsModalRef
  onInvoiceImage(images, id) {
    this.imageModalRef = this.modalService.show(images, this.options)
  }

  closeImageModal() {
    this.imageModalRef.hide()
  }

  options: any = {
    backdrop: 'static',
    keyboard: false
  };
  /************************************************************ */

  //Call this method in the image source, it will sanitize it.
  base64Image: any;
  base64PDF: any;
  transform(): SafeResourceUrl {
    if (this.base64Image) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
    } else if (this.base64PDF) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64PDF);
    }
  }
  /****************************************************************************
   @PURPOSE      : To View Uploaded Images details
   @PARAMETERS   : expensesId
   @RETURN       : NA
 ****************************************************************************/
  invoiceExpenseImages: any = {}
  uploadImage: any
  viewUploadedImages(id) {
    this.commonService.callApi('attachments/expensesid/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.invoiceExpenseImages = successData
      this.uploadImage = [
        { name: this.invoiceExpenseImages.fileName, size: this.invoiceExpenseImages.fileSize }
      ]

      // Determine the file type based on the file extension
      const filename = this.invoiceExpenseImages.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + this.invoiceExpenseImages.fileData;;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + this.invoiceExpenseImages.fileData;;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  /****************************************************************************/
  isClickedOnce: boolean = false
  public expensesDetailData: any = {}

  onSubmitExpenses(form, expensesDetailData) {
    if (form.valid) {
      if (!this.isClickedOnce) {
        this.isClickedOnce = true
        expensesDetailData.expensesId = this.expensesId;
        expensesDetailData.type = "Expenses Approval"
        expensesDetailData.role = this.getToken('role')
        expensesDetailData.actionBy = this.getToken('username');

        this.commonService.callApi('expenses/approval', expensesDetailData, 'post', false, false, 'REG').then(success => {
          let successData: any = success
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/invoice-payment"]);
            if (expensesDetailData.status == 'APR') {
              setTimeout(() => {
                this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
              }, 1000)
            } else {
              setTimeout(() => {
                this.toastr.warningToastr("Expense Details Rejected Successfully", "Rejected")
              }, 1000)
            }
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }

    }
  }

  /****************************************************************************
    @PURPOSE      : to get expense details based on expensesId
    @PARAMETERS   : expensesId
    @RETURN       : NA
  ****************************************************************************/
  public expenseList: any = {}
  public approvalHistDtlsDTOs: any = []
  onExpensesView(expensesId) {
    setTimeout(() => {
      this.commonService.callApi('expenses/approval/' + expensesId + '/Expenses Approval', '', 'get', false, false, 'REG').then(success => {
        let data: any = success;
        console.log(data);

        this.expenseList = data.expensesDetails
        console.log(this.expenseList);
        this.expenseList.category = this.categoryDetails.find(x => x.code == this.expenseList.category)
        this.expenseList.bankName = this.bankList.find(x => x.code == this.expenseList.bankName)

        this.approvalHistDtlsDTOs = data.schoolApprovalHistoryDetails;
        console.log(this.approvalHistDtlsDTOs);

      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }, 500)
  }
  /*****************************************************************************************/



  /**************************************************************************
 @PURPOSE      : To Submit the upload payment form
 @PARAMETERS   : form, data
 @RETURN       : NA
****************************************************************************/
  uploadPaymentData: any
  successmodelRef: any
  invoiceResponse: any
  submitUploadPayForm(form, uploadPaymentData, template) {
    const sendData = new FormData();

    if (this.uploadImage[0].type) {
      sendData.append('quotation', this.uploadImage[0])

      this.commonService.callApi("expenses/paymentdetails", sendData, 'post', true, false, 'REG').then(success => {
        let successData: any = success;
        this.invoiceResponse = successData.body;
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.uploadPayRef.hide()
          this.successmodelRef = this.modalService.show(template, this.options)

          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }
  }
  /****************************************************************************/

  /**************************************************************************
  @PURPOSE      : To open the upload payment popup
  @PARAMETERS   : paymentTemplate
  @RETURN       : NA
 ****************************************************************************/
  uploadPayRef: any
  uploadPaymentInfo(paymentTemplate) {
    this.uploadPayRef = paymentTemplate;
    this.uploadImage = []
    paymentTemplate.show();

  }
  /****************************************************************************/
  colorTheme = 'theme-blue';
  utrDateValidConfig = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    showWeekNumbers: false,
    adaptivePosition: true,
    maxDate: new Date(),
  }

  statusList = [
    {
      code: 'SUCC',
      name: "Success"
    },
    {
      code: 'FAIL',
      name: "Failure"
    }
  ]

  closeModel() {
    this.successmodelRef.hide()
    this.router.navigate(['/main/invoice-payment/invoice-payment-list'])
  }
}