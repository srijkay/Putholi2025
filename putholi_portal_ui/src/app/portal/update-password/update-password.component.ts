import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent extends BaseComponent implements OnInit {

  public submitted: boolean = false;
  public updatePassword: any = {}
  schoolDetailsId: any
  constructor(inj: Injector) {
    super(inj)
    this.activatedRoute.params.subscribe(param => {
      this.schoolDetailsId = param['id']

    })
  }

  ngOnInit(): void {
  }

  /****************************************************************************
  @PURPOSE      : to submit update Password details
  @PARAMETERS   : form,formdata
  @RETURN       : NA
 ****************************************************************************/

  submitForm(form, updatePassword) {
    if (form.valid) {
      updatePassword = {
        "newsecretkey": updatePassword.newsecretkey,
        "secretKey": updatePassword.secretKey,
        "username": this.getToken('emailId')
      }
      console.log(updatePassword);

      this.commonService.callApi('donorauthenticate/updatecredentials', updatePassword, 'post', false, true, 'LOG').then(success => {
        let successData: any = success
        if (successData.status === "SUCCESS") {
          if (this.schoolDetailsId != 0) {
            this.router.navigate(["/portal/donate/", this.schoolDetailsId]);
          } else {
            this.router.navigate(['/portal/trackingdonation'])
          }
          this.toastr.successToastr(successData.statusDescription, 'Success')
        } else {
          this.toastr.errorToastr(successData.statusDescription, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
      });
    }

  }
  /******************************************************************************/

  /****************************************************************************
       @PURPOSE      : appear and disappear the password text
       @PARAMETERS   : form,formdata
       @RETURN       : NA
  ****************************************************************************/
  password: any = "password";
  rePassword: any = "password";
  cnfrmPassword: any = "password";
  cnfrm = false;
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

    if (type === 'new') {
      if (this.rePassword === 'password') {
        this.rePassword = 'text';
        this.display = true;
      } else {
        this.rePassword = 'password';
        this.display = false;
      }
    }
    if (type === 'cnfrm') {
      if (this.cnfrmPassword === 'password') {
        this.cnfrmPassword = 'text';
        this.cnfrm = true;
      } else {
        this.cnfrmPassword = 'password';
        this.cnfrm = false;
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
    console.log(event);
    this.isValidCaptcha = event
  }

  /******************************************************************/
  clickCancel() {
    if (this.schoolDetailsId != 0) {
      this.router.navigate(["/portal/donate/", this.schoolDetailsId]);
    } else {
      this.router.navigate(['/portal/trackingdonation'])
    }
  }
}
