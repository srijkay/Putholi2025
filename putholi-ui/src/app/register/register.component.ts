import { HttpClient } from '@angular/common/http';
import { Component, Injector, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from '../common/commonComponent';
declare var $: any;
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent extends BaseComponent implements OnInit {

  public submitted: boolean = false;
  public registerData: any = {};
  public uploadProof: any

  constructor(inj: Injector, public _http: HttpClient) {
    super(inj)
    this.getListofDistrict()
    this.getConfigId()
    if (localStorage.getItem("registerDetails")) {
      this.registerData = JSON.parse(localStorage.getItem("registerDetails"))
      this.getImageByUsername(this.registerData.userName)
    }
  }
  public base64Image = 'data:image/png;base64,'
  ngOnInit(): void {
    this.registerData.country = "IND";
    this.registerData.state = "TamilNadu"
    sessionStorage.removeItem("payment")
    this.removeToken("accessToken")
    localStorage.removeItem("accessToken")
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

  onRegisterForm(form, registerData, suceess) {

    if (!this.isClickedSubmitted) {
      this.isClickedSubmitted = true


      if (form.valid && registerData.confirmPassword == registerData.secretKey) {
        if (this.registerData.emailId != null && this.registerData.emailId != "") {
          console.log(this.registerData.emailId);
          this.testEmailValidation(this.registerData.emailId);
        }
        if (registerData.emailId != undefined) {
          registerData.emailId = registerData.emailId?.toLowerCase()
        }

        // if (registerData.userName != undefined) {
        //   registerData.userName = registerData.userName?.toLowerCase()
        // }

        console.log(this.uploadProof[0]);


        if (this.Valid == true) {

          const sendData = new FormData();

          registerData.active = "N"
          registerData.status = this.applConfigData.configValue != 0 ? "PENPAY" : "PENADM"
          registerData.role = "TRUSTM";
          registerData.createdBy = this.getToken('role')
          registerData.state = "TN"
          registerData.registrationFee = this.applConfigData.configValue

          let userData = JSON.stringify(registerData)
          sendData.append('UserRegisterDetailsDTO', userData)
          if (this.uploadProof[0].type)
            sendData.append('identifyproof', this.uploadProof[0])


          if (!this.check) {
            this.toastr.errorToastr("Please accept terms and conditions", 'Error');
          } else if (!this.isValidCaptcha) {
            this.toastr.errorToastr("Please verify the entered captcha", 'Error')
          } else {
            this.commonService.callApi('authenticate/registeruser', sendData, 'post', true, true, 'LOG').then(res => {
              let successData: any = res
              if (successData.apiStatusCode == "SUCCESS") {
                if (this.applConfigData.configValue != 0) {
                  this.router.navigate(['/summary/', registerData.userName])
                  localStorage.setItem("registerDetails", userData);
                } else {
                  this.suceessmodelRef = this.modalService.show(suceess, this.options)
                }

              } else {
                this.toastr.errorToastr(successData.apiStatusDesc, 'Error')
              }
            }).catch(e => {
              this.toastr.errorToastr(e.message, 'Error')
            })
          }

        } else {

          var error = "Invalid email Id"
          if (this.invalidEmails.length > 0) {
            this.toastr.errorToastr(error + " " + this.invalidEmails, 'Error');
          }
        }
      } else {
        console.log("-=-=-=-=-=============");

        this.submitted = true

      }


    } setTimeout(() => {
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

  closeModel() {
    this.suceessmodelRef.hide();
    this.router.navigate(["/login"])
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

  isValidCaptcha: boolean;
  getCaptcha(event) {
    this.isValidCaptcha = event
  }

  /**************************************************************************** */
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
  applConfigData: any = {}
  getConfigId() {
    this.commonService.callApi('config/Trust_Member_Fee', '', 'get', false, true, 'LOG').then(success => {
      this.applConfigData = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  /**************************************************************************
       @PURPOSE      : get user image by username
       @PARAMETERS   : Username
       @RETURN       : image
 ****************************************************************************/

  getImageByUsername(username) {
    this.commonService.callApi('attachment/' + username + '/' + 'IP', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      console.log(successData);

      this.uploadProof = [
        { name: successData.fileType, size: successData.fileData }
      ]
      console.log(this.uploadProof);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /***********************************************************************/

}
