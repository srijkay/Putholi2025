import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BsModalRef, ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-reassign-volunteer',
  templateUrl: './reassign-volunteer.component.html',
  styleUrls: ['./reassign-volunteer.component.css']
})
export class ReassignVolunteerComponent extends BaseComponent implements OnInit {

  public modalRef: BsModalRef;
  public submitted: boolean = false;
  public changeVolunteerData: any = {};
  public selectedValue: any;

  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.getListofDistrict()
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id']
      this.schoolListById(id);
      this.schoolImagesBySchoolId(id);
      this.getListofDistrict()
    });
  }

  ngOnInit(): void {
  }

  options: any = {
    backdrop: 'static',
    keyboard: false
  };



  /****************************************************************************
     @PURPOSE      : open popup for showing the images
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ****************************************************************************/
  onSchoolPic(picture, id) {
    this.schoolImagesByAttachmentId(id)
    this.modalRef = this.modalService.show(picture)
  }
  /*********************************************************************************/

  /**************************************************************************
        @PURPOSE      : get school images by school id
        @PARAMETERS   : school ID
        @RETURN       : School Images
  ****************************************************************************/
  schoolList: any = []
  public loading: boolean = true;
  schoolImagesBySchoolId(id) {
    this.commonService.callApi('attachments/school/' + id + '/SI', '', 'get', false, false, 'REG').then(success => {
      this.schoolList = success
      this.loading = false
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /***********************************************************************/
  base64Image: any
  transform() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
  }

  /**************************************************************************
       @PURPOSE      : get pre-images by attachment id
       @PARAMETERS   : attachment ID
       @RETURN       : pre-images
  ****************************************************************************/
  schoolImagesByAttachmentId(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.base64Image = 'data:image/png;base64,' + successData.fileData;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /***********************************************************************/

  /********************************************************************
    @PURPOSE      : open popup for images
    @PARAMETERS   : template id
    @RETURN       : NA
  *******************************************************************/

  imageModalRef: BsModalRef
  onRequirePic(reqpicture, id) {
    this.reqModalRef.hide()
    this.imageModalRef = this.modalService.show(reqpicture, this.options)
  }



  closeImageModal() {
    this.imageModalRef.hide()
    this.reqModalRef.show()
  }

  /********************************************************************/

  /*********************************************************************
         @PURPOSE      : open popup for view requirements
         @PARAMETERS   : template id
         @RETURN       : NA
   *********************************************************************/

  reqModalRef: ModalDirective
  reqModelTemplate: any
  onView(reqView, requirementId) {
    this.requirementList.requirementId = requirementId
    this.viewUploadedImages(requirementId)
    this.getRequirementInfoById(requirementId);
    this.reqModelTemplate = reqView
    this.reqModalRef = reqView
    reqView.show()
  }
  /*********************************************************************/

  /****************************************************************************
    @PURPOSE      : To View Uploaded Images details
    @PARAMETERS   : requirementId,UploadFor
    @RETURN       : NA
  ****************************************************************************/
  reqImages: any = {}
  viewUploadedImages(id) {
    this.commonService.callApi('attachments/' + id + '/RI', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.reqImages = successData[0];
      this.base64Image = 'data:image/png;base64,' + this.reqImages.fileData;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  /****************************************************************************/

  selectChange(event) {
    this.selectedValue = event.userName;
    this.getUserDetails(event.userName)
  }

  // open modal for change volunteer
  template: any
  reAssign(template: ModalDirective) {
    this.template = template
    template.show()
  }

  // carpture the dropdown value in modal
  changeVolunteerValue: any
  onChange(event) {
    this.changeVolunteerValue = event.name

  }

  // update volunteer and hide Modal
  ReassignVolunteer() {
    this.selectedValue = this.changeVolunteerValue
    this.template.hide()
  }



  districtName: any
  chooseDistrict(e) {
    this.getVolunteerDetails(e.code)
  }



  /**************************************************************************
       @PURPOSE      : Get the user details based on name
       @PARAMETERS   : Status
       @RETURN       : NA
****************************************************************************/
  public volunteerDetails: any
  getUserDetails(name) {
    this.commonService.callApi("userapproval/" + name + '/approval', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.volunteerDetails = successData.userRegisterDetails
      this.volunteerDetails.district = this.DistrictList.find(x => x.code == this.volunteerDetails.district)

    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })

  }
  /**************************************************************************/

  data: any = {
    pageNumber: 1,
    pageSize: 10,
  }
  /**************************************************************************
     @PURPOSE      : Get Volunteer Info details 
     @PARAMETERS   : pageno, page size, role
     @RETURN       : NA
  ****************************************************************************/
  VolunteerList: any = [];
  getVolunteerDetails(data) {
    this.commonService.callApi("usermgmt/" + data, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success

      this.VolunteerList = successData
      if (!this.VolunteerList.length) {
        this.toastr.warningToastr("There is no volunteer is available for this district, Please select from other district", "Warning")
      }

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /**************************************************************************/

  /**************************************************************************
      @PURPOSE      : Assign Volunteer Info details to School
      @PARAMETERS   : FormData
      @RETURN       : NA
  ****************************************************************************/
  onChangeVolunteer(form, changeVolunteerData) {
    if (form.valid) {
      this.commonService.callApi("schoolinfo/updatestatus/" + this.schoolInfo.schoolInfoId + "/ASGVOL/" + this.selectedValue, '', 'get', false, false, 'REG').then(success => {
        this.updateConsolidate();
        this.router.navigate(["/main/change-volunteer/change-volunteer-list"]);
        this.toastr.successToastr("Volunteer has been re-assigned successfully", 'Success')
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }
  }
  /*****************************************************************************/

  /**************************************************************************
       @PURPOSE      : Update Status In Manage school
       @PARAMETERS   : Status
       @RETURN       : NA
  ****************************************************************************/
  consolidateData: any = {}
  updateConsolidate() {
    this.consolidateData = this.schoolInfo.consolidateRefInfo;
    // 
    // let requiredEditData = consolidate.requirementInfo

    delete this.consolidateData.requirementInfo
    this.consolidateData.consolidateId = this.consolidateData.consolidateId,
      this.consolidateData.status = "ASGVOL",
      this.consolidateData.schoolDetailsDTO = {
        "schoolInfoId": this.schoolInfo.schoolInfoId
      }
    this.consolidateData.lastModifiedBy = this.getToken('username')
    this.commonService.callApi("consolidate", this.consolidateData, 'put', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
        // this.getRequireConfig(successData.body.id)
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })

  }
  /******************************************************************************************************/
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
    this.commonService.callApi('requirement/all/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      successData.forEach(e => {
        this.onReqEdit(e)
      });

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }

  /**************************************************************************/
  /************************************************************************
  @PURPOSE      : to add / edit requirement details
  @PARAMETERS   : form,formdata
  @RETURN       : NA
 ***********************************************************************/

  onReqEdit(requiredEditData) {
    let consolidate = this.schoolInfo.consolidateRefInfo;

    requiredEditData.consolidateRef = {
      "consolidateId": consolidate.consolidateId
    }
    requiredEditData.lastModifiedBy = this.getToken('username')
    requiredEditData.reqStatus = "ASGVOL"

    this.commonService.callApi("requirement", requiredEditData, 'put', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })


  }

  /************************************************************************/

  /**************************************************************************
        @PURPOSE      : get requirements info by requirement id
        @PARAMETERS   : requirement ID
        @RETURN       : RequirementsDTO
  ****************************************************************************/
  requirementInfoById: any = {}
  assetName: any
  getRequirementInfoById(id) {
    this.commonService.callApi('requirement/req/description/' + id, '', 'get', false, false, 'REG').then(success => {
      this.requirementInfoById = success
    })
  }
  /***************************************************************************************/

  /****************************************************************************
     @PURPOSE      : To get List of district list 
     @PARAMETERS   : NA
     @RETURN       : Master code type active list
    ****************************************************************************/
  public DistrictList = [];
  getListofDistrict() {
    this.commonService.callApi('mastercode/active/DIST', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.DistrictList = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

}
