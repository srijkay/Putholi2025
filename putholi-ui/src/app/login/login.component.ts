import { Component, OnInit, Injector, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { BaseComponent } from './../common/commonComponent';
import { EnvService } from '../common/env.service';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService, SocialUser } from 'angularx-social-login';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { HttpClient } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent extends BaseComponent implements OnInit {

  public user: any = {};
  public loggedIn: boolean;
  auth2: any;


  public loginData: any = {};
  public isLogin: boolean = true;
  public isForget: boolean = false;
  public forgetData: any = {}

  @Input("config") config: any = {};
  @Output() captchaCode = new EventEmitter();
  public code: any = null;
  public resultCode: any = null;
  public buildDate: any;
  public buildVersion: any;

  public homeInfo: any = {};
  public submitted: boolean = false;
  constructor(inj: Injector, public _http: HttpClient, private envservice: EnvService, public translate: TranslateService) {
    super(inj);


    const browserLang = translate.getBrowserLang();
    this.homeInfo.lang = browserLang;
    translate.use(browserLang.match(/en|tm/) ? browserLang : 'en');
    localStorage.setItem("Language", browserLang.match(/tm|tm-TM/) ? 'tm' : 'en')
    this.setToken("Language", browserLang.match(/tm|tm-TM/) ? 'tm' : 'en')
    this.buildDate = this.envservice.build_Date;
    this.buildVersion = this.envservice.build_Version;
  }

  ngOnInit(): void {
    localStorage.removeItem("registerDetails")
    sessionStorage.removeItem("payment")
    sessionStorage.removeItem("params")
    this.clearToken();
  }

  /****************************************************************************
     @PURPOSE      : to submit login details
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ****************************************************************************/

  submitLoginForm(form, loginData) {

    // if (localStorage.getItem("isguilty") == "YES") {
    //   alert('Application is already opened in another browser or tab. If not opened, clear the browser cache and try again.');
    //   return 0;
    // }

    if (form.valid) {
      console.log(loginData);
      this.ngxLoader.start();
      setTimeout(() => {
        this.commonService.callApi('authenticate/validateuser', loginData, 'post', false, true, 'LOG').then(success => {
          let successData: any = success;

          if (successData.status === "SUCCESS") {
            if (successData.changePasswordRequired) {
              this.setToken("cpflag", successData.changePasswordRequired);
              this.setToken('username', successData.username);
              this.router.navigate(["/update-password"]);
              this.ngxLoader.stop();
            } else {
              form.reset();
              this.ngxLoader.stop();
              this.setToken('profilePic', successData.profilePic)
              this.setToken("role", successData.roles);
              this.setToken("username", successData.username);
              this.setToken('emailId', successData.emailId);
              this.setToken('firstName', successData.firstName);
              this.setToken('lastName', successData.lastName);
              this.setToken("mobileNumber", successData.mobileNumber)
              if (successData.userStatus === "RENPAY") {
                this.router.navigate(['/summary/', successData.username]);
                this.setToken("userStatus", successData.userStatus)
              } else {
                this.setToken("accessToken", successData.accessToken);
                localStorage.setItem("accessToken", successData.accessToken);
                this.router.navigate(['/main/dashboard']);
                localStorage.setItem("isguilty", "YES")
              }
              this.toastr.successToastr(successData.statusDescription, "Success")
            }
          } else {
            this.ngxLoader.stop();
            this.toastr.errorToastr(successData.statusDescription, 'Error');
          }
        }).catch(e => {
          this.getUser(loginData.username)


          setTimeout(() => {
            console.log(this.userDetails);

            if (this.userDetails != null && this.userDetails != undefined) {
              if (this.userDetails.status == 'CHRREV' || this.userDetails.status == 'CHRAPR' || this.userDetails.status == 'PENSUS' || this.userDetails.status == 'PENSUA' || this.userDetails.status == 'CHRADM') {
                this.toastr.errorToastr("Change role is in progress, Please contact admin.", 'Oops!')
              } else if (this.userDetails.status == 'PENREV' || this.userDetails.status == 'PENAPR' || this.userDetails.status == 'PENADM') {
                this.toastr.errorToastr("Approval is in progress", 'Oops!')
              } else if (this.userDetails.status == 'REJ') {
                this.toastr.errorToastr("Your account is rejected, Please contact admin.", 'Oops!')
              } else if (e.error.statusDescription == "User account is locked") {
                this.toastr.errorToastr("User account is locked, please wait for 20mins")
              } else if (this.userDetails.status == 'DELREV' || this.userDetails.status == 'DELAPR' || this.userDetails.status == 'DELUSR' || this.userDetails.status == 'DELADM' || this.userDetails.status == 'DELSUA') {
                this.toastr.errorToastr("Your account Deletion is in progress, Please contact admin.", 'Oops!')
              } else if (this.userDetails.status == 'DEL') {
                this.toastr.errorToastr("Your account is deleted, Please contact admin.", 'Oops!')

              } else {
                if (e.error.statusDescription == "User account has expired") {
                  this.router.navigate(['/summary/', loginData.username]);
                  this.setToken("userStatus", "RENPAY")
                } else if (this.userDetails.status == 'PENPAY') {
                  this.router.navigate(['/summary/', loginData.username]);
                }
                this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
              }
            } else {
              this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
            }
            this.ngxLoader.stop();
          }, 500)




        })
      }, 1000)

    } else {
      this.submitted = true;
    }

  }

  /****************************************************************************/

  /****************************************************************************
     @PURPOSE      : hide and show login and forget form
     @PARAMETERS   : type
     @RETURN       : NA
  ****************************************************************************/
  ipAddress: any;
  forgotPassword(type) {

    if (type == 'B') {
      this.isLogin = true;
      this.isForget = false;
    }
    else if (type == 'F') {
      this.isLogin = false;
      this.isForget = true;
      this._http.get<{ ip: string }>('https://jsonip.com')
        .subscribe(data => {
          this.ipAddress = data
        })
    }

  }

  /****************************************************************************/



  password: any = "password";
  rePassword: any = "password";
  show = false;

  /****************************************************************************
      @PURPOSE      : hide and show login and forget form
      @PARAMETERS   : type
      @RETURN       : NA
   ****************************************************************************/
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



  /****************************************************************************
    @PURPOSE      : to submit Forget Password details
    @PARAMETERS   : form,formdata
    @RETURN       : NA
 ****************************************************************************/
  public forgotSubmitted: boolean = false
  submitforgetForm(form, forgetData) {
    if (form.valid) {
      forgetData.address = this.ipAddress.ip
      if (forgetData.personalEmail != undefined) {
        forgetData.emailid = forgetData.emailid.toLowerCase()
      }
      this.commonService.callApi('authenticate/forgetpassword/' + forgetData.emailid + '/' + forgetData.address, '', 'get', false, true, 'REG').then(success => {
        let successData: any = success
        if (successData.apiStatusCode === "SUCCESS") {
          this.toastr.successToastr(successData.apiStatusDesc, 'Success')
          form.reset();
          this.isForget = false;
          this.isLogin = true;
        } else {
          this.toastr.errorToastr(successData.apiStatusDesc, 'Error');
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }
  }
  /*********************************************************************************/

  /****************************************************************************
     @PURPOSE      : captcha validation
     @PARAMETERS   : NA
     @RETURN       : NA
  ****************************************************************************/
  isValidCaptcha: boolean
  getCaptcha(event) {
    this.isValidCaptcha = event
    console.log(this.isValidCaptcha);

  }

  /******************************************************************/

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

  public async socialSignIn(socialProvider: string) {

    let socialPlatformProvider;
    if (socialProvider === 'facebook') {
      socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
    } else if (socialProvider === 'google') {
      socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
    }

    let data = await this.SocialAuthService.signIn(socialPlatformProvider)
    console.log(data);
    let socialUser = this.setToken('socialUser', JSON.stringify(data))
    this.submitLoginForm(true, socialUser)
  }

  /****************************************************************************
       @PURPOSE      : get user details based on username
       @PARAMETERS   : username
       @RETURN       : NA
    ****************************************************************************/

  userDetails: any = {}
  getUser(username) {
    this.commonService.callApi('authenticate/' + username, '', 'get', false, true, 'REG').then(success => {
      let successData: any = success
      this.userDetails = successData

    })
  }

}