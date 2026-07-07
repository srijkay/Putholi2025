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
  pageNumber: number = 1
  page: any = 1;
  pageSize: any
  pagesize: any = 10

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
            this.getTheTrackingDetails(successData.emailId)
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

  projectBookData: any = {}
  collectedDonationAmount: any = 0
  getTheTrackingDetails(email: any) {
    this.commonService.callApi('projectbook/emailId/' + email, "", 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      if (successData) {
        this.projectBookData = successData
        this.pageSize = this.projectBookData.length


        this.collectedDonationAmount = this.projectBookData.reduce((sum: any, current: any) => sum + current.amount, 0);

      console.log(this.projectBookData, this.collectedDonationAmount);

      } else {
        this.toastr.errorToastr(successData.statusDescription, 'Error');
      }
    }).catch(e => {
      this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
    })
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
  showPassword: boolean = false
  emailValidateion(form: any, loginData: any) {
    if (form.valid) {
      this.commonService.callApi('donorauthenticate/validate/' + loginData.username, "", 'get', false, true, 'LOG').then(success => {
        let successData: any = success;
        if (successData.apiStatusCode === "SUCCESS") {
          // this.showPassword = true
          this.setToken('emailId', loginData.username)
          this.isShowTracking = true
          this.getTheTrackingDetails(loginData.username)
          this.toastr.successToastr(successData.statusDescription, "Success")
        } else {
          // this.showPassword = false
          this.isShowTracking = false
          this.toastr.errorToastr(successData.apiStatusDesc, 'Oops!')
        }
      }).catch(e => {
        this.ngxLoader.stop();
        console.log(e);
        this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
      })
    } else {
      this.submitted = true;
      this.toastr.errorToastr("Please provide the required data", "Oops!")
    }

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