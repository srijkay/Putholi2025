import { DatePipe } from '@angular/common';
import { Component, Injector, OnInit } from '@angular/core';
import { ConfigurationOptions, ContentOptionsEnum, CustomCountryModel } from 'intl-input-phone';
import { BaseComponent } from 'src/app/common/commonComponent';
declare var $: any;
@Component({
  selector: 'app-donate-now',
  templateUrl: './donate-now.component.html',
  styleUrls: ['./donate-now.component.css']
})
export class DonateNowComponent extends BaseComponent implements OnInit {

  public submitted: boolean = false;
  public donateNowData: any = {}
  public loginData: any = {}
  public isOrganzn: boolean = false;
  public isForm: boolean = false;
  public isRegister: boolean = false;
  public isCorporate: boolean = false;
  public isRupees: boolean = false;
  rePassword: any = "password";
  display = false;


  configOption1: ConfigurationOptions;

  showingEstimatedAmount: any = {}

  quotateAmount: any = 0
  orderId: any
  schoolDetailsId: any
  constructor(inj: Injector, public datepipe: DatePipe) {
    super(inj)
    this.getOrganizationType()
    this.getEntityType()
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id']
      this.schoolDetailsId = id
      // this.donateNowData.quotateAmount = 0
      this.getSchoolList(id)
      this.removeToken('count')

    })
    this.getCountrList()

    sessionStorage.removeItem("payment")
    this.removeToken('count')

  }

  /****************************************************************************
    @PURPOSE      : check box validation
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
  /*************************************************************************************/

  schoolDetails: any = {}
  reqId: any;


  ngOnInit(): void {
    this.loadScript('assets/js/main.js');
    this.configOption1 = new ConfigurationOptions();
    this.configOption1.SelectorClass = "WithBasic";
    this.donateNowData.collectedAmount = 0;
  }
  public loadScript(url: string) {

    const body = <HTMLDivElement>document.body;
    const script = document.createElement('script');
    script.innerHTML = '';
    script.src = url;
    script.async = false;
    script.defer = true;
    body.appendChild(script);
  }
  /****************************************************************************
     @PURPOSE      : To submit Donate details
     @PARAMETERS   : form,formdata
     @RETURN       : NA
   ****************************************************************************/
  successmodelRef: any
  onDonate(form, donateNowData, template) {
    console.log("form.valid", donateNowData);

    if (form.valid && donateNowData.confirmPassword == donateNowData.secretKey) {
      this.donateNowData.createdBy = "DONOR";
      this.donateNowData.active = "Y"

      if (donateNowData.phoneNumber.Number) {
        let number = donateNowData.phoneNumber.Number
        donateNowData.phoneNumber = number.replace(/[^\d]/g, '');
      }

      if (donateNowData.emailId != undefined) {
        donateNowData.emailId = donateNowData.emailId?.toLowerCase()
        this.testEmailValidation(this.donateNowData.emailId);

      }

      if (this.Valid == true) {
        if (!this.check) {
          this.toastr.errorToastr("Please accept terms and conditions", 'Error')
        }
        else {

          this.commonService.callApi("donorauthenticate/savedonor", donateNowData, 'post', false, true, 'LOG').then(success => {
            let successData: any = success;
            console.log(successData);

            if (successData.apiStatusCode === "SUCCESS") {
              this.donateNowData = {}
              this.isOrganzn = false;
              this.isRegister = false;
              this.isForm = false;
              this.isCorporate = false;
              $(".form-check-input").prop('checked', false);
              //this.toastr.successToastr("Registered Successfully! Please try to Login", "Success")
              this.successmodelRef = this.modalService.show(template, this.options)
            } else {
              this.toastr.errorToastr(successData.apiStatusDesc, 'Error');
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
    }
    else {
      this.submitted = true;
    }
  }
  /********************************************************************************/

  closeModel() {
    this.successmodelRef.hide();

    const yesRadioButton = document.getElementById('flexRadioDisabled') as HTMLInputElement;
    if (yesRadioButton) {
      yesRadioButton.checked = true;
    }
    this.individualForm()
  }
  options: any = {
    backdrop: 'static',
    keyboard: false
  };

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
         @PURPOSE      : to submit login details
         @PARAMETERS   : form,formdata
         @RETURN       : NA
****************************************************************************/

  onLogin(form, loginData) {
    if (form.valid && this.showingEstimatedAmount.YouCanContributeinRs) {
      console.log(loginData);
      this.ngxLoader.start();
      setTimeout(() => {
        this.commonService.callApi('authenticate/validateuser', loginData, 'post', false, true, 'LOG').then(success => {
          let successData: any = success;
          if (successData.status === "SUCCESS") {
            this.setToken('emailId', successData.emailId)
            if (successData.changePasswordRequired) {
              this.router.navigate(["/portal/update-password/", this.schoolDetailsId]);
              this.ngxLoader.stop();
            } else {
              this.router.navigate(['/portal/summary/', this.schoolDetailsId]);

              this.setToken("donatedAmount", this.showingEstimatedAmount.YouCanContributeinRs)

              this.toastr.successToastr(successData.statusDescription, "Success")
            }
          } else {
            this.ngxLoader.stop();
            this.toastr.errorToastr(successData.statusDescription, 'Error');
          }
        }).catch(e => {
          this.ngxLoader.stop();
          console.log(e);
          this.toastr.errorToastr(e.error.statusDescription, 'Oops!')
        })
      })
    } else {
      this.submitted = true;
      this.toastr.errorToastr("Please provide the required data", "Oops!")
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
  /****************************************************************************
      @PURPOSE      : email validation
      @PARAMETERS   : NA
      @RETURN       : NA
   ****************************************************************************/
  isValidEmail: boolean
  getEmail(event) {
    this.isValidEmail = event

  }

  /******************************************************************/
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

  /****************************************************************************/


  organzForm() {
    this.isOrganzn = false;
    this.isRegister = false;
    this.isForm = true;

  }
  individualForm() {
    this.isOrganzn = true;
    this.isRegister = false;
    this.isForm = false;
    this.isCorporate = false;
  }
  donationForm() {
    this.isForm = true;
    this.isRegister = true;
    this.isCorporate = false;
  }
  donationCorporate() {
    this.isForm = true;
    this.isRegister = false;
    this.isCorporate = true;
  }
  onMoney(e) {
    if (e.target.value == 'N') {
      this.toastr.errorToastr(" Could not accept other currencies at this moment.", "Sorry! ")
    }
  }
  onTransfer() {
    this.isRupees = false
  }

  paymentCancel() {
    console.log("lkjhg");
    this.isOrganzn = false;
    this.isRegister = false;
    this.isForm = false;
    this.isCorporate = false;
    $(".form-check-input").prop('checked', false);
  }


  paymentDetails(id) {
    this.commonService.callApi("projectbook/" + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      let sum = 0
      for (let i = 0; i < successData.length; i++) {
        sum += successData[i].amount;
      }
      this.showingEstimatedAmount.collectedAmount = sum
      this.showingEstimatedAmount.YouCanContribute = this.quotateAmount - this.showingEstimatedAmount.collectedAmount
      console.log(this.showingEstimatedAmount.YouCanContribute);
    })
  }

  getSchoolList(id) {
    this.commonService.callApi('schoolinfo/' + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      console.log(successData);
      this.schoolDetails = successData
      let id = this.schoolDetails.consolidateRefInfo.find(x => x.status != 'CMPLTD')
      console.log(id);

      this.paymentDetails(id.consolidateId)
      let requirementInfo: any = id.requirementInfo.filter(x => x.reqStatus == 'QUOARV')

      requirementInfo.forEach(e => {
        console.log(e.quotationInfo);
        let sum = 0;
        let quotData: any = e.quotationInfo.filter(x => x.quotateStatus == 'QUOARV')
        for (let i = 0; i < quotData.length; i++) {
          console.log(quotData[i].totalAmount);
          sum = quotData[i].totalAmount;
        }
        this.quotateAmount += sum
        console.log(this.quotateAmount);
        this.setToken("quotateAmount", this.quotateAmount)
      });

    })
  }
}
