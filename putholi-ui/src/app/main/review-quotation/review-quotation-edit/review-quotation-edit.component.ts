import { Component, Injector, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-review-quotation-edit',
  templateUrl: './review-quotation-edit.component.html',
  styleUrls: ['./review-quotation-edit.component.css']
})
export class ReviewQuotationEditComponent extends BaseComponent implements OnInit {

  @ViewChild('staticTabs', { static: true }) staticTabs: TabsetComponent;


  requirementId: any
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.getListofsports()
    this.getRoles()
    this.getSportsDesc()
    this.getListofInfrastructure()
    this.callStatusApi();
    this.activatedRoute.params.subscribe((params) => {
      console.log(params);

      let id = params['id']
      this.requirementId = params['id2']
      this.schoolListById(id)
      this.getSchoolImagesInfo(id)
      this.getStatusList()
      this.getRequireConfig(this.requirementId)
      this.getPreImagesInfo(this.requirementId)
      this.getquotationImagesInfo(this.requirementId)


    });
  }

  ngOnInit(): void {
  }
  isSchool: boolean = false;
  isReq: boolean = false;
  isPreImg: boolean = false;
  isQuot: boolean = false;
  isUnselect: boolean = false;


  /**************************************************************************
          @PURPOSE      : Search requirement Info details
          @PARAMETERS   : pageno. pagesize. requirement ID
          @RETURN       : RequirementInfo
   ****************************************************************************/
  public requirementInfoById: any
  quotationInfoList: any = []
  unSelectInfo: any = []
  assetName: any
  getRequireConfig(id) {
    this.commonService.callApi('requirement/req/description/' + id, '', 'get', false, false, 'REG').then(success => {
      this.requirementInfoById = success;
      //get the quotation List in admin screen
      let result = this.statusList.filter(o1 => this.requirementInfoById.quotationInfo.some(o2 => o1.code === o2.quotateStatus))
      this.requirementInfoById.quotationInfo.forEach(function (checkbox) {
        result.forEach(e => {
          if (e.code == checkbox.quotateStatus)
            checkbox.description = e.description
        });
      })

      let reqQuotation: any = this.requirementInfoById.quotationInfo

      reqQuotation.forEach(e => {
        let days = e.warranty.match(/[0-9]+/)[0]
        let months = e.warranty.match(/[a-zA-Z]+/)[0]

        let monDesc: any
        if (months == 'D') {
          monDesc = 'Days'
        } else if (months == 'Y') {
          monDesc = 'Years'
        } else {
          monDesc = months
        }
        e.warranty = days + monDesc
      });

      this.quotationInfoList = reqQuotation
      //get unselected quotation details 
      this.unSelectInfo = this.quotationInfoList.filter(x => x.quotateStatus == "ADMQUO");

      if (this.getToken('role') == 'ADMIN') {
        this.approvalHistoryByReqIdAndType(this.requirementId)
      } else if (this.getToken('role') == 'REVIEW') {
        this.quotationInfoList = this.requirementInfoById.quotationInfo.filter(item => item.quotateStatus == "REVQUO");
        this.uploadQuotateId = this.quotationInfoList[0].quotationId
        this.totalAmount = this.quotationInfoList[0].totalAmount

        //get the quotation List in reviewer screen
        this.quotationInfoByQuotateId(this.uploadQuotateId)
      } else if (this.getToken('role') == 'APPRV') {
        this.quotationInfoList = this.requirementInfoById.quotationInfo.filter(item => item.quotateStatus == "APRQUO");
        this.uploadQuotateId = this.quotationInfoList[0].quotationId
        this.totalAmount = this.quotationInfoList[0].totalAmount
        this.quotationInfoByQuotateId(this.uploadQuotateId)
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*****************************************************************************************/

  /**************************************************************************
           @PURPOSE      : get List of pre-images by requirement id
           @PARAMETERS   : requirement ID, upload for
           @RETURN       : UploadQuotationDTO
    ****************************************************************************/
  preImages: any = []
  getPreImagesInfo(id) {
    this.commonService.callApi('attachments/' + id + '/PI', '', 'get', false, false, 'REG').then(success => {
      this.preImages = success
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/

  /**************************************************************************
          @PURPOSE      : get List of pre-images by requirement id
          @PARAMETERS   : requirement ID, upload for
          @RETURN       : UploadQuotationDTO
   ****************************************************************************/
  QuotationImages: any = []
  getquotationImagesInfo(id) {
    this.commonService.callApi('attachments/' + id + '/QI', '', 'get', false, false, 'REG').then(success => {
      this.QuotationImages = success
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /**********************************************************************************/
  options: any = {
    backdrop: 'static',
    keyboard: false
  };

  quotateModelRef: BsModalRef
  clickPreImages(template, type, id) {
    console.log(id);
    this.isSchool = false;
    this.isReq = false;
    this.isPreImg = true;
    this.isQuot = false;
    this.isUnselect = false;
    if (type == 'PI') {
      this.preImagesByAttachmentId(id);
    } else if (type == 'QI') {
      this.isSchool = false;
      this.isReq = false;
      this.isPreImg = false;
      this.isQuot = true;
      this.isUnselect = false;
      this.quotationImagesByQuotationId(id)
    } else if (type == 'TI') {
      this.isUnselect = true;
      this.isSchool = false;
      this.isReq = false;
      this.isPreImg = false;
      this.isQuot = false;
      this.preImagesByAttachmentId(id);
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
       @PURPOSE      : get quotation images by quotate id
       @PARAMETERS   : quotate ID
       @RETURN       : AttachmentDTO
****************************************************************************/
  quotateAttachment: any
  quotationImagesByQuotationId(id) {
    this.commonService.callApi('attachments/quotate/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.quotateAttachment = successData
      // Determine the file type based on the file extension
      const filename = this.quotateAttachment.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + this.quotateAttachment.fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + this.quotateAttachment.fileData;
      }

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/




  onClickNext(type) {
    if (type == 'SI') {
      this.staticTabs.tabs[1].active = true
    } else if (type == 'RI') {
      this.staticTabs.tabs[2].active = true
    } else if (type == 'PI') {
      this.staticTabs.tabs[3].active = true
    } else if (type == 'QI') {
    }
  }

  onClickPrevoius(type) {
    if (type == 'RI') {
      this.staticTabs.tabs[0].active = true
    } else if (type == 'PI') {
      this.staticTabs.tabs[1].active = true
    } else if (type == 'QI') {
      this.staticTabs.tabs[2].active = true
    }

  }

  selectStatus = false
  totalAmount: any = 0
  uploadQuotateId: any
  selectQuotate(id, amount, event) {
    this.uploadQuotateId = id
    this.totalAmount = amount
    if (!event.target.checked) {
      this.totalAmount = 0
      this.selectStatus = false
    } else {
      this.selectStatus = true
    }
  }


  /****************************************************************************
     @PURPOSE      :To Approve / Reject quotation Details
     @PARAMETERS   : Formdata
     @RETURN       : NA
   ****************************************************************************/
  isClickedOnce: boolean = false;
  quotationData: any = {}

  onSubmitForm(form, quotationData) {
    if (form.valid) {
      if (!this.isClickedOnce) {
        this.isClickedOnce = true
        if (this.getToken('role') == 'ADMIN' && !this.selectStatus) {
          this.toastr.errorToastr("Please Choose the quotation", 'Oops!')
        } else {
          quotationData.type = "Quotation Approval"
          quotationData.quotationId = this.uploadQuotateId
          quotationData.requirementId = this.requirementId
          quotationData.schoolInfoId = this.schoolInfo.schoolInfoId

          if (this.getToken('role') == 'ADMIN') {
            quotationData.actionBy = this.getToken('username');
            quotationData.role = 'ADMIN'
          } else if (this.getToken('role') == 'REVIEW') {
            quotationData.actionBy = this.getToken('username');
            quotationData.role = 'REVIEW'
          } else if (this.getToken('role') == 'APPRV') {
            quotationData.actionBy = this.getToken('username');
            quotationData.role = 'APPRV'
            quotationData.quotationAmount = this.totalAmount
          }
          this.commonService.callApi('quotation/approvalhistory', quotationData, 'post', false, false, 'REG').then(success => {
            let successData: any = success
            console.log(successData);
            if (successData.body.apiStatusCode === "SUCCESS") {
              this.router.navigate(['/main/review-quotate'])
              if (quotationData.status == 'APR') {
                this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
              } else {
                this.toastr.warningToastr("Quotation Details Rejected Successfully", "Rejected")
              }
            } else {
              this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
            }

          }).catch(e => {
            this.toastr.errorToastr(e.message, 'Error!')
          })
        }
      }
    }
    setTimeout(() => {
      this.isClickedOnce = false
    }, 1000)
  }
  /*****************************************************************************/


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
    this.isQuot = false;
    this.isUnselect = false;
    this.schoolImagesByAttachmentId(id)
    this.quotateModelRef = this.modalService.show(schoolImages, this.options)
  }

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
    this.getRequirementImagesInfo(id)
    this.quotateModelRef = this.modalService.show(template, this.options)
    this.isSchool = false;
    this.isReq = true;
    this.isPreImg = false;
    this.isQuot = false;
    this.isUnselect = false;
  }


  /**************************************************************************
             @PURPOSE      : get List of requirement-images by school id
             @PARAMETERS   : requirement ID, upload for
             @RETURN       : AttachmentsDTO
  ****************************************************************************/
  getRequirementImagesInfo(id) {
    this.commonService.callApi('attachments/' + id + '/PI', '', 'get', false, false, 'REG').then(success => {
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
  unSelectedModalRef: BsModalRef;
  viewUnselectQuotation(template) {
    this.unselectQuotationImages();
    this.unSelectedModalRef = this.modalService.show(template, this.options)
  }

  quotationInfoDetails: any = []
  unselectQuotationList: any = []
  unselectQuotationImages() {
    this.commonService.callApi('attachments/' + this.requirementId + '/QI', '', 'get', false, false, 'REG').then(success => {
      let images: any = success
      this.unselectQuotationList = images.filter(x => x.quotationId != this.quotationInfoList[0].quotationId);

      this.unselectQuotationList.forEach(e => {
        this.unSelectInfo.forEach(y => {
          if (e.quotationId == y.quotationId) {
            e.companyName = y.companyName
            e.totalAmount = y.totalAmount
          }
        });

      });




    }).catch(e => {
      this.toastr.successToastr(e.message, "Oops!")
    })
  }


  /**************************************************************************
        @PURPOSE      : get quotation info by quotate id
        @PARAMETERS   : quotate ID
        @RETURN       : UploadQuotationDTO
  ****************************************************************************/
  quotateData: any = {}
  approvalHistDtlsDTOs: any = []
  quotationInfoByQuotateId(id) {
    this.commonService.callApi('quotation/approval/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.approvalHistDtlsDTOs = successData.schoolApprovalHistoryDetails

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
    });
  }

  /******************************************************************************/
  approvalHistory: any = []
  approvalHistoryByReqIdAndType(id) {
    this.commonService.callApi('quotation/approval/' + id + '/Quotation Approval', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success

      this.approvalHistory = successData
      let type = this.rolesList.filter(o1 => this.approvalHistory.some(o2 => o1.code === o2.role))
      console.log(type);

      this.approvalHistory.forEach(function (checkbox) {
        type.forEach(e => {
          if (e.code == checkbox.role)
            checkbox.role = e.description
        });
      })

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
}
