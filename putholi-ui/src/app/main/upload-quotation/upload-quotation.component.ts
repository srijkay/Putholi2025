import { Component, Injector, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BsModalRef, ModalDirective } from 'ngx-bootstrap/modal';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { BaseComponent } from 'src/app/common/commonComponent';
import * as moment from 'moment';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { NgPopupsService } from 'ng-popups';

@Component({
  selector: 'app-upload-quotation',
  templateUrl: './upload-quotation.component.html',
  styleUrls: ['./upload-quotation.component.css']
})
export class UploadQuotationComponent extends BaseComponent implements OnInit {
  @ViewChild('staticTabs', { static: true }) staticTabs: TabsetComponent;

  options: any = {
    backdrop: 'static',
    keyboard: false
  };
  clickPreImagesButton: boolean = false
  clickQuotationButton: boolean = false
  clickPostImagesButton: boolean = false
  clickInvoiceButton: boolean = false
  clickCompletionOfReq: boolean = false

  public isSubmitting: boolean = false;
  dateOfCompl: Partial<BsDatepickerConfig>;
  colorTheme = 'theme-blue';

  requirementId: any
  constructor(inj: Injector, private sanitizer: DomSanitizer, private ngPopups: NgPopupsService) {
    super(inj)
    this.callStatusApi();
    this.getSportsDesc()
    this.getListofsports()

    this.getStatusList()

    setTimeout(() => {

      this.activatedRoute.params.subscribe((params) => {
        let id = params['id']
        this.requirementId = params['id2']
        this.getRequireConfig(this.requirementId)
        this.schoolListById(id)
        this.getSchoolImagesInfo(id)
        this.getPreImagesInfo(this.requirementId, 'PI')
        this.getPostImagesInfo(this.requirementId, 'PO')
      });
    }, 150)
  }
  ngOnInit(): void {
    this.dateOfCompl = Object.assign({}, { containerClass: this.colorTheme, customTodayClass: 'custom-today-class', showWeekNumbers: false, dateInputFormat: 'DD-MM-YYYY', adaptivePosition: true, maxDate: new Date() });

  }


  /**************************************************************************
          @PURPOSE      : Search requirement Info details
          @PARAMETERS   : pageno. pagesize. requirement ID
          @RETURN       : RequirementInfo
   ****************************************************************************/
  showInvoice: boolean = false;
  public requirementInfoById: any
  public quodationWorkOrder: any
  invoiceTotalAmount: any
  public workStatus: any = {}

