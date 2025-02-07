import { HttpClient } from '@angular/common/http';
import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-user-edit',
  templateUrl: './manage-user-edit.component.html',
  styleUrls: ['./manage-user-edit.component.css']
})
export class ManageUserEditComponent extends BaseComponent implements OnInit {
  userName: any;

  constructor(inj: Injector, public _http: HttpClient) {
    super(inj);
    this.callStatusApi();
    this.getCountrList()
    this.getNationalityList()
    this.getRoles()
    this.getListofDistrict()

    this.userEditData.country = "IND"

  }

  public submitted: boolean = false;
  public userEditData: any = {};

  isNew: any = false
  colorTheme = 'theme-blue';
  dateOfJoinValue = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
  }

  dateOfBirthValue = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
  }

  // Gender list
  public genderList = [
    {
      "name": "Female",
      "code": "F"
    },
    {
      "name": "Male",
      "code": "M"
    }

  ]

  ipAddress: any;
  ngOnInit(): void {
    this.userEditData.state = "TamilNadu"
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['username']);

      if (params['username'] === 'New') {
        this.isNew = true;
      } else {
        this.userName = params['username'];
        this.isNew = false
        this.getViewId(this.userName)
      }
    })
    this._http.get<{ ip: string }>('https://jsonip.com')
      .subscribe(data => {
        this.ipAddress = data
      })
  }

  /****************************************************************************
     @PURPOSE      : To submit user details
     @PARAMETERS   : form,formdata
     @RETURN       : NA
    ****************************************************************************/
  onUser(form, userEditData) {

    userEditData.createdBy = "ADMIN";
    userEditData.active = "Y"
    userEditData.state = "TN"
    if (userEditData.emailId != undefined) {
      userEditData.emailId = userEditData.emailId?.toLowerCase()
    }
    console.log(userEditData);
    if (this.isNew) {
      if (form.valid) {

        const sendData = new FormData();
        let data = JSON.stringify(userEditData)
        sendData.append('UserRegisterDetailsDTO', data)

        this.commonService.callApi('authenticate/registeruserinternal', sendData, 'post', true, false, 'REG').then(res => {
          let successData: any = res;
          if (successData.body.apiStatusCode == "SUCCESS") {
            this.router.navigate(["/main/manage-user"]);
            this.toastr.successToastr("User Register Successfully", 'Success')
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }
    } else {
      this.userEditData.updatedBy = this.getToken('username');
      this.userEditData.isReviewed = "Y"
      this.commonService.callApi("authenticate/updateuser", userEditData, 'put', false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.router.navigate(["/main/manage-user"]);
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

  /****************************************************************************
    @PURPOSE      : to get Announcement info based on announcementId
    @PARAMETERS   : announcementId
    @RETURN       : NA
  ****************************************************************************/
  changeRolesList: any = []
  getViewId(username) {
    this.commonService.callApi('authenticate/' + username, '', 'get', false, false, 'REG').then(success => {
      this.userEditData = success;
      this.userEditData.state = "Tamil Nadu"
      console.log(this.userEditData);

      // if (this.getToken('role') == 'SUUSR') {
      //   this.rolesList = this.rolesList.filter(x => x.code == 'ADMIN' || x.code == 'TRUSTM' || x.code == 'REVIEW' || x.code == 'APPRV')

      // } else if (this.getToken('role') == 'ADMIN') {
      //   this.rolesList = this.rolesList.filter(x => x.code == 'TRUSTV' || x.code == 'TRUSTM')
      // }
      this.changeRolesList = this.rolesList
      setTimeout(() => {
        if (this.getToken('role') != 'ADMIN') {
          if (this.userEditData.role == 'REVIEW') {
            this.changeRolesList = this.rolesList.filter(x => x.code == 'ADMIN' || x.code == 'TRUSTM' || x.code == 'APPRV')
          } else if (this.userEditData.role == 'ADMIN') {
            this.changeRolesList = this.rolesList.filter(x => x.code == 'REVIEW' || x.code == 'TRUSTM' || x.code == 'APPRV')
          }
          else if (this.userEditData.role == 'TRUSTM') {
            this.changeRolesList = this.rolesList.filter(x => x.code == 'REVIEW' || x.code == 'APPRV' || x.code == 'ADMIN')
          }
          else if (this.userEditData.role == 'APPRV') {
            this.changeRolesList = this.rolesList.filter(x => x.code == 'REVIEW' || x.code == 'TRUSTM' || x.code == 'ADMIN')
          }
        } else {
          this.changeRolesList = this.rolesList.filter(x => x.code != 'ADMIN' && x.code != 'REVIEW' && x.code != 'APPRV' && x.code != 'BENIF' && x.code != 'SUUSR' && x.code != 'SUADM' && x.code != this.userEditData.role)

        }
      }, 500)
      this.setToken('changeRequestRole', this.userEditData.role)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /****************************************************************************/
  selectChange(event) {
    this.userEditData.role = this.rolesList.filter(event => event.code)
    console.log(this.rolesList);



  }

}

