import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../common/commonComponent';
import { EnvService } from '../common/env.service';

@Component({
  selector: 'app-payment-confirmation',
  templateUrl: './payment-confirmation.component.html',
  styleUrls: ['./payment-confirmation.component.css']
})
export class PaymentConfirmationComponent extends BaseComponent implements OnInit {
  paymentId: any
  routingParams: any
  consolidateId: any

  statusMessage: any
  bankRefNo: any
  keyValuePairs = {};
  constructor(inj: Injector, private envservice: EnvService) {
    super(inj)


    this.removeToken("payment")

    const response = this.router.parseUrl(this.router.url).queryParams["response"];
    const decodedResponse = atob(response).toString();

    const parameters = decodedResponse.split('&');

    parameters.forEach(parameter => {
      const [key, value] = parameter.split('=');
      this.keyValuePairs[key] = value;
    });

    this.statusMessage = this.keyValuePairs["order_status"]

    let count: any = this.getToken('count')
    if (this.statusMessage == 'Success' && count == 0) {
      count += 1
      this.setToken('count', count)

      this.trustAccountBook(this.keyValuePairs)
      this.projectAccountBook(this.keyValuePairs)
    }

  }

  ngOnInit(): void {
    this.removeToken("accessToken")
    localStorage.removeItem("accessToken")
  }






  trustAccountBook(data) {
    console.log(data);

    let accountBook: any = {
      amount: Number(data.amount) - Number(this.getToken("bankCharges")),

      description: "Collected the amount from the registered user and the payment mode is " + this.getToken('paymentMode'),
      orderId: data.order_id,
      bankRefNo: data.bank_ref_no,
      trackingId: data.tracking_id,
      feeType: "INC",
      paymentId: data.order_id,
      bankCharge: this.getToken("bankCharges"),
      paymentType: data.payment_method,
    }

    console.log(accountBook);
    this.commonService.callApi("trustaccountbook", accountBook, 'post', false, true, 'LOG').then(success => {
      let successData: any = success
      console.log(successData);
    })
  }

  projectAccountBook(data) {

    let projectBook: any = {
      amount: Number(data.amount) - Number(this.getToken("bankCharges")),

      description: "Collected the amount from the registered user",
      orderId: data.order_id,
      bankRefNo: data.bank_ref_no,
      trackingId: data.tracking_id,
      feeType: "INC",
      paymentId: data.order_id,
      bankCharge: this.getToken("bankCharges"),
      remarks: this.getToken("userStatus") != null ? 'RENPAY' : 'REGFEE'
    }

    this.commonService.callApi("trustmember", projectBook, 'post', false, true, 'LOG').then(success => {
      let successData: any = success
      console.log(successData);

    })
  }

}
