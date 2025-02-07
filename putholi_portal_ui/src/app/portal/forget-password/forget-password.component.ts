import { HttpClient } from '@angular/common/http';
import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent extends BaseComponent implements OnInit {
  schoolDetailsId: any
  constructor(inj: Injector, public _http: HttpClient) {
    super(inj)
    this.activatedRoute.params.subscribe(params => {
      this.schoolDetailsId = params['id']
    })
  }
  public forgetData: any = {};
  public submitted: boolean = false;
  ipAddress: any;

  ngOnInit(): void {
    this.loadScript('assets/js/main.js');

    this._http.get<{ ip: string }>('https://jsonip.com')
      .subscribe(data => {
        this.ipAddress = data
      })
  }

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

  /****************************************************************************
   @PURPOSE      : to submit Forget Password details
   @PARAMETERS   : form,formdata
   @RETURN       : NA
****************************************************************************/
  public forgotSubmitted: boolean = false
  onForget(form, forgetData) {
    if (form.valid) {
      forgetData.address = this.ipAddress.ip
      if (forgetData.personalEmail != undefined) {
        forgetData.emailid = forgetData.emailid.toLowerCase()
      }
      this.commonService.callApi('donorauthenticate/forgetpassword/' + forgetData.emailid + '/' + forgetData.address, '', 'get', false, true, 'LOG').then(success => {
        let successData: any = success
        if (successData.apiStatusCode === "SUCCESS") {
          if (this.schoolDetailsId != 0) {
            this.router.navigate(["/portal/donate/", this.schoolDetailsId]);
          } else {
            this.router.navigate(['/portal/trackingdonation'])
          }
          this.toastr.successToastr(successData.apiStatusDesc, 'Success')
        } else {
          this.toastr.errorToastr(successData.apiStatusDesc, 'Error');
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }
  }
  /*********************************************************************************/

  clickCancel() {
    if (this.schoolDetailsId != 0) {
      this.router.navigate(["/portal/donate/", this.schoolDetailsId]);
    } else {
      this.router.navigate(['/portal/trackingdonation'])
    }
  }

  /****************************************************************************
   @PURPOSE      : email validation
   @PARAMETERS   : NA
   @RETURN       : NA
****************************************************************************/
  isValidEmail: boolean
  getEmail(event) {
    this.isValidEmail = event
    console.log(this.isValidEmail);

  }

  /******************************************************************/
  public loadScript(url: string) {
    const body = <HTMLDivElement>document.body;
    const script = document.createElement('script');
    script.innerHTML = '';
    script.src = url;
    script.async = false;
    script.defer = true;
    body.appendChild(script);
  }
}
