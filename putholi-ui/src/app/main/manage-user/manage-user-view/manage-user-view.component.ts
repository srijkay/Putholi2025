import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-user-view',
  templateUrl: './manage-user-view.component.html',
  styleUrls: ['./manage-user-view.component.css']
})
export class ManageUserViewComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    this.getListofsports()
  }
  public type: any
  public submitted: boolean = false;
  public manageViewData: any = {}
  public userEditData: any = {}
  public username: any
  ngOnInit(): void {
    this.getRoles()
    this.getCountrList()
    this.getListofDistrict()
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['username'], params['type']);
      this.username = params['username']
      this.type = params['type']
      if (this.type == "APR") {
        this.getView(this.username, "Approval")
      } else {
        this.getView(this.username, "Deletion")
      }
    })
  }


  public status: any;
  setradio(event) {
    this.status = event
    console.log(this.status);
  }



  /****************************************************************************
   @PURPOSE      : to submit Approvals for beneficiary data
   @PARAMETERS   : Form data
   @RETURN       : NA
 ****************************************************************************/
  onUserView(form, manageViewData) {
    console.log("form.valid", manageViewData);

    manageViewData.status = this.status;
    manageViewData.username = this.username;

    manageViewData.actionBy = this.getToken('username');
    manageViewData.role = this.getToken('role')

    let urlName: any
    if (this.type == 'APR' || this.type == 'REJ') {
      urlName = "userapproval"
      manageViewData.type = "Approval"
    } else {
      urlName = "userapproval/deletion"
      manageViewData.type = "Deletion"
    }

    if (this.type == "APR") {
      if (this.status == 'REJ') {
        manageViewData.changeRequestRole = this.userEditData.role.code
      } else {
        manageViewData.changeRequestRole = this.userEditData.changeRequestRole.code
      }
    }

    this.commonService.callApi(urlName, manageViewData, 'post', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode === "SUCCESS") {
        this.router.navigate(["/main/manage-user/user-list"]);
        if (this.status == 'REJ') {
          this.toastr.warningToastr("User Account Rejected Successfully", "Rejected")
        } else if (this.status == 'CAN') {
          this.toastr.warningToastr("Deletion Account cancelled Successfully", "Deleted")
        }
        else if (this.status == 'DEL') {
          this.toastr.successToastr("User Account deleted Successfully", "Deleted")
        }
        else {
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        }
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })

  }
  /**********************************************************************/


  /****************************************************************************
    @PURPOSE      : to get manage user info based on username
    @PARAMETERS   : username
    @RETURN       : ManageUserDTO
  ****************************************************************************/
  public approvalHistDtlsDTOs: any = [];
  country: any = {}
  getView(username, type) {
    this.commonService.callApi('userapproval/' + username + '/' + type, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.userEditData = successData.userRegisterDetails
      this.approvalHistDtlsDTOs = successData.approvalHistDtlsDTOs;
      setTimeout(() => {
        let type = this.rolesList.filter(o1 => this.approvalHistDtlsDTOs.some(o2 => o1.code === o2.role))
        console.log(type);

        this.approvalHistDtlsDTOs.forEach(function (checkbox) {
          type.forEach(e => {
            if (e.code == checkbox.role)
              checkbox.role = e.description
          });
        })
      }, 150)


      this.userEditData.country = this.countryList.find(x => x.code == this.userEditData.country)

      this.userEditData.district = this.DistrictList.find(x => x.code == this.userEditData.district)

      this.userEditData.changeRequestRole = this.rolesList.find(x => x.code == this.userEditData.changeRequestRole)
      console.log(this.userEditData.referredBy);


      if (this.userEditData.referredBy == null) {
        let role = this.rolesList.find(x => x.code == this.userEditData.role)
        this.userEditData.role = role.description
      } else {
        this.userEditData.role = "Referred Volunteer"
      }


      console.log(this.userEditData.role);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/

}
