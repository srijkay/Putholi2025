import { HttpClient, HttpHeaders, HttpResponse, HttpParams, } from '@angular/common/http';
import { Component, Injector, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { send } from 'process';
import { BaseComponent } from '../common/commonComponent';

@Component({
  selector: 'app-beneficiary-register',
  templateUrl: './beneficiary-register.component.html',
  styleUrls: ['./beneficiary-register.component.css']
})
export class BeneficiaryRegisterComponent extends BaseComponent implements OnInit {

  public submitted: boolean = false;
  public beneficiaryData: any = {};
  public uploadProof: any

  public genderList = [
    {
      "name": "Female",
      "code": "F"
    },
    {
      "name": "Male",
      "code": "M"
    },
    {
      "name": "Others",
      "code": "O"
    },
  ]

  constructor(inj: Injector, public _http: HttpClient) {
    super(inj)
    this.getListofDistrict()
  }

  ngOnInit(): void {
    this.beneficiaryData.country = "IND";
    this.beneficiaryData.state = "TamilNadu"
  }

  public base64Image = 'data:image/png;base64,'
  options: any = {
    backdrop: 'static',
    keyboard: false
  };


  /****************************************************************************
     @PURPOSE      : to submit beneficiary details
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ****************************************************************************/
  public file: any = "";
  suceessmodelRef: BsModalRef;
  isClickedSubmitted: boolean = false
 

  onBeneficiary(form, beneficiaryData, template) {
    if (!this.isClickedSubmitted) {
      this.isClickedSubmitted = true
  
    if (form.valid && beneficiaryData.confirmPassword == beneficiaryData.secretKey) {

      const sendData = new FormData();
      beneficiaryData.active = "N";
      beneficiaryData.status = "PENADM";
      beneficiaryData.role = "BENIF";
      beneficiaryData.createdBy = "admin"
      beneficiaryData.state = "TN"
      if (this.beneficiaryData.emailId != null && this.beneficiaryData.emailId != "") {
        console.log(this.beneficiaryData.emailId);
        this.testEmailValidation(this.beneficiaryData.emailId);
      }
      if (beneficiaryData.emailId != undefined && beneficiaryData.emailId != null) {
        beneficiaryData.emailId = beneficiaryData.emailId?.toLowerCase()
      }
      // if (beneficiaryData.userName != undefined) {
      //   beneficiaryData.userName = beneficiaryData.userName?.toLowerCase()
      // }

      if (this.Valid == true) {
        sendData.append('UserRegisterDetailsDTO', JSON.stringify(beneficiaryData))
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
      this.submitted = true

    }
  } setTimeout(() => {
    this.isClickedSubmitted = false
  }, 2000)

  }
  
  /****************************************************************************/
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


  /****************************************************************************
      @PURPOSE      : appear and disappear the password text
      @PARAMETERS   : NA
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
  /*******************************************************************************/


  /****************************************************************************
      @PURPOSE      : password Strength validation
      @PARAMETERS   : NA
      @RETURN       : NA
   ****************************************************************************/
  isEnable: boolean;
  getData(event) {
    console.log(event);
    this.isEnable = event
  }
  /***********************************************************************************/



  /****************************************************************************
      @PURPOSE      : Captcha validation
      @PARAMETERS   : NA
      @RETURN       : NA
   ****************************************************************************/
  isValidCaptcha: boolean
  getCaptcha(event) {
    this.isValidCaptcha = event
  }

  /**********************************************************************************/
  closeModel() {
    this.suceessmodelRef.hide();
    this.router.navigate(["/login"])
  }

  /****************************************************************************
    @PURPOSE      : To get List of district list 
    @PARAMETERS   : NA
    @RETURN       : Master code type active list
   ****************************************************************************/
  public DistrictList = [
    // {
    //   description:'Trichy',
    //   code:'TRI'
    // }
  ];
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
}
