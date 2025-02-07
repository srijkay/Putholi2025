import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
import { EnvService } from 'src/app/common/env.service';
@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent extends BaseComponent implements OnInit {
  paymentData: any = {}

  public summaryList: any = {};
  emailid: any;
  schoolDetails: any = {}
  requirementInfo: any = []
  consolidateId: any
  schoolInfoId: any
  constructor(inj: Injector, private envservice: EnvService) {
    super(inj)
    this.getConfigId()
    this.getAllMasterList()
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id']
      this.schoolInfoId = id
      this.setToken('schoolId', this.schoolInfoId)
      this.getSchoolList(id)
    })
    this.setToken('count', 0)
    this.getSummary(this.getToken('emailId'))
  }

  showErrorBlock: boolean = false
  ngOnInit(): void {
    this.loadScript('assets/js/main.js');

    if (this.getToken('payment') == 'true') {
      this.router.navigate(['/cancel'])
    }
  }


  sportsInfo: any
  getSchoolList(id) {
    setTimeout(() => {
      this.commonService.callApi('schoolinfo/' + id, '', 'get', false, true, 'LOG').then(success => {
        let successData: any = success
        this.schoolDetails = successData
        this.consolidateId = this.schoolDetails.consolidateRefInfo.find(x => x.status != 'CMPLTD')

        this.requirementInfo = this.consolidateId.requirementInfo.filter(x => x.reqStatus == "QUOARV")

        let require = this.allActiveList.filter(o1 => this.requirementInfo.some(o2 => o1.code === o2.assetName))
        console.log(require);

        this.requirementInfo.forEach(function (checkbox) {
          require.forEach(e => {
            if (e.code == checkbox.assetName)
              checkbox.assetName = e.description
          });
        })


      })
    }, 150)
  }

  /****************************************************************************
    @PURPOSE      : to get summary info based on  EmailId
    @PARAMETERS   : EmailId
    @RETURN       : NA
  ****************************************************************************/
  getSummary(emailid) {
    this.commonService.callApi('donorauthenticate/' + emailid, '', 'get', false, true, 'LOG').then(success => {
      this.summaryList = success;
      console.log(this.summaryList);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/


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
  selectPaymentOption: boolean = false
  clickPayment() {
    console.log(this.paymentData.paymentType);



    let data: any = {
      amount: this.Contribute,
      order_id: this.summaryList.orderId,
      username: this.summaryList.emailId,
      consolidate_id: this.consolidateId.consolidateId,
      payment_type: "DONATE",
      payment_option: this.applConfigData.paymentMode
    }


    if (data.payment_option == 'OPTUPI') {
      this.setToken("bankCharges", 0)
      this.setToken("colectedforProject", this.Contribute)
    } else {
      this.setToken("bankCharges", this.applConfigData.configValue)
      this.setToken("colectedforProject", this.Contribute - (this.applConfigData.configValue))
    }

    this.setToken("paymentMode", this.applConfigData.paymentMode)

    if (this.applConfigData.paymentMode == null || this.applConfigData.paymentMode == undefined || this.applConfigData.paymentMode == '') {
      this.toastr.errorToastr("Please choose the payment mode", 'Error!')
    } else {
      this.commonService.callApi("payment/checkout-redirect", data, 'post', false, true, 'LOG').then(success => {
        let successData: any = success
        console.log(successData);
        if ((successData.encRequest != null || successData.encRequest != undefined) &&
          (successData.accessCode != null || successData.accessCode != undefined)) {
          this.redirectCcavenve(successData)
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }

  }


  redirectCcavenve(successData) {
    const encRequestInput = document.createElement('input');
    encRequestInput.type = 'hidden';
    encRequestInput.name = 'encRequest';
    encRequestInput.value = successData.encRequest;

    const accessCodeInput = document.createElement('input');
    accessCodeInput.type = 'hidden';
    accessCodeInput.name = 'access_code';
    accessCodeInput.value = successData.accessCode;



    const myform = document.createElement('form');
    myform.method = 'POST';
    myform.action = this.envservice.ccavenue_redirect_url

    myform.style.display = 'none';
    myform.append('Content-Type', 'multipart/form-data');
    // Append the input elements to the form
    myform.appendChild(encRequestInput);
    myform.appendChild(accessCodeInput);

    myform.append('Accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8');
    document.body.appendChild(myform);
    myform.submit();


    this.setToken("payment", "true")
  }

  applConfigData: any = {}
  Contribute: number
  getConfigId() {
    this.commonService.callApi('config/Bank_Processing_Fee', '', 'get', false, true, 'LOG').then(success => {
      this.applConfigData = success;

      this.Contribute = Number(this.getToken('donatedAmount'))
      let value = Math.round(Number(this.applConfigData.configValue) * 100);
      this.applConfigData.configValue = Math.round((this.Contribute) * (value / 100))



      // this.setToken("donatedAmount", this.applConfigData.configValue)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  onScanSuccess(result) {
    const qrCodeData = result.codeResult.text;
    console.log(qrCodeData);
    // Use the QR code data to complete the payment process
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
}