import { HttpClient } from '@angular/common/http';
import { Component, Injector, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from '../common/commonComponent';

@Component({
  selector: 'app-volunteer-registration',
  templateUrl: './volunteer-registration.component.html',
  styleUrls: ['./volunteer-registration.component.css']
})
export class VolunteerRegistrationComponent extends BaseComponent implements OnInit {

  routingParams: any
  public submitted: boolean = false;
  public memberData: any = {};
  public uploadProof: any
  constructor(inj: Injector, public _http: HttpClient) {
    super(inj)

    this.routingParams = this.activatedRoute.snapshot.queryParams;

    console.log(this.routingParams);


    if (this.routingParams.email_id) {
      this.getBeneficiaryData(this.routingParams.referred_name)
      this.memberData.emailId = this.routingParams.email_id
      this.setToken('params', JSON.stringify(this.routingParams))
      this.router.navigate(["/volunteer-registration"])

    } else if (this.getToken('params') != null || this.getToken('params') != undefined) {
      let parameters: any = JSON.parse(this.getToken('params'))
      this.getBeneficiaryData(parameters.referred_name)
      this.memberData.emailId = parameters.email_id

    }


    this.getListofDistrict()

  }
  public base64Image = 'data:image/png;base64,'
  ngOnInit(): void {
    this.memberData.country = "IND";
    this.memberData.state = "TamilNadu"
    this.memberData.selectRole = "TRUSTV"
  }

  options: any = {
    backdrop: 'static',
    keyboard: false
  };


  /****************************************************************************
     @PURPOSE      : to submit registration details
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ****************************************************************************/
  suceessmodelRef: BsModalRef;
  isClickedSubmitted: boolean = false
  onMemberForm(form, memberData, template) {
    if (!this.isClickedSubmitted) {
      this.isClickedSubmitted = true
    if (form.valid && memberData.confirmPassword == memberData.secretKey) {
      const sendData = new FormData();
      memberData.active = "N";
      memberData.status = "PENADM";
      memberData.role = "TRUSTV";
      memberData.createdBy = "admin"
      memberData.state = "TN"
      // memberData.referredBy = this.routingParams.referred_name
      if (this.memberData.emailId != null && this.memberData.emailId != "") {
        console.log(this.memberData.emailId);
        this.testEmailValidation(this.memberData.emailId);
      }
      if (memberData.emailId != undefined) {
        memberData.emailId = memberData.emailId?.toLowerCase()
      }

      // if (memberData.userName != undefined) {
      //   memberData.userName = memberData.userName?.toLowerCase()
      // }
      if (this.Valid == true) {
        let data = JSON.stringify(memberData)
        sendData.append('UserRegisterDetailsDTO', data)
        sendData.append('identifyproof', this.uploadProof[0])
        if (!this.check) {
          this.toastr.errorToastr("Please accept terms and conditions", 'Error');
        } else if (!this.isValidCaptcha) {
          this.toastr.errorToastr("Please verify the entered captcha", 'Error')
        } else {
          this.commonService.callApi('authenticate/registeruser', sendData, 'post', true, true, 'LOG').then(res => {
            let successData: any = res
            if (successData.apiStatusCode == "SUCCESS") {
              this.suceessmodelRef = this.modalService.show(template, this.options)

            } else {
              this.toastr.errorToastr(successData.apiStatusDesc, 'Error')
            }
          }).catch(e => {
            this.toastr.errorToastr(e.message, 'Oops!')
          })
        }
      } else {
        var error = "Invalid email Id"
        if (this.invalidEmails.length > 0) {
          this.toastr.errorToastr(error + " " + this.invalidEmails, 'Error');
        }
      }

    } else {
      this.submitted = true

    }
  }
    setTimeout(() => {
      this.isClickedSubmitted = false
    }, 2000)
  }
  /***************************************************************************/
  invalidEmails: any = [];
  Valid = true
  validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
  }

  testEmailValidation(email) {
    var emails = email.split(',');
    this.invalidEmails = [];
    this.Valid = true;

    for (var i = 0; i < emails.length; i++) {
      if (!this.validateEmail(emails[i].trim())) {
        if (emails[i] == "") {
          this.toastr.errorToastr(this.translate.instant('ASSIGNVOLUNTEER.emptyVal'), 'Error');
          this.Valid = false;
          break;
        }
        else {
          this.invalidEmails.push(emails[i].trim());
        }
      }
    }
    if (this.invalidEmails.length > 0) {
      this.Valid = false;
    }

    console.log(this.Valid);

  }

  /****************************************************************************
   @PURPOSE      : checkbox validation
   @PARAMETERS   : NA
   @RETURN       : NA
  ****************************************************************************/

  public check: boolean
  onSelect(event) {
    if (event.target.checked) {
      this.check = true;

    } else {
      this.check = false;

    }
  }
  /***************************************************************************************/



  /****************************************************************************
       @PURPOSE      : appear and disappear the password text
       @PARAMETERS   : form,formdata
       @RETURN       : NA
    ****************************************************************************/
  password: any = "password";
  rePassword: any = "password";
  show = false;
  display = false
  onClick(type) {
    if (type === 'secret') {
      if (this.password === 'password') {
        this.password = 'text';
        this.show = true;
      } else {
        this.password = 'password';
        this.show = false;
      }
    }

    if (type === 'cnfrm') {
      if (this.rePassword === 'password') {
        this.rePassword = 'text';
        this.display = true;
      } else {
        this.rePassword = 'password';
        this.display = false;
      }
    }
  }

  /******************************************************************************/


  /****************************************************************************
       @PURPOSE      : Password strength validation
       @PARAMETERS   : NA
       @RETURN       : NA
    ****************************************************************************/
  isEnable: boolean;
  getData(event) {
    console.log(event);
    this.isEnable = event
  }
  /************************************************************************************* */

  /****************************************************************************
     @PURPOSE      : captcha validation
     @PARAMETERS   : NA
     @RETURN       : NA
  ****************************************************************************/

  isValidCaptcha: boolean
  getCaptcha(event) {
    this.isValidCaptcha = event
  }

  /*****************************************************************************/
  closeModel() {
    this.suceessmodelRef.hide();
    this.router.navigate(["/login"])
  }
  /****************************************************************************
    @PURPOSE      : To get List of district list 
    @PARAMETERS   : NA
    @RETURN       : Master code type active list
   ****************************************************************************/
  public DistrictList = [];
  getListofDistrict() {
    this.commonService.callApi('mastercode/active/DIST', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.DistrictList = successData.masterCodeResultDTOs;
      console.log(this.DistrictList);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

  /****************************************************************************
    @PURPOSE      : Retriving beneficiary Details
    @PARAMETERS   : username
    @RETURN       : NA
 ****************************************************************************/

  public profileData: any = {};
  getBeneficiaryData(username) {

    this.commonService.callApi('authenticate/' + username, '', 'get', false, true, 'REG').then(success => {
      let successData: any = success;
      this.profileData = successData;
      this.memberData.referredBy = this.profileData.firstName + " " + this.profileData.lastName
      console.log(this.profileData);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }
  /********************************************************************************/
}