  quotationCount: any
  tax :any
  getRequireConfig(id) {
    this.commonService.callApi('requirement/req/description/' + id, '', 'get', false, false, 'REG').then(success => {
      this.requirementInfoById = success
      this.quodationWorkOrder = this.requirementInfoById.quotationInfo.find(x => x.quotateStatus != 'ADMQUO')
      // get the quotation info from the requirement api
      this.quotationInfoList = this.requirementInfoById.quotationInfo;
      // get the invoice info from the requirement api
      this.invoiceList = this.requirementInfoById.invoiceDetails
      this.workStatus = this.invoiceList.find(x => x.workStatus == 'FC' && x.invoiceStatus != 'REJINV')


      const notRequiredQuotatusStatus = ['VOLACP', 'REJQUO', 'ADMQUO', 'REVQUO', 'APRQUO']
      this.quotationCount = !notRequiredQuotatusStatus.includes(this.requirementInfoById.reqStatus)

      if (this.quotationCount) {
        this.quotationInfoList = this.requirementInfoById.quotationInfo.filter(x => x.quotateStatus == 'QUOARV');
        this.tax = Math.round((Number(this.quodationWorkOrder.totalAmount) * Number(this.quodationWorkOrder.tax))/  100)
      }

      //convert the invoice status from code to description
      let invoiceResult = this.statusList.filter(o1 => this.invoiceList.some(o2 => o1.code === o2.invoiceStatus))
      setTimeout(() => {
        this.invoiceList.forEach(function (checkbox) {
          invoiceResult.forEach(e => {
            if (e.code == checkbox.invoiceStatus)
              checkbox.description = e.description
          });
        })
      }, 800)


      //convert the quotate status from code to description
      let result = this.statusList.filter(o1 => this.quotationInfoList.some(o2 => o1.code === o2.quotateStatus))
      setTimeout(() => {
        this.quotationInfoList.forEach(function (checkbox) {
          result.forEach(e => {
            if (e.code == checkbox.quotateStatus)
              checkbox.description = e.description
          });
        })
      }, 800)

      this.approvedAmount = this.quotationInfoList.find(x => x.quotateStatus == 'QUOARV')
      this.approvedQuotation = this.approvedAmount
      const notRequiredStatus = ['VOLACP', 'REJQUO', 'ADMQUO', 'REVQUO', 'APRQUO', 'QUOARV', 'REDALL', 'GNRORD'];
      this.showInvoice = !notRequiredStatus.includes(this.requirementInfoById.reqStatus)

      if (this.approvedAmount) {
        let sum = 0
        for (let i = 0; i < this.invoiceList.length; i++) {
          sum += this.invoiceList[i].invoiceAmount
        }
        this.invoiceTotalAmount = this.approvedAmount.totalAmount - sum
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*****************************************************************************************/

  /**************************************************************************
          @PURPOSE      : To view images based on attachment id
          @PARAMETERS   : attachment ID
          @RETURN       : AttachmentDTO
   ****************************************************************************/
  quotateModelRef: BsModalRef
  isPreImg: boolean = false;
  isPostImg: boolean = false;
  clickPreImages(template, type, id, uploadFor) {
    if (type == 'VIEW') {
      this.preImagesByAttachmentId(id);
    }
    if (uploadFor == 'PO') {
      this.isPostImg = true;
      this.isPreImg = false;
      this.isReq = false;
      this.isSchool = false;
    }
    if (uploadFor == 'PI') {
      this.isPreImg = true;
      this.isReq = false;
      this.isSchool = false;
      this.isPostImg = false;
    }
    this.quotateModelRef = this.modalService.show(template, this.options)
  }
  /*********************************************************************************/
  addModelRef: BsModalRef
  isAddImage: boolean = false
  attachmentId: any

  // open pop-up to upload the images
  addPreImages(template, type, id) {
    this.isClickedOnce = false
    if (type == 'EDIT') {
      this.attachmentId = id
      this.isAddImage = false
      this.uploadImage = {}
      this.preImagesByAttachmentId(id);
    } else if (type == 'ADD') {
      this.isAddImage = true
      this.uploadImage = {}
    }
    this.addModelRef = this.modalService.show(template, this.options)
  }

  /**************************************************************************
          @PURPOSE      : ADD/UPDATE pre-images by requirement id
          @PARAMETERS   : requirement ID, image
          @RETURN       : NA
   ****************************************************************************/
  requirementPreImages: any = {}
  isClickedOnce = false
  uploadImage: any
  uploadPreImages(id) {

    if (!this.isClickedOnce && this.uploadImage[0] != undefined) {
      this.isClickedOnce = true

      const sendData = new FormData();
      let api_Type: any;

      if (this.uploadImage[0].type) {
        if (this.staticTabs.tabs[2].active) {
          sendData.append('fileType ', "PI")
        } else {
          sendData.append('fileType ', "PO")
        }
        this.isSubmitting = true

        if (this.isAddImage) {
          this.requirementPreImages.requirementId = this.requirementId;
          let data = JSON.stringify(this.requirementPreImages);
          sendData.append('QuotationAttachmentsDTO', data);
          sendData.append('quotation', this.uploadImage[0])
          api_Type = 'post'
        } else {
          let quotData: any = {}
          quotData.requirementId = this.requirementId;
          quotData.attachmentId = this.attachmentId;
          let data = JSON.stringify(quotData);
          sendData.append('QuotationAttachmentsDTO', data);
          sendData.append('quotation', this.uploadImage[0])
          api_Type = 'put'
        }
        this.commonService.callApi('attachments', sendData, api_Type, true, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode == "SUCCESS") {
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            if (this.staticTabs.tabs[2].active) {
              this.getPreImagesInfo(this.requirementId, "PI");
            } else {
              this.getPostImagesInfo(this.requirementId, "PO");
            }
            this.addModelRef.hide()
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        });
      } else {
        this.addModelRef.hide()
      }
    }
    setTimeout(() => {
      this.isClickedOnce = false
    }, 500)
  }
  /********************************************************************************/

  /**************************************************************************
         @PURPOSE      : get List of pre-images by requirement id
         @PARAMETERS   : requirement ID, upload for
         @RETURN       : UploadQuotationDTO
  ****************************************************************************/
  preImages: any = []
  getPreImagesInfo(id, uploadFor) {
    this.commonService.callApi('attachments/' + id + '/' + uploadFor, '', 'get', false, false, 'REG').then(success => {
      this.preImages = success
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/

  /**************************************************************************
        @PURPOSE      : get List of post-images by requirement id
        @PARAMETERS   : requirement ID, upload for
        @RETURN       : UploadQuotationDTO
 ****************************************************************************/
  postImages: any = []
  getPostImagesInfo(id, uploadFor) {
    this.commonService.callApi('attachments/' + id + '/' + uploadFor, '', 'get', false, false, 'REG').then(success => {
      this.postImages = success
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/

  /**************************************************************************
        @PURPOSE      : get pre-images by attachment id
        @PARAMETERS   : attachment ID
        @RETURN       : pre-images
 ****************************************************************************/
  preImagesByAttachmentId(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      let images: any = successData[0]
      this.uploadImage = [
        { name: successData.fileName, size: successData.fileSize }
      ]

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
        @PURPOSE      : delete pre images by requirement id
        @PARAMETERS   : requirement ID
        @RETURN       : NA
 ****************************************************************************/
  deletePreImages(id, type) {
    this.commonService.callApi('attachments/' + id, '', 'delete', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
        this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        this.getPreImagesInfo(this.requirementId, type);
        this.getPostImagesInfo(this.requirementId, type)
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*********************************************************************************/

  /**************************************************************************
        @PURPOSE      : get List of quotation info by requirement id
        @PARAMETERS   : requirement ID
        @RETURN       : UploadQuotationDTO
 ****************************************************************************/
  quotationInfoList: any = []
  quotationInfoByRequirementId(id) {
    this.commonService.callApi('quotation/info/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      setTimeout(() => {
        let result = this.statusList.filter(o1 => successData.some(o2 => o1.code === o2.quotateStatus))
        successData.forEach(function (checkbox) {
          result.forEach(e => {
            if (e.code == checkbox.quotateStatus)
              checkbox.description = e.description
          });
        })
      }, 800)
      this.quotationInfoList = successData
      if (this.quotationCount) {
        this.quotationInfoList = this.requirementInfoById.quotationInfo.filter(x => x.quotateStatus == 'QUOARV');
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/


  /**************************************************************************
     @PURPOSE      : delete upload Quotation and quotate image by requirement id
     @PARAMETERS   : quotateId
     @RETURN       : UploadQuotationDTO
****************************************************************************/
  deleteQuotate(id) {
    let deleteMsg = "Are you sure want to delete ?"
    this.ngPopups.confirm(deleteMsg)
      .subscribe(res => {
        let successData: any = res
        console.log(successData);

        if (successData) {
          console.log(res);

          this.commonService.callApi('quotation/' + id, '', 'delete', false, false, 'REG').then(success => {
            let successData: any = success;
            if (successData.body.apiStatusCode == "SUCCESS") {
              this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
              this.deleteQuotateImages(id)
              this.quotationInfoByRequirementId(this.requirementId)
            } else {
              this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
            }
          }).catch(e => {
            this.toastr.errorToastr(e.message, 'Oops!')
          });


        }
      })
  }

  /*********************************************************************************/


  /**************************************************************************
     @PURPOSE      : delete upload Quotation and quotate image by requirement id
     @PARAMETERS   : quotateId
     @RETURN       : UploadQuotationDTO
****************************************************************************/
  deleteQuotateImages(id) {
    this.commonService.callApi('attachments/quotationid/' + id, '', 'delete', false, false, 'REG').then(success => {
      let successData: any = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*********************************************************************************/

  isSchool: boolean = false;
  isReq: boolean = false;

  requirementStatus: any
  approvedAmount: any
  onClickNext(type) {
    if (type == 'SI') {
      this.staticTabs.tabs[1].active = true
    } else if (type == 'RI') {
      this.staticTabs.tabs[2].active = true
    } else if (type == 'PI') {

      if (!this.clickPreImagesButton) {
        this.clickPreImagesButton = true
        if (this.preImages.length >= 2) {
          this.staticTabs.tabs[3].active = true
        } else {
          this.toastr.errorToastr("Please add minimum 2 Pre-Images", "Error")
        }
      }
    } else if (type == 'QI') {
      if (!this.clickQuotationButton) {
        this.clickQuotationButton = true
        if (!this.showInvoice) {
          if (this.quotationInfoList.length >= 2 || this.quotationCount) {
            this.requirementStatus = "ADMQUO"
            let data: any = this.quotationInfoList.filter(c => c.quotateStatus == 'REJQUO')
            if (data.length == 0) {
              this.requirementInfoById.reqStatus == 'VOLACP' || this.requirementInfoById.reqStatus == 'ADMQUO' || this.requirementInfoById.reqStatus == 'REJQUO' ? this.updateRequirementStatus() : this.router.navigate(['/main/pending-workflow'])
            } else {
              this.router.navigate(['/main/pending-workflow'])
            }
          } else {
            this.toastr.errorToastr("Please add minimum 2 Quotations", "Error")
          }
        } else {
          this.staticTabs.tabs[4].active = true;
        }
      }
    } else if (type == 'PO') {
      if (!this.clickPostImagesButton) {
        this.clickPostImagesButton = true
        if (this.postImages.length >= 2) {
          this.staticTabs.tabs[5].active = true;
        } else {
          this.toastr.errorToastr("Please add minimum 2 Post-Images", "Error")
        }
      }

    } else if (type == 'II') {
      if (!this.clickInvoiceButton) {
        this.clickInvoiceButton = true

        if (this.invoiceList.length > 0 || Number(this.getToken('pendingAmount')) == 0) {
          if (this.invoiceList.length * 2 == this.postImages.length || this.invoiceList.length * 2 <= this.postImages.length) {
            this.staticTabs.tabs[6].active = true;
          } else {
            if (Number(this.getToken('pendingAmount')) == 0) {
              this.staticTabs.tabs[6].active = true;
            } else {
              this.toastr.errorToastr("Please add minimum " + this.invoiceList.length * 2 + " Post-Images", "Error")
            }
          }
        } else {
          this.toastr.errorToastr("Please add Invoices")
        }
      }
    }

    setTimeout(() => {
      this.clickPreImagesButton = false;
      this.clickQuotationButton = false;
      this.clickPostImagesButton = false;
      this.clickInvoiceButton = false
    }, 2000);
  }


  onClickPrevoius(type) {
    if (type == 'SI') {
      this.staticTabs.tabs[0].active = true
    } else if (type == 'RI') {
      this.staticTabs.tabs[1].active = true
    } else if (type == 'PI') {
      this.staticTabs.tabs[2].active = true
    } else if (type == 'QI') {
      this.staticTabs.tabs[3].active = true;
    } else if (type == 'PO') {
      this.staticTabs.tabs[4].active = true;
    } else if (type == 'CR') {
      this.staticTabs.tabs[5].active = true;
    }
  }
  /*************************************************************************/

  /****************************************************************************
      @PURPOSE      :open modal for quotations
      @PARAMETERS   : template id
      @RETURN       : NA
****************************************************************************/
  modalRef: BsModalRef
  openQuotationImage(template) {
    this.modalRef = this.modalService.show(template, this.options);
  }
  /***********************************************************************************/
  updateRequirementStatus() {
    this.commonService.callApi("requirement/updatestatus/" + this.requirementId + "/" + this.requirementStatus, '', 'get', false, false, 'REG').then(success => {
      this.router.navigate(['/main/pending-workflow'])
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /****************************************************************************************/

  /****************************************************************************
      @PURPOSE      : get invoice details by requirementId
      @PARAMETERS   : requirementId
      @RETURN       : InvoiceDetailsDTO
   ****************************************************************************/
  invoiceList: any = []
  getInvoiceByRequirementId(id) {
    this.commonService.callApi('invoice/info/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;

      setTimeout(() => {
        let result = this.statusList.filter(o1 => successData.some(o2 => o1.code === o2.invoiceStatus))
        successData.forEach(function (checkbox) {
          result.forEach(e => {
            if (e.code == checkbox.invoiceStatus)
              checkbox.description = e.description
          });
        })
      }, 800)
      this.invoiceList = successData
      console.log(this.invoiceList);
      if (this.approvedAmount) {
        let sum = 0
        for (let i = 0; i < this.invoiceList.length; i++) {
          sum += this.invoiceList[i].invoiceAmount
        }
        this.invoiceTotalAmount = this.approvedAmount.totalAmount - sum
      }

      this.workStatus = this.invoiceList.find(x => x.workStatus == 'FC' && x.invoiceStatus != 'REJINV')

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /************************************************************************************/


  /**************************************************************************
     @PURPOSE      : delete  quotate image by quotation id
     @PARAMETERS   : quotationid
     @RETURN       : NA
****************************************************************************/
  deleteInvoiceImages(id) {
    this.commonService.callApi('attachments/invoiceid/' + id, '', 'delete', false, false, 'REG').then(success => {
      let successData: any = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*********************************************************************************/


  /****************************************************************************
       @PURPOSE      : Delete invoice Details by invoiceId
       @PARAMETERS   : invoiceId
       @RETURN       : NA
  ****************************************************************************/
  deleteInvoice(id) {
    this.commonService.callApi('invoice/' + id, '', 'delete', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
        this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        this.deleteInvoiceImages(id)
        this.getInvoiceByRequirementId(this.requirementId)
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /************************************************************************************/




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
    this.schoolImagesByAttachmentId(id)
    this.quotateModelRef = this.modalService.show(schoolImages)
  }

  /**************************************************************************
        @PURPOSE      : get pre-images by attachment id
        @PARAMETERS   : attachment ID
        @RETURN       : pre-images
 ****************************************************************************/
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
    this.isPreImg = false;
    this.getRequirementImagesInfo(id)
    this.quotateModelRef = this.modalService.show(template)
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



  isViewQuotation: boolean = false
  quotationId: any
  viewquotates(id) {
    this.isViewQuotation = true
    this.quotationId = id
  }


  addQuotate: boolean = false
  quotateType: any
  addquotates(type, id) {
    if (this.preImages.length >= 2) {
      this.quotationId = id
      this.addQuotate = true
      this.quotateType = type
    } else {
      this.toastr.errorToastr("Please upload the pre-images", "Error")
    }
  }

  isInvoice: boolean = false
  invoiceType: any
  invoiceId: any
  approvedQuotation: any
  addInvoice(type, id) {

    if (type == 'ADD') {
      if (this.postImages.length >= (this.invoiceList.length * 2) + 2) {
        this.isInvoice = true
        this.invoiceId = id
        this.invoiceType = type
        this.approvedQuotation = JSON.stringify(this.approvedAmount);
      } else {
        this.toastr.errorToastr("Please upload the post-images", "Error")
      }
    } else {
      this.isInvoice = true
      this.invoiceId = id
      this.invoiceType = type
      this.approvedQuotation = JSON.stringify(this.approvedAmount);
    }
  }
  isviewInvoice: boolean = false
  viewInvoice(id) {
    this.isviewInvoice = true
    this.invoiceId = id
  }

  receipt: boolean = false
  uploadReceipt(invoiceid) {
    this.receipt = true
    this.invoiceId = invoiceid
  }

  getInvoice(event) {
    this.receipt = event
    this.getInvoiceByRequirementId(this.requirementId)
  }

  paymentInvoice: boolean = false
  paymentInvoiceAmount: any
  addPayment(invoiceId, amount) {
    console.log(amount);

    this.paymentInvoice = true
    this.invoiceId = invoiceId
    this.paymentInvoiceAmount = amount
    console.log(this.paymentInvoiceAmount);
  }

  closeQuotation(event) {
    this.isViewQuotation = event
    this.addQuotate = event
    this.quotationInfoByRequirementId(this.requirementId)

  }

  closeInvoice(event) {
    console.log(event);

    this.isInvoice = event
    this.isviewInvoice = event
    this.paymentInvoice = event
    this.getInvoiceByRequirementId(this.requirementId)
  }
  /************************************************************************************ */
  public requirementCompletion: any = {}


  public rangeList = [
    {
      name: 'Fully completed',
      id: 'FC'
    }]




  /**************************************************************************
    @PURPOSE      : To View Work Order
    @PARAMETERS   : NA
    @RETURN       : NA
  ****************************************************************************/

  clickWorkOrder(workOrder: ModalDirective) {
    workOrder.show();
    this.fethUser(this.schoolInfo.volunteerName)
    // this.updatedRequiremnt(this.requirementTypeList[0])
  }
  /****************************************************************************/

  /****************************************************************************
   @PURPOSE      : To get userDetails based on username
   @PARAMETERS   : username
   @RETURN       : UserDetailsDTO
****************************************************************************/
  userData: any = {}
  fethUser(username) {
    this.commonService.callApi('authenticate/' + username, '', 'get', false, false, 'REG').then(success => {
      this.userData = success;

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops')
    })
  }
  /**************************************************************************/
  /****************************************************************************
     @PURPOSE      : To submit completion of requirements details
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ****************************************************************************/
  onCompletion(form, requirementCompletion) {
    if (!this.clickCompletionOfReq) {
      this.clickCompletionOfReq = true


      requirementCompletion.requirementId = this.requirementId
      requirementCompletion.beneficiaryName = this.schoolInfo.createdBy
      if (form.valid) {
        if (Number(this.getToken('pendingAmount')) == 0 && requirementCompletion.completionOfPayment == "FC" && this.postImages.length >= 2) {
          this.requirementCompletion.createdBy = this.getToken('username');
          this.commonService.callApi("completionofrequirements", requirementCompletion, 'post', false, false, 'REG').then(success => {
            let successData: any = success;
            if (successData.body.apiStatusCode === "SUCCESS") {
              this.router.navigate(['/main/pending-workflow'])
              this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            } else {
              this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
            }
          }).catch(e => {
            this.toastr.errorToastr(e.message, 'Oops!')
          })
        } else {
          this.toastr.warningToastr("Work is not completed Yet", "Error")
        }
      }
    }
    setTimeout(() => {
      this.clickCompletionOfReq = false
    }, 2000);
  }
}
/****************************************************************************/

