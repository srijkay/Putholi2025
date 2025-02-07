import { Component, Injector, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { NgPopupsService } from 'ng-popups';
import { BsModalRef, ModalDirective } from 'ngx-bootstrap/modal';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { BaseComponent } from 'src/app/common/commonComponent';

declare var $: any
@Component({
  selector: 'app-manage-requirement-edit',
  templateUrl: './manage-requirement-edit.component.html',
  styleUrls: ['./manage-requirement-edit.component.css']
})
export class ManageRequirementEditComponent extends BaseComponent implements OnInit {
  @ViewChild('staticTabs', { static: true }) staticTabs: TabsetComponent;

  public modalRef: BsModalRef;
  public submitted: boolean = false;
  public requiredEditData: any = {};
  public consolidateData: any = {};
  public requiredViewData: any = {};

  consolidateId: any;

  isNew: any = false
  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }

  assetName: any;
  comments: any;

  options: any = {
    backdrop: 'static',
    keyboard: false
  };

  public assetTypeList = [
    {
      "name": "Infrastructure",
      "code": "INF"
    },
    {
      "name": "Others",
      "code": "OTH"
    },
    {
      "name": "Sports",
      "code": "SPR"
    }

  ]

  public timePeriodList = [
    {
      "name": "6 Months",
      "code": "6M"
    },
    {
      "name": "1 Year",
      "code": "1Y"
    }
  ]


  isClickedOnce = false
  ngPopupService: any;

  constructor(inj: Injector, private sanitizer: DomSanitizer, private ngPopups: NgPopupsService) {
    super(inj);
    this.getSchoolTypeList()
    this.getListofsports()
    this.getSportsDesc()
    this.getrequirementType()
    this.getStatusList()
    this.getListofDistrict()
    this.getListofInfrastructure()
    this.getRoles()
    this.callStatusApi()

    this.activatedRoute.params.subscribe((params) => {
      this.requirementId = ['id']
      if (params['id'] === 'New') {
        if (this.getToken('consolidateId') == null && this.getToken('consolidateId') == undefined) {
          this.isNew = true;
          this.disableHidden = false
        } else {
          this.consolidateId = this.getToken('consolidateId');
          this.isNew = false
          this.disableHidden = false
          this.getConsolidateDetails(this.consolidateId);
        }
      } else {
        this.consolidateId = params['id'];
        this.isNew = false
        this.getConsolidateDetails(this.consolidateId);
        this.showQuotationImages(this.consolidateId)
      }
    })


  }

  requirementId: any

  disableHidden: boolean = true
  ngOnInit(): void {
    this.getschoolList(this.getToken('schoolId'))
    if (this.getToken('role') == 'BENIF') {
      this.staticTabs.tabs[1].disabled = true;
    } else {
      this.getRequireConfig(this.consolidateId ? this.consolidateId : this.getToken('consolidateId'));
    }
    setTimeout(() => {
      if (this.getToken('role') == 'BENIF' && (this.consolidateData.status == 'ADDREQ' || this.consolidateData.status == undefined) || this.consolidateData.status == 'REJ' || this.consolidateData.status == 'DEOREJ') {
        this.disableHidden = false
      } else {
        this.disableHidden = true
      }
    }, 1000)


  }


  clickNext() {
    if (this.getToken('role') == 'BENIF') {
      this.staticTabs.tabs[1].disabled = false;
      this.staticTabs.tabs[0].disabled = true;
    }
    this.staticTabs.tabs[1].active = true
    // this.getRequireConfig(this.consolidateId ? this.consolidateId : this.getToken('consolidateId'));
  }

  onPrevious() {
    if (this.getToken('role') == 'BENIF') {
      this.staticTabs.tabs[0].disabled = false;
      this.staticTabs.tabs[1].disabled = true;
    }
    this.staticTabs.tabs[0].active = true
    // this.isNew = false;
  }

  selectTab(e) {
  }

  callRequirementsApi: boolean = true
  disableButton = false;
  disableHome = false;
  goToHome() {
    this.isClickedOnce = false
    this.callRequirementsApi = false

    if (!this.disableButton) {
      this.disableButton = true

      // this.getConsolidateDetails(this.consolidateId ? this.consolidateId : this.getToken('consolidateId'))
      if (this.consolidateData.status != 'CMPLTD') {
        this.onConsolidate(true, this.consolidateData)
      }
      if (this.requirementList.length == this.consolidateData.noOfRequirements) {
        if (this.getToken('role') == 'BENIF' && (this.consolidateData.status == 'ADDREQ' || this.consolidateData.status == undefined) ||
          this.consolidateData.status == 'REJ' || this.consolidateData.status == 'DEOREJ' || this.consolidateData.status == 'REVREQ' ||
          this.consolidateData.status == 'ADMREQ' || this.consolidateData.status == 'APRREQ') {
          setTimeout(() => {
            this.updateConsolidateStatus(this.consolidateId ? this.consolidateId : this.getToken('consolidateId'))
          }, 500)
        }
        this.router.navigate(['/main/manage-requirement'])
      } else {
        this.toastr.warningToastr("Please check the number of requirements added", 'Error')
      }
    }
    setTimeout(() => {
      this.disableButton = false;
    }, 3000)
  }

  goBackToList() {
    if (!this.disableHome) {
      this.disableHome = true

      if (this.getToken('role') == 'BENIF') {
        if (this.requirementList.length == this.consolidateData.noOfRequirements) {
          this.router.navigate(['/main/manage-requirement'])
        } else {
          this.toastr.warningToastr("Please check the number of requirements added", 'Error')
        }
      } else {
        this.router.navigate(['/main/manage-requirement'])
      }
    } setTimeout(() => {
      this.disableHome = false;
    }, 3000)
  }





  updateConsolidateStatus(id) {
    this.commonService.callApi("requirement/checkingStatus/" + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  /**************************************************************************
         @PURPOSE      : Get School Info details 
         @PARAMETERS   : school name/ school id
         @RETURN       : NA
  ****************************************************************************/
  schoolInfo: any = {}
  getschoolList(id) {
    this.commonService.callApi('schoolinfo/descriptions/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.schoolInfo = successData;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /**************************************************************************/
  clickNextTab(consolidateData) {
    this.consolidateData = consolidateData
    this.staticTabs.tabs[1].disabled = false;
    this.staticTabs.tabs[1].active = true;
  }

  /*********************************************************************
    @PURPOSE      : To submit Consolidate details
    @PARAMETERS   : form,formdata
    @RETURN       : NA
  **********************************************************************/
  schoolId: any

  onConsolidate(form, consolidateData) {

    // if(!this.requirementList && this.consolidateData?.)

    if (!this.isClickedOnce) {
      this.isClickedOnce = true

      this.requiredEditData = consolidateData
      consolidateData = this.consolidateData;
      console.log(consolidateData);


      if (this.requirementList.length) {
        this.isNew = false
        // this.getRequireConfig(this.getToken('consolidateId'))
      }
      console.log(form);

      if (form.valid || form) {
        let apiMethod: any
        if (this.isNew) {

          this.consolidateData = {

            "createdBy": this.getToken('username'),
            "noOfRequirements": consolidateData.noOfRequirements,
            "status": "ADDREQ",
            "totalAmount": consolidateData.totalAmount,
            "schoolDetailsDTO": {
              "schoolInfoId": this.schoolInfo.schoolInfoId
            }
          }
          apiMethod = "post"

        } else {
          let status = "ADDREQ"
          if (this.requirementList.length == consolidateData.noOfRequirements) {
            status = "ADMREQ"
          }

          apiMethod = "put"
          this.consolidateData = {

            "consolidateId": consolidateData.consolidateId,
            "createdBy": consolidateData.createdBy,
            "noOfRequirements": consolidateData.noOfRequirements,
            "status": this.consolidateData.status == 'ADDREQ' || this.consolidateData.status == 'REJ' || this.consolidateData.status == 'DEOREJ' ? status : this.consolidateData.status,
            "totalAmount": consolidateData.totalAmount,
            "schoolDetailsDTO": {
              "schoolInfoId": this.schoolInfo.schoolInfoId
            },
            "lastModifiedBy": this.getToken('username')

          }
          console.log(status);
        }
        this.consolidateData.active = "Y"
        this.commonService.callApi("consolidate", this.consolidateData, apiMethod, false, false, 'REG').then(success => {
          let successData: any = success;

          if (successData.body.apiStatusCode == "SUCCESS") {
            this.setToken("consolidateId", successData.body.id)
            this.isNew = false
            this.getConsolidateDetails(successData.body.id)

            if (this.callRequirementsApi) {
              this.onReqEdit(this.requiredEditData)
            }

          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }

        }).catch((e) => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })

      }
    }

    if (this.getToken('role') == 'BENIF' && (this.consolidateData.status == 'ADDREQ' || this.consolidateData.status == undefined) || this.consolidateData.status == 'REJ' || this.consolidateData.status == 'DEOREJ') {
      this.disableHidden = false
    } else {
      this.disableHidden = true
    }
  }

  /*********************************************************************/



  /**********************************************************************
  @PURPOSE      : To get consolidate INFO
  @PARAMETERS   : id
  @RETURN       : consolidateDTO
  ************************************************************************/
  getConsolidateDetails(id) {
    this.commonService.callApi('consolidate/consolidate/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.consolidateData = successData;
      this.consolidateId = this.consolidateData.consolidateId
      this.getRequireConfig(id)
    }).catch(e => {
      this.ngxLoader.stop()
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }
  /***********************************************************************/

  /********************************************************************
          @PURPOSE      : open popup for add/ edit requirements
          @PARAMETERS   : template id
          @RETURN       : NA
  *********************************************************************/
  isRequirement: boolean = false
  reqEditId: any

  onEdit(reqEdit, type, requirementId) {
    // this.uploadImage = {} 
    this.isClickedOnce = false
    this.submitted = false
    if (type == 'NEW') {
      this.requiredEditData = {}
      this.isRequirement = true
      this.uploadImage = []
    } else if (type == 'EDIT') {
      this.isRequirement = false
      this.reqEditId = requirementId;
      this.getRequirementById(requirementId)
      this.viewUploadedImages(requirementId)
    }
    this.callRequirementsApi = true
    this.modalRef = this.modalService.show(reqEdit, this.options)
  }
  /*******************************************************************/

  /*********************************************************************
         @PURPOSE      : open popup for view requirements
         @PARAMETERS   : template id
         @RETURN       : NA
   *********************************************************************/

  reqModalRef: ModalDirective
  reqModelTemplate: any
  onView(reqView, requirementId) {
    this.reqEditId = requirementId
    this.isRequirement = true
    this.getRequirementDescById(requirementId)
    this.viewUploadedImages(requirementId)
    this.requiredViewData = {}
    this.reqModelTemplate = reqView
    this.reqModalRef = reqView
    reqView.show()
    this.isClickedOnce = false

  }
  /*********************************************************************/

  /************************************************************************
    @PURPOSE      : to add / edit requirement details
    @PARAMETERS   : form,formdata
    @RETURN       : NA
   ***********************************************************************/

  onReqEdit(requiredEditData) {

    if (requiredEditData.requirementType == 'NE') {
      requiredEditData.timePeriod = ''
    }
    let id = this.consolidateId ? this.consolidateId : this.getToken('consolidateId');

    if (this.isRequirement) {
      this.requiredEditData = {
        assetName: requiredEditData.assetName,
        assetType: requiredEditData.assetType,
        comments: requiredEditData.comments,
        quantity: requiredEditData.quantity,
        timePeriod: requiredEditData.timePeriod,
        reqStatus: "ADMREQ",
        active: "Y",
        requirementType: requiredEditData.requirementType,
        createdBy: this.getToken('username'),
        "consolidateRef": {
          "consolidateId": id
        },
      }

      this.commonService.callApi("requirement", this.requiredEditData, 'post', false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode == "SUCCESS") {
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          this.modalRef.hide();
          this.uploadReqImages(successData.body.id)
          console.log(id, "id");

          this.getRequireConfig(id)
          // this.updateConsolidateStatus()
        } else {
          this.isClickedOnce = false
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch((e) => {
        this.isClickedOnce = false
        this.toastr.errorToastr(e.message, 'Oops!')
      })

    } else {


      this.requiredEditData = {
        "requirementId": this.reqEditId,
        assetName: requiredEditData.assetName,
        assetType: requiredEditData.assetType,
        comments: requiredEditData.comments,
        quantity: requiredEditData.quantity,
        timePeriod: requiredEditData.timePeriod,
        createdBy: this.requiredEditData.createdBy,
        reqStatus: "ADMREQ",
        active: "Y",
        requirementType: requiredEditData.requirementType,
        lastModifiedBy: this.getToken('username'),
        "consolidateRef": {
          "consolidateId": id
        },
      }
      this.commonService.callApi("requirement", this.requiredEditData, 'put', false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode == "SUCCESS") {
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          this.modalRef.hide();
          this.uploadReqImages(successData.body.id)
          console.log(id, "id");

          this.getRequireConfig(id)
        } else {
          this.isClickedOnce = false
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch((e) => {
        this.isClickedOnce = false
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }

  }

  /************************************************************************/

  /*************************************************************************
       @PURPOSE      : to get requirement approval info based on requirementId
       @PARAMETERS   : requirementId
       @RETURN       : NA
     *************************************************************************/
  public approvalHistDtlsDTOs: any = []
  successData: any = {}
  getRequirementDescById(requirementId) {
    this.commonService.callApi('requirement/reqapproval/' + requirementId + "/Requirement Approval", '', 'get', false, false, 'REG').then(success => {
      let data: any = success;
      this.successData = data.requirementInfo;
      this.approvalHistDtlsDTOs = data.schoolApprovalHistoryDetails;


    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /************************************************************************/

  /*************************************************************************
       @PURPOSE      : to get requirement info based on requirementId
       @PARAMETERS   : requirementId
       @RETURN       : NA
     *************************************************************************/
  getRequirementById(requirementId) {
    this.commonService.callApi('requirement/req/' + requirementId, '', 'get', false, false, 'REG').then(success => {
      let data: any = success;
      this.requiredEditData = data;
      console.log(this.requiredEditData);

      this.getAssetName(this.requiredEditData.assetType)

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /************************************************************************/

  /**************************************************************************
          @PURPOSE      : Search requirement Info details
          @PARAMETERS   : pageno. pagesize
          @RETURN       : RequirementInfo
   ****************************************************************************/
  api_url: any
  public requirementList: any = [];
  reqstatus: any
  sumofEstimations: any
  amount: any
  getRequireConfig(id) {
    if (this.getToken('role') == 'ADMIN') {
      this.reqstatus = "ADMREQ"
    } else if (this.getToken('role') == 'APPRV') {
      this.reqstatus = "APRREQ"
    } else if (this.getToken('role') == 'REVIEW') {
      this.reqstatus = "REVREQ"
    }
    if (this.getToken('role') != 'BENIF') {
      this.api_url = 'requirement/all/' + id + '/' + this.reqstatus;
    } else {
      this.api_url = 'requirement/all/' + id
    }
    this.commonService.callApi(this.api_url, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.requirementList = successData
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }

  /**************************************************************************/

  /**********************************************************************
        @PURPOSE      : Delete requirements Details based on Id
        @PARAMETERS   : id
        @RETURN       : RequirementInfoDTO
   ***********************************************************************/

  deleteMessage: any
  onDelete(id) {

    this.deleteMessage = "Reasons for Deletion"
    this.removeToken('schoolInfoId')
    this.ngPopups.prompt(this.deleteMessage)


      .subscribe(res => {
        let successData: any = res
        if (successData.result) {
          if (successData.value) {
            console.log(successData);
            let requiredViewData: any = {}
            requiredViewData.requirementId = id;
            requiredViewData.type = "Requirement Deletion"
            requiredViewData.actionBy = this.getToken('username');
            requiredViewData.role = this.getToken('role');
            requiredViewData.status = "APR"
            requiredViewData.remarks = successData.value

            this.commonService.callApi('requirement/reqapproval', requiredViewData, 'post', false, false, 'REG').then(success => {
              let successData: any = success
              if (successData.body.apiStatusCode == "SUCCESS") {
                this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
                this.deletePreImages(id)
                this.getConsolidateDetails(this.consolidateId ? this.consolidateId : this.getToken('consolidateId'))
              } else {
                this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
              }

            }).catch(e => {
              this.toastr.errorToastr(e.message, 'Oops!')
            })
          } else {
            this.toastr.warningToastr("Please enter the reasond for deletion", "Error")
          }
        }
      })
  }
  /********************************************************************/

  /****************************************************************************
     @PURPOSE      : To Approve / Reject requirement details
     @PARAMETERS   : Formdata
     @RETURN       : NA
   ****************************************************************************/
  onReqView(form, requiredViewData) {
    if (!this.isClickedOnce) {
      this.isClickedOnce = true
      requiredViewData.requirementId = this.reqEditId;
      requiredViewData.type = "Requirement Approval"
      requiredViewData.actionBy = this.getToken('username');
      requiredViewData.role = this.getToken('role')

      this.commonService.callApi('requirement/reqapproval', requiredViewData, 'post', false, false, 'REG').then(success => {
        let successData: any = success
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.getRequireConfig(this.consolidateId ? this.consolidateId : this.getToken('consolidateId'));
          if (requiredViewData.status == 'APR') {
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.warningToastr("Requirement Details Rejected Successfully", "Rejected")
          }
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }

      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })

    }
  }
  /********************************************************************************************/

  /****************************************************************************
     @PURPOSE      : To Add / Update uploadimage details
     @PARAMETERS   : Formdata
     @RETURN       : NA
   ****************************************************************************/

  requirementPreImages: any = {}
  uploadImage: any
  fileType: any
  uploadReqImages(id) {
    const sendData = new FormData();
    if (this.isRequirement) {
      this.requirementPreImages.requirementId = id;
      let data = JSON.stringify(this.requirementPreImages);
      sendData.append('QuotationAttachmentsDTO', data);
      sendData.append('fileType ', 'RI')
      sendData.append('quotation', this.uploadImage[0])
      this.commonService.callApi('attachments', sendData, 'post', true, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode == "SUCCESS") {
          this.modalRef.hide()
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    } else {
      if (this.uploadImage[0].type) {
        let requiredEditData: any = {}
        requiredEditData.requirementId = id;
        requiredEditData.attachmentId = this.reqImages.attachmentId;
        let data = JSON.stringify(requiredEditData);
        sendData.append('QuotationAttachmentsDTO', data);
        sendData.append('fileType ', 'RI')
        sendData.append('quotation', this.uploadImage[0])
        this.commonService.callApi('attachments', sendData, 'put', true, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode == "SUCCESS") {
            this.modalRef.hide()

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

  /****************************************************************************
     @PURPOSE      : To View Uploaded Images details
     @PARAMETERS   : requirementId,UploadFor
     @RETURN       : NA
   ****************************************************************************/
  reqImages: any = {}
  base64Image: any;
  base64PDF: any;
  transform(): SafeResourceUrl {
    if (this.base64Image) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
    } else if (this.base64PDF) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64PDF);
    }
  }

  viewUploadedImages(id) {
    this.commonService.callApi('attachments/' + id + '/RI', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.reqImages = successData[0]
      this.uploadImage = [
        { name: this.reqImages.fileName, size: this.reqImages.fileSize }
      ]

      // Determine the file type based on the file extension
      const filename = this.reqImages.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + this.reqImages.fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + this.reqImages.fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  /****************************************************************************/
  /********************************************************************
    @PURPOSE      : open popup for images
    @PARAMETERS   : template id
    @RETURN       : NA
  *******************************************************************/

  imageModalRef: BsModalRef
  onImage(images, id) {
    this.reqModalRef.hide()
    this.imageModalRef = this.modalService.show(images, this.options)
  }

  closeImageModal() {
    this.imageModalRef.hide()
    this.reqModalRef.show()
  }

  /********************************************************************/

  /**************************************************************************
        @PURPOSE      : delete uploaded images by requirementId
        @PARAMETERS   : requirementId
        @RETURN       : NA
  ****************************************************************************/
  deletePreImages(id) {

    this.commonService.callApi('attachments/delAttach/' + id + '/RI', '', 'delete', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }




  /****************************************************************************
  @PURPOSE      : To get List of requirementType
  @PARAMETERS   : NA
  @RETURN       : Nationality List
  ****************************************************************************/
  requirementTypeList: any = []
  getrequirementType() {
    this.commonService.callApi('mastercode/active/REQTY', '', 'get', false, false, 'REG').then(success => {
      this.requirementTypeList = success;
      this.requirementTypeList = this.requirementTypeList.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

  assetNameList: any = []
  selectAssetType(event) {
    this.requiredEditData.assetName = null
    this.commonService.callApi('mastercode/active/' + event, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.assetNameList = successData.masterCodeResultDTOs;

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }




  getAssetName(assetType) {
    this.commonService.callApi('mastercode/active/' + assetType, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.assetNameList = successData.masterCodeResultDTOs;

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }


  /****************************************************************************
       @PURPOSE      :To show the image of DEO response
       @PARAMETERS   : SchoolId
       @RETURN       : AttachmentDTO
  ****************************************************************************/
  isShowDeoResponse: boolean = false
  showQuotationImages(id) {
    console.log(id);

    this.commonService.callApi('attachments/' + id + '/DR', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.length) {
        this.isShowDeoResponse = true
        this.base64Image = 'data:image/png;base64,' + successData[successData.length - 1].fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /**********************************************************************************************/

  /****************************************************************************
   @PURPOSE      : open popup for showing the images
   @PARAMETERS   : form,formdata
   @RETURN       : NA
  ****************************************************************************/
  onPerson(personId) {
    this.showQuotationImages(this.consolidateId)
    this.modalRef = this.modalService.show(personId)
  }
  /*********************************************************************************/
}






