import { Component, Injector, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-review-invoice-details',
  templateUrl: './review-invoice-details.component.html',
  styleUrls: ['./review-invoice-details.component.css']
})
export class ReviewInvoiceDetailsComponent extends BaseComponent implements OnInit {
  @ViewChild('staticTabs', { static: true }) staticTabs: TabsetComponent;


  requirementId: any
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.callStatusApi();
    this.getListofsports()
    this.getSportsDesc()
    this.getStatusList()
    this.getAccountDetails()
    this.getCardDetails()
    this.getRoles()
    this.getBccountDetails()
    this.getPaymentDetails()
    this.getListofDistrict()
    this.getListofInfrastructure()

    this.activatedRoute.params.subscribe((params) => {
      console.log(params);

      let id = params['id']
      this.requirementId = params['id2']
      this.schoolListById(id)
      this.getSchoolImagesInfo(id)


    });
  }
  isPreImg: boolean = false;
  isPostImg: boolean = false;
  isSchool: boolean = false;
  isReq: boolean = false;
  assetName: any

  ngOnInit(): void {

    this.getRequireConfig(this.requirementId)
    this.getPreImagesInfo(this.requirementId, 'PO')
    this.getInvoiceByRequirementId(this.requirementId)

  }

  /**************************************************************************
   @PURPOSE      : Search requirement Info details
   @PARAMETERS   : pageno. pagesize. requirement ID
   @RETURN       : RequirementInfo
   ****************************************************************************/
  public requirementInfoById: any
  quotationInfoList: any = []
  invoiceTotalAmount = 0
  getRequireConfig(id) {
    this.commonService.callApi('requirement/req/description/' + id, '', 'get', false, false, 'REG').then(success => {
      this.requirementInfoById = success;
      console.log(this.requirementInfoById);

      this.quotationInfoList = this.requirementInfoById.quotationInfo.filter(x => x.quotateStatus == 'QUOARV')
      console.log(this.quotationInfoList);

      let approvedAmount = this.quotationInfoList.find(x => x.quotateStatus == 'QUOARV')
      console.log(approvedAmount);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*****************************************************************************************/
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
  /**************************************************************************
           @PURPOSE      : get List of pre-images by requirement id
           @PARAMETERS   : requirement ID, upload for
           @RETURN       : UploadQuotationDTO
    ****************************************************************************/
  preImages: any = []
  getPreImagesInfo(id, uploadFor) {
    this.commonService.callApi('attachments/' + id + '/' + uploadFor, '', 'get', false, false, 'REG').then(success => {
      this.preImages = success
      console.log(this.preImages);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/
  isInvoiceImg: boolean = false
  options: any = {
    backdrop: 'static',
    keyboard: false
  };
  quotateModelRef: BsModalRef
  clickPreImages(template, type, id) {
    console.log(id);
    if (type == 'PI' || type == 'PO') {
      this.isPostImg = true;
      this.isPreImg = false;
      this.isReq = false;
      this.isSchool = false;
      this.isInvoiceImg = false;

      this.preImagesByAttachmentId(id);
    } else if (type == 'II') {
      this.isPostImg = false;
      this.isPreImg = false;
      this.isReq = false;
      this.isSchool = false;
      this.isInvoiceImg = true;
      this.quotationImagesByInvoiceId(id);
    }

    this.quotateModelRef = this.modalService.show(template, this.options)
  }
  /**************************************************************************
        @PURPOSE      : get pre-images by attachment id
        @PARAMETERS   : attachment ID
        @RETURN       : pre-images
 ****************************************************************************/
  preImagesByAttachmentId(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success

      // Determine the file type based on the file extension
      const filename = successData.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + successData.fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + successData.fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /***********************************************************************/

  /**************************************************************************
       @PURPOSE      : get invoice by attachment id
       @PARAMETERS   : attachment ID
       @RETURN       : pre-images
****************************************************************************/
  invoiceListImages: any
  getInvoiceImagesInfo(id, uploadFor) {
    this.commonService.callApi('attachments/' + id + '/' + uploadFor, '', 'get', false, false, 'REG').then(success => {
      this.invoiceListImages = success
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*******************************************************************************************/
  /****************************************************************************
     @PURPOSE      : get invoice details by requirementId
     @PARAMETERS   : requirementId
     @RETURN       : InvoiceDetailsDTO
  ****************************************************************************/
  invoiceList: any = []
  successData: any
  getInvoiceByRequirementId(id) {
    this.commonService.callApi('invoice/info/' + id, '', 'get', false, false, 'REG').then(success => {
      this.successData = success;
      if (this.getToken('role') == 'ADMIN') {
        this.invoiceList = this.successData.filter(x => x.invoiceStatus == 'ADMINV' || x.invoiceStatus == 'ADMREC' || x.invoiceStatus == 'PAYINI')
      } else if (this.getToken('role') == 'REVIEW') {
        this.invoiceList = this.successData.filter(x => x.invoiceStatus == 'INVREV')
      }
      else if (this.getToken('role') == 'APPRV') {
        this.invoiceList = this.successData.filter(x => x.invoiceStatus == 'INVAPR')
      }
      //convert the invoice status from code to description
      setTimeout(() => {
        let invoiceResult = this.statusList.filter(o1 => this.invoiceList.some(o2 => o1.code === o2.invoiceStatus))
        this.invoiceList.forEach(function (checkbox) {
          invoiceResult.forEach(e => {
            if (e.code == checkbox.invoiceStatus)
              checkbox.description = e.description
          });
        })
      }, 100)

      let paidInvoiceAmount: any = this.successData.filter(x => x.invoiceStatus != 'ADMINV' && x.invoiceStatus != 'INVREV' && x.invoiceStatus != 'INVAPR' && x.invoiceStatus != 'INVREV' && x.invoiceStatus != 'REJINV')
      let sum = 0
      for (let i = 0; i < paidInvoiceAmount.length; i++) {
        sum += paidInvoiceAmount[i].invoiceAmount
      }
      this.invoiceTotalAmount = sum
      console.log(this.invoiceTotalAmount, sum, paidInvoiceAmount);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /************************************************************************************/
  invoiceModalRef: any
  addInvoice(template, id) {
    this.invoiceModalRef = template;
    this.getInvoiceById(id)
    this.quotationImagesByInvoiceId(id);
    this.onSchoolView(id);
    template.show();
    this.ApprovalInvoiceData = {}
    this.isClickedOnce = false
  }

  /****************************************************************************
    @PURPOSE      : get invoice details by invoiceId
    @PARAMETERS   : invoiceId
    @RETURN       : InvoiceDetailsDTO
  ****************************************************************************/
  invoiceData: any = {}
  getInvoiceById(id) {
    this.commonService.callApi('invoice/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.invoiceData = successData

      this.invoiceData.bankName = this.bankDetails.find(x => x.code == this.invoiceData.bankName)
      this.invoiceData.workStatus = this.PaymentDetails.find(x => x.code == this.invoiceData.workStatus)
      this.invoiceData.accountType = this.AccountDetails.find(x => x.code == this.invoiceData.accountType)
      this.invoiceData.paymentMode = this.cardDetails.find(x => x.code == this.invoiceData.paymentMode)

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*******************************************************************************************/

  /**************************************************************************
      @PURPOSE      : get quotation images by quotate id
      @PARAMETERS   : quotate ID
      @RETURN       : AttachmentDTO
  ****************************************************************************/
  invoiceImages: any = {}
  quotationImagesByInvoiceId(id) {
    this.commonService.callApi('attachments/invoice/' + id + '/II', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.invoiceImages = successData

      // Determine the file type based on the file extension
      const filename = this.invoiceImages[0].fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + this.invoiceImages[0].fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + this.invoiceImages[0].fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/
  reqStatus: any

  onClickNext(type) {
    if (type == 'SI') {
      this.staticTabs.tabs[1].active = true
    } else if (type == 'RI') {
      this.staticTabs.tabs[2].active = true
    } else if (type == 'PO') {
      this.staticTabs.tabs[3].active = true
    } else if (type == 'II') {
      // if (this.requirementInfoById.reqStatus == "ADMINV") {
      //   this.reqStatus = 'PROCES'

      // } else {
      //   this.reqStatus = 'CMPLTD'

      // }
      this.router.navigate(['/main/review-invoice'])
    }
  }

  onClickPrevoius(type) {
    if (type == 'RI') {
      this.staticTabs.tabs[0].active = true
    } else if (type == 'PO') {
      this.staticTabs.tabs[1].active = true
    } else if (type == 'II') {
      this.staticTabs.tabs[2].active = true
    }

  }





  /****************************************************************************
     @PURPOSE      :To Approve / Reject invoice Details
     @PARAMETERS   : Formdata
     @RETURN       : NA
   ****************************************************************************/
  isClickedOnce: boolean = false;
  ApprovalInvoiceData: any = {}
  onSubmitInvoiceForm(form, ApprovalInvoiceData) {
    if (form.valid) {
      if (!this.isClickedOnce) {
        this.isClickedOnce = true

        ApprovalInvoiceData.invoiceId = this.invoiceData.invoiceId
        ApprovalInvoiceData.type = 'Invoice Approval'
        if (this.getToken('role') == 'ADMIN') {
          ApprovalInvoiceData.actionBy = this.getToken('username');
          ApprovalInvoiceData.role = 'ADMIN'
        } else if (this.getToken('role') == 'REVIEW') {
          ApprovalInvoiceData.actionBy = this.getToken('username');
          ApprovalInvoiceData.role = 'REVIEW'
        } else if (this.getToken('role') == 'APPRV') {
          ApprovalInvoiceData.actionBy = this.getToken('username');
          ApprovalInvoiceData.role = 'APPRV'
        }
        console.log(ApprovalInvoiceData);

        this.commonService.callApi('invoice/approvalhistory', ApprovalInvoiceData, 'post', false, false, 'REG').then(success => {
          let successData: any = success
          console.log(successData);
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.invoiceModalRef.hide();
            this.getInvoiceByRequirementId(this.requirementId)
            if (ApprovalInvoiceData.status == 'APR') {
              this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            } else {
              this.toastr.warningToastr("Invoice Details Rejected Successfully", "Rejected")
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
  /**************************************************************************/

  /****************************************************************************
    @PURPOSE      : Get approval history by invoice Id
    @PARAMETERS   : invoiceId
    @RETURN       : NA
  ****************************************************************************/
  public addressInfo: any = {}
  public contactsInfo: any = {}
  public approvalHistDtlsDTOs: any = []
  onSchoolView(invoiceId) {
    this.commonService.callApi('invoice/approval/' + invoiceId + '/Invoice Approval', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.approvalHistDtlsDTOs = successData.schoolApprovalHistoryDetails;
      let type = this.rolesList.filter(o1 => this.approvalHistDtlsDTOs.some(o2 => o1.code === o2.role))
      console.log(type);

      this.approvalHistDtlsDTOs.forEach(function (checkbox) {
        type.forEach(e => {
          if (e.code == checkbox.role)
            checkbox.role = e.description
        });
      })
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/


  // updateRequirementStatus() {
  //   console.log(this.successData);


  //   let InvoiceDetails = this.successData.filter(item => item.invoiceStatus == "INVREV" || item.invoiceStatus == "PENADM" || item.invoiceStatus == "INVAPR" || item.invoiceStatus == "RECADD");
  //   this.requirementInfoById.consolidateRef = {
  //     "consolidateId": this.schoolInfo.consolidateRefInfo.consolidateId
  //   }
  //   this.requirementInfoById.reqStatus = this.reqStatus
  //   if (InvoiceDetails.length == 0) {
  //     this.commonService.callApi("requirement", this.requirementInfoById, 'post', false, false, 'REG').then(success => {
  //       let successData: any = success;
  //       if (successData.body.apiStatusCode == "SUCCESS") {
  //         this.router.navigate(['/main/review-invoice'])
  //       } else {
  //         this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
  //       }
  //     }).catch((e) => {
  //       this.toastr.errorToastr(e.message, 'Oops!')
  //     })
  //   } else {
  //     this.router.navigate(['/main/review-invoice'])
  //   }
  // }
  /****************************************************************/



  /**************************************************************************
             @PURPOSE      : get List of school-images by school id
             @PARAMETERS   : school ID, upload for
             @RETURN       : AttachmentsDTO
      ****************************************************************************/
  imagesList: any = []
  getSchoolImagesInfo(id) {
    this.commonService.callApi('attachments/school/' + id + '/SI', '', 'get', false, false, 'REG').then(success => {
      this.imagesList = success
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }

  /******************************************************************************/

  onSchoolTemplate(schoolImages, id) {
    this.isSchool = true;
    this.isReq = false;
    this.isPreImg = false;
    this.isPostImg = false;
    this.isInvoiceImg = false;

    this.schoolImagesByAttachmentId(id)
    this.quotateModelRef = this.modalService.show(schoolImages, this.options)
  }

  /**************************************************************************
        @PURPOSE      : get pre-images by attachment id
        @PARAMETERS   : attachment ID
        @RETURN       : pre-images
  ****************************************************************************/
  schoolImagesByAttachmentId(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success

      // Determine the file type based on the file extension
      const filename = successData.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + successData.fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + successData.fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /***********************************************************************/
  clickReqImages(template, id) {
    this.isReq = true;
    this.isSchool = false;
    this.isPreImg = false;
    this.isPostImg = false;
    this.isInvoiceImg = false;
    this.getRequirementImagesInfo(id)
    this.quotateModelRef = this.modalService.show(template, this.options)
  }


  /**************************************************************************
             @PURPOSE      : get List of requirement-images by school id
             @PARAMETERS   : requirement ID, upload for
             @RETURN       : AttachmentsDTO
  ****************************************************************************/
  getRequirementImagesInfo(id) {
    this.commonService.callApi('attachments/' + id + '/RI', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success

      // Determine the file type based on the file extension
      const filename = successData[0].fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + successData[0].fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + successData[0].fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/

  receipt: boolean = false
  invoiceId: any
  uploadReceipt(invoiceid) {
    this.receipt = true
    this.invoiceId = invoiceid
  }

  getInvoice(event) {
    this.receipt = event
    this.getInvoiceByRequirementId(this.requirementId);
  }

  /**************************************************************************
  @PURPOSE      : To Submit the upload payment form
  @PARAMETERS   : form, data
  @RETURN       : NA
 ****************************************************************************/
  uploadPaymentData: any = {}
  uploadImage: any
  successmodelRef: any
  invoiceResponse: any
  submitUploadPayForm(form, uploadPaymentData, template) {
    const sendData = new FormData();

    if (this.uploadImage[0].type) {
      sendData.append('quotation', this.uploadImage[0])

      this.commonService.callApi("invoicepaymentdetails", sendData, 'post', true, false, 'REG').then(success => {
        let successData: any = success;
        this.invoiceResponse = successData.body;
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.uploadPayRef.hide()
          this.invoiceModalRef.hide()

          this.successmodelRef = this.modalService.show(template, this.options)



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

  clickCancel() {
    this.invoiceModalRef.hide()
  }

  expenseStatusList = [
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
    this.getInvoiceByRequirementId(this.requirementId);
    this.successmodelRef.hide()
  }
}
