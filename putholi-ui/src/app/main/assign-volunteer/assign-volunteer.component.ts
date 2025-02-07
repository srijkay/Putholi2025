import { Component, Injector, OnInit } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-assign-volunteer',
  templateUrl: './assign-volunteer.component.html',
  styleUrls: ['./assign-volunteer.component.css']
})
export class AssignVolunteerComponent extends BaseComponent implements OnInit {

  data: any = {
    pageNumber: 1,
    pageSize: 10,
  }

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    setTimeout(() => {
      this.getListofDistrict()
      this.getListofsports()
      this.getSportsDesc()
      this.getRoles()
    }, 500)
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id']
      this.schoolListById(id);
      this.onSchoolView(id)
      // this.getVolunteerDetails(this.data)
    });
  }

  ngOnInit(): void {
    setTimeout(() => {

      console.log(this.requirementList);

    }, 1000)
  }


  //capture the dropdownValue
  public assignVolunteerValue: any
  selectChange(event) {
    this.volunteerDetails = {}
    this.assignVolunteerValue = event.userName
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
    this.changeVolunteerValue = event.userName
  }

  // update volunteer and hide Modal
  changeVolunteer() {
    this.assignVolunteerValue = this.changeVolunteerValue
    this.template.hide()
  }

  districtName: any
  chooseDistrict(e) {
    console.log(e);
    this.assignVolunteerData.volunteername = null
    this.volunteerDetails = {}
    this.assignVolunteerValue = ''

    this.getVolunteerDetails(e.code)

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

  assignVolunteerData: any = {}
  onSubmit(form, assignVolunteerData) {
    if (form.valid) {

      this.commonService.callApi("schoolinfo/updatestatus/" + this.schoolInfo.schoolInfoId + "/ASGVOL/" + this.assignVolunteerValue, '', 'get', false, false, 'REG').then(success => {
        let successData: any = success
        
          this.updateConsolidate();
          this.router.navigate(["/main/pending-workflow"]);
          this.toastr.successToastr("Volunteer has been assigned successfully", 'Success')
       
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
    this.consolidateData.consolidateId = this.consolidateData.consolidateId,
      this.consolidateData.status = "ASGVOL",
      this.consolidateData.schoolDetailsDTO = {
        "schoolInfoId": this.schoolInfo.schoolInfoId
      },
      console.log(this.consolidateData.schoolDetailsDTO);

    this.consolidateData.lastModifiedBy = this.getToken('username')
    this.commonService.callApi("consolidate", this.consolidateData, 'put', false, false, 'REG').then(success => {
      let successData: any = success;
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })

  }
  /******************************************************************************************************/

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

  /****************************************************************************
    @PURPOSE      : to get school info based on schoolId
    @PARAMETERS   : schoolId
    @RETURN       : NA
  ****************************************************************************/
  public successData: any = {}
  public addressInfo: any = {}
  public contactsInfo: any = {}
  public approvalHistDtlsDTOs: any = []
  onSchoolView(schoolId) {
    setTimeout(() => {
      this.commonService.callApi('schoolinfo/approval/' + schoolId + '/Volunteer Approval', '', 'get', false, false, 'REG').then(success => {
        let data: any = success;
        this.successData = data.schoolInfo
        this.approvalHistDtlsDTOs = data.schoolApprovalHistoryDetails;

        this.approvalHistDtlsDTOs = this.approvalHistDtlsDTOs.filter(x => x.consolidateId == this.schoolInfo.consolidateRefInfo.consolidateId)
        let type = this.rolesList.filter(o1 => this.approvalHistDtlsDTOs.some(o2 => o1.code === o2.role))


        this.approvalHistDtlsDTOs.forEach(function (checkbox) {
          type.forEach(e => {
            if (e.code == checkbox.role)
              checkbox.role = e.description
          });
        })
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }, 200)
  }
  /*****************************************************************************************/


}