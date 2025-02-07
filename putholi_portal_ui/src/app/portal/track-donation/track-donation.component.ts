import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-track-donation',
  templateUrl: './track-donation.component.html',
  styleUrls: ['./track-donation.component.css']
})
export class TrackDonationComponent extends BaseComponent implements OnInit {

  public submitted: boolean = false;
  public trackDonationData: any = {}
  consolidateId: any
  schoolDetails: any = {}

  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.loadScript('assets/js/main.js');
  }

  ngOnInit(): void { }

  isShowTracking: boolean = false
  paymentList: any
  onTrack(form, trackDonationData) {
    if (form.valid) {
      this.commonService.callApi('payment/' + trackDonationData.paymentId, '', 'get', false, true, 'LOG').then(success => {
        this.paymentList = success;
        if (this.paymentList) {
          this.router.navigate(['/portal/track-details/', this.paymentList.consolidateId])
          this.toastr.successToastr('Track your details successfully')
        } else {
          this.toastr.errorToastr('Invalid Tracking Id')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }
  }

  /***************************************************************************/
  loginData: any = {}


  onLogin(form, loginData) {
    if (form.valid) {
      this.commonService.callApi('authenticate/validateuser', loginData, 'post', false, true, 'LOG').then(success => {
        let successData: any = success;
        if (successData.status === "SUCCESS") {
          this.setToken('emailId', successData.emailId)
          if (successData.changePasswordRequired) {
            this.router.navigate(["/portal/update-password/", "0"]);
            this.isShowTracking = false
          } else {
            this.isShowTracking = true
            this.toastr.successToastr(successData.statusDescription, "Success")
          }
        } else {
          this.toastr.errorToastr(successData.statusDescription, 'Error');
        }
      }).catch(e => {
        this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
      })
    }
  }

  /****************************************************************************
        @PURPOSE      : hide and show login and forget form
        @PARAMETERS   : type
        @RETURN       : NA
 ****************************************************************************/
  password: any = "password";
  show = false;

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
  }

  /****************************************************************************/

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