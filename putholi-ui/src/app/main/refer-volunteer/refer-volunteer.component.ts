import { HttpClient } from '@angular/common/http';
import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-refer-volunteer',
  templateUrl: './refer-volunteer.component.html',
  styleUrls: ['./refer-volunteer.component.css']
})
export class ReferVolunteerComponent extends BaseComponent implements OnInit {


  public volunteerdata: any = {};
  constructor(inj: Injector, public _http: HttpClient) {
    super(inj)
  }

  ipAddress: any
  ngOnInit(): void {
    this._http.get<{ ip: string }>('https://jsonip.com')
      .subscribe(data => {
        this.ipAddress = data
      })
  }

  referVolunteer(form, volunteerdata) {
    if (form.valid) {


      //check email validations
      if (this.volunteerdata.emailId != null && this.volunteerdata.emailId != "") {
        console.log(this.volunteerdata.emailId);

        this.testEmailValidation(this.volunteerdata.emailId);
      }
      if (this.Valid == true) {



        this.commonService.callApi('authenticate/refervolunteer/' + this.volunteerdata.emailId + '/' + this.ipAddress.ip, "", 'get', false, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.apiStatusCode == 'SUCCESS') {
            this.toastr.successToastr(successData.apiStatusDesc, 'Success')
            this.volunteerdata = {}
          } else {
            this.toastr.errorToastr(successData.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Error')
        })
      } else {
        var error = "Invalid email Id"
        if (this.invalidEmails.length > 0) {
          this.toastr.errorToastr(error + " " + this.invalidEmails, 'Error');
        }
      }
    }
  }
  /*********************************************************************************************/



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
}
