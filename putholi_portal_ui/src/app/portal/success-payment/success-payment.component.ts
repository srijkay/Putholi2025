import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-success-payment',
  templateUrl: './success-payment.component.html',
  styleUrls: ['./success-payment.component.css']
})
export class SuccessPaymentComponent extends BaseComponent implements OnInit {

  routingParams: any
  consolidateId: any
  keyValuePairs: any = {}
  statusMessage: any

  constructor(inj: Injector) {
    super(inj)

    this.loadScript('assets/js/main.js');

    const response = this.router.parseUrl(this.router.url).queryParams["response"];
    console.log(response);

    const decodedResponse = atob(response).toString();
    console.log(decodedResponse);


    const parameters = decodedResponse.split('&');
    console.log(parameters);

    parameters.forEach(parameter => {
      const [key, value] = parameter.split('=');
      this.keyValuePairs[key] = value;
    });
    console.log(this.keyValuePairs);

    this.statusMessage = this.keyValuePairs["order_status"]

    let count: any = this.getToken('count')
    if (this.statusMessage == 'Success' && count == 0) {
      count += 1
      this.setToken('count', count)
      this.getPaymentDetails(this.keyValuePairs["order_id"])
    }
  }

  ngOnInit(): void {
    this.removeToken('payment')
  }



  dashboardStatistics: any = {}
  paymentResponse: any = {}

  getPaymentDetails(id) {
    this.commonService.callApi('payment/' + id, '', 'get', false, true, 'LOG').then(success => {
      this.paymentResponse = success
      this.getDashboardStatistics(this.paymentResponse)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }




  getDashboardStatistics(data) {
    this.commonService.callApi('dashboard/' + data.consolidateId, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.dashboardStatistics = successData;
      if (this.dashboardStatistics.trustFund != null) {
        this.getConfigId()
      }
      setTimeout(() => {
        this.trustAccountBook(this.keyValuePairs)
        this.projectAccountBook(this.keyValuePairs)
      }, 200)


    }).catch(e => {
      this.toastr.errorToastr(e.message);
    })
  }



  trustAccountBook(data) {
    console.log(data);

    let contribute: number
    let status: any
    contribute = Number(this.getToken("quotateAmount")) - Number(this.dashboardStatistics.collectedAmount) - (Number(data.amount) - Number(this.getToken('bankCharges')))
    if ((Number(this.getToken("colectedforProject")) + Number(this.getToken("collectedTillNow"))) == Number(this.getToken("quotateAmount"))) {
      status = "Fully Collected"
    } else if (Number(contribute) <= Number(this.applConfigData.configValue)) {
      status = "Fully Collected"
    } else {
      status = "Deposited"
    }
    let accountBook: any = {
      amount: Number(data.amount) - Number(this.getToken("bankCharges")),
      description: "Donor donated the amount to the project NO " + this.paymentResponse.consolidateId + " and the payment mode is " + this.getToken('paymentMode'),
      orderId: data.order_id,
      bankRefNo: data.bank_ref_no,
      trackingId: data.tracking_id,
      feeType: "INC",
      paymentId: data.order_id,
      projectId: this.paymentResponse.consolidateId,
      paymentType: data.payment_method,
      remarks: status,
      bankCharge: this.getToken("bankCharges"),
    }
    this.commonService.callApi("trustaccountbook", accountBook, 'post', false, true, 'LOG').then(success => {
      let successData: any = success
    })
  }

  showingEstimatedAmount: any
  projectAccountBook(data) {
    let status: any
    let contribute = Number(this.getToken("quotateAmount")) - Number(this.dashboardStatistics.collectedAmount) - (Number(data.amount) - Number(this.getToken('bankCharges')))
    console.log(contribute, Number(this.applConfigData.configValue), Number(contribute) <= Number(this.applConfigData.configValue));

    if ((Number(this.getToken("colectedforProject")) + Number(this.getToken("collectedTillNow"))) == Number(this.getToken("quotateAmount"))) {
      status = "Fully Collected"
      console.log("if");
    } else if (Number(contribute) <= Number(this.applConfigData.configValue)) {
      status = "Fully Collected"
      console.log("else if");

    } else {
      console.log("else");
      status = "Deposited"
    }

    let projectBook: any = {
      amount: Number(data.amount) - Number(this.getToken("bankCharges")),
      description: "Donor donated the amount to the project NO " + this.paymentResponse.consolidateId + " and the payment mode is " + this.getToken('paymentMode'),
      orderId: data.order_id,
      bankRefNo: data.bank_ref_no,
      trackingId: data.tracking_id,
      feeType: "INC",
      paymentId: data.order_id,
      projectId: this.paymentResponse.consolidateId,
      paymentType: data.payment_method,
      remarks: status,
      bankCharge: this.getToken("bankCharges"),
    }

    this.commonService.callApi("projectbook", projectBook, 'post', false, true, 'LOG').then(success => {
      let successData: any = success

      this.paymentDetails(this.paymentResponse.consolidateId)
    })
  }

  applConfigData: any = {}
  getConfigId() {
    this.commonService.callApi('config/Adjustable_Amount', '', 'get', false, true, 'LOG').then(success => {
      this.applConfigData = success;

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  collectedAmount: any
  paymentDetails(id) {
    this.commonService.callApi("projectbook/" + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      console.log(successData);
      let sum = 0
      for (let i = 0; i < successData.length; i++) {
        sum += successData[i].amount;
      }
      this.collectedAmount = Math.round(Number(this.collectedAmount = sum) * 100);


    })
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
