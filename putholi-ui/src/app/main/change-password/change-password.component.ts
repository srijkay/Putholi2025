import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent extends BaseComponent implements OnInit {

  public changePasswordData: any = {}
  constructor(inj: Injector) {
    super(inj)
  }

  ngOnInit(): void {
  }

  /****************************************************************************
     @PURPOSE      : To submit change password details
     @PARAMETERS   : changePasswordData
     @RETURN       : NA
****************************************************************************/

  onPass(form, changePasswordData) {
    if (form.valid) {
      changePasswordData = {
        "newsecretkey": changePasswordData.newsecretkey,
        "secretKey": changePasswordData.secretKey,
        "username": this.getToken('username')
      }
      this.commonService.callApi('authenticate/updatecredentials', changePasswordData, 'post', false, false, 'REG').then(success => {
        let successData: any = success
        if (successData.body.status === "SUCCESS") {
          this.router.navigate(["/main/dashboard"]);
          this.toastr.successToastr(successData.body.statusDescription, 'Success')
        } else {
          this.toastr.errorToastr(successData.body.statusDescription, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
      });
    }

  }
  /**************************************************************************/

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

}
