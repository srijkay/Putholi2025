import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../common/commonComponent';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent extends BaseComponent implements OnInit {
  public updatePassword: any = {}
  constructor(inj: Injector) {
    super(inj);
  }

  ngOnInit(): void {
  }


  submitForm(form, updatePassword) {
    if (form.valid) {
      updatePassword = {
        "newsecretkey": updatePassword.newsecretkey,
        "secretKey": updatePassword.secretKey,
        "username": this.getToken('username')
      }
      this.commonService.callApi('authenticate/updatecredentials', updatePassword, 'post', false, true, 'LOG').then(success => {
        let successData: any = success
        if (successData.status === "SUCCESS") {
          this.router.navigate(["/login"]);
          this.toastr.successToastr(successData.statusDescription, 'Success')
        } else {
          this.toastr.errorToastr(successData.statusDescription, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
      });
    }

  }



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
