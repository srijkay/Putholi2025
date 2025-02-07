import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-invoive-payment-edit',
  templateUrl: './invoive-payment-edit.component.html',
  styleUrls: ['./invoive-payment-edit.component.css']
})
export class InvoivePaymentEditComponent extends BaseComponent implements OnInit {
  invoicePaymentData: any = {}
  isNew: boolean = false

  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if (id == 'NEW') {
        this.isNew = true
      } else {
        this.isNew = false
        this.getExpensesDetails(id);
        this.viewInvoicePayImages(id)
      }
    })
  }

  colorTheme = 'theme-blue';
  transactionDateValue = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    showWeekNumbers: false,
    adaptivePosition: true,
    maxDate: new Date(),
  }
  ngOnInit(): void {
    this.callStatusApi();
    this.getBankName()
    this.getCategoryList()
    this.getStatusList()
  }

  /****************************************************************************
     @PURPOSE      : to submit invoice expense details
     @PARAMETERS   : form,invoicePaymentData
     @RETURN       : NA
****************************************************************************/
  onSubmit(form, invoicePaymentData) {
    if (this.isNew) {
      if (form.valid) {
        this.invoicePaymentData.createdBy = "Admin";
        this.invoicePaymentData.status = 'EXPREV'
        if (invoicePaymentData.category != 'OTHR') {
          invoicePaymentData.other = null
        }
        this.commonService.callApi("expenses", invoicePaymentData, 'post', false, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.uploadInvoiceExpImages(successData.body.id)
            this.router.navigate(["/main/invoice-payment/invoice-payment-list"]);
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })

      }
    } else {
      this.invoicePaymentData.lastModifiedBy = "Admin";
      this.invoicePaymentData.status = 'EXPREV'
      if (invoicePaymentData.category != 'OTHR') {
        invoicePaymentData.other = null
      }
      this.commonService.callApi("expenses", invoicePaymentData, 'put', false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.uploadInvoiceExpImages(successData.body.id)
          this.router.navigate(["/main/invoice-payment/invoice-payment-list"]);
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })

    }
  }

  /****************************************************************************** */
  /****************************************************************************
     @PURPOSE      : to get expenses info based on expenseId
     @PARAMETERS   : expenseId
     @RETURN       : NA
****************************************************************************/
  getExpensesDetails(id) {
    this.commonService.callApi('expenses/' + id, '', 'get', false, false, 'REG').then(success => {
      this.invoicePaymentData = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/

  /****************************************************************************
    @PURPOSE      : To Add / Update uploadimage details
    @PARAMETERS   : Formdata
    @RETURN       : NA
  ****************************************************************************/

  expensesImage: any = {}
  uploadImage: any
  fileType: any
  uploadInvoiceExpImages(id) {
    const sendData = new FormData();
    if (this.isNew) {
      this.expensesImage.expensesId = id;
      let data = JSON.stringify(this.expensesImage);
      sendData.append('QuotationAttachmentsDTO', data);
      sendData.append('fileType ', 'EI')
      sendData.append('quotation', this.uploadImage[0])
      this.commonService.callApi('attachments', sendData, 'post', true, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode == "SUCCESS") {

        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    } else {
      if (this.uploadImage[0].type) {
        let invoicePaymentDetails: any = {}
        invoicePaymentDetails.expensesId = id;
        invoicePaymentDetails.attachmentId = this.invoiceExpenseImages.attachmentId;
        let data = JSON.stringify(invoicePaymentDetails);
        sendData.append('QuotationAttachmentsDTO', data);
        sendData.append('fileType ', 'EI')
        sendData.append('quotation', this.uploadImage[0])
        this.commonService.callApi('attachments', sendData, 'put', true, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode == "SUCCESS") {
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        });
      }
    }
  }
  /****************************************************************************/
  //Call this method in the image source, it will sanitize it.
  base64Image: any
  transform() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
  }
  /****************************************************************************
   @PURPOSE      : To View Uploaded Images details
   @PARAMETERS   : expensesId
   @RETURN       : NA
 ****************************************************************************/
  invoiceExpenseImages: any = {}
  viewInvoicePayImages(id) {
    this.commonService.callApi('attachments/expensesid/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.invoiceExpenseImages = successData
      this.uploadImage = [
        { name: this.invoiceExpenseImages.fileName, size: this.invoiceExpenseImages.fileSize }
      ]

      this.base64Image = 'data:image/png;base64,' + this.invoiceExpenseImages.fileData;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /****************************************************************************/

}
