import { HttpClient } from '@angular/common/http';
import { Component, Injector, OnInit, ViewChild } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
declare var $: any;

@Component({
  selector: 'app-email-send-to-deo',
  templateUrl: './email-send-to-deo.component.html',
  styleUrls: ['./email-send-to-deo.component.css']
})
export class EmailSendToDEOComponent extends BaseComponent implements OnInit {

  ipAddress: any;
  schoolId: any
  constructor(inj: Injector, public _http: HttpClient) {
    super(inj)
    this.getListofsports()
    this.getSportsDesc()
    this.callStatusApi();
    this.getListofEmails();

    this.activatedRoute.params.subscribe((params) => {
      let id = params['id']
      this.schoolId = id;

      this.schoolListById(id)
      this.getBeneficiaryData(params['id2'])
    });
  }

  ngOnInit(): void {
    setTimeout(() => {
      console.log(this.requirementList);
    }, 1000);

    this._http.get<{ ip: string }>('https://api64.ipify.org?format=json')
      .subscribe(data => {
        this.ipAddress = data
      })

      console.log(this.ipAddress);
      
  }



  showCc: boolean = false
  showBcc: boolean = false
  carbonCopy(event) {
    if (event == 'CC') {
      this.showCc = true
    } else {
      this.showBcc = true
    }
  }

  selectCancel() {
    this.showCc = false;
    this.showBcc = false;
  }

  /****************************************************************************
     @PURPOSE      : Retriving beneficiary Details
     @PARAMETERS   : username
     @RETURN       : NA
  ****************************************************************************/
  public profileData: any = {};
  getBeneficiaryData(username) {
    this.commonService.callApi('authenticate/' + username, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.profileData = successData;
      this.profileData.fullName = this.profileData.firstName + " " + this.profileData.lastName
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /********************************************************************************/
  mailData: any = {}
  mailContent: any = ""
  getMailData() {
    console.log("email sending");

    let mailCc: any

    if (this.mailData.mailcc != undefined) {
      mailCc = this.profileData.emailId + "," + this.mailData.mailcc;
    } else {
      mailCc = this.profileData.emailId
    }
    //check email validations

    if (this.mailData.to != null && this.mailData.to != undefined && this.mailData.to != "") {
      this.testEmailValidation(this.mailData.to);
      if (this.Valid) {
        if (this.mailData.mailcc != null && this.mailData.mailcc != undefined && this.mailData.mailcc != "") {
          this.testEmailValidation(this.mailData.mailcc);
        }
      }
    }

    console.log("----sending email", this.mailData);

    if (this.Valid == true) {

      // this.mailData.message = this.mailData.message.replace(/[/]/g, '%2F');

      this.commonService.callApi('schoolinfo/sendMailtoDEO/' + this.mailData.to + '/' + this.ipAddress.ip + '/' + this.schoolId + '/' + this.mailData.message + '/' + this.schoolInfo.consolidateRefInfo.consolidateId + '/' + mailCc, '', 'get', false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.apiStatusCode == 'SUCCESS') {
          this.toastr.successToastr(successData.apiStatusDesc, 'Success')

          this.router.navigate(["/main/pending-workflow"])
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    } else {
      var error = "Invalid email Id"
      if (this.invalidEmails.length > 0) {
        this.toastr.errorToastr(error + " " + this.invalidEmails, 'Error');
      }
    }
  }

  /******************************************************************************************************/


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

  }


  /****************************************************************************
   @PURPOSE      : To get List of deo email  
   @PARAMETERS   : NA
   @RETURN       : list of emails
  ****************************************************************************/
  public emailList = [];
  getListofEmails() {
    this.commonService.callApi('deomastercode/active', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.emailList = successData.deoMasterCodeResultDTO;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

}
