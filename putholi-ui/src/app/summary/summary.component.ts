import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../common/commonComponent';
import { EnvService } from '../common/env.service';
import { HttpHeaders } from '@angular/common/http';
import { loadStripe } from '@stripe/stripe-js';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent extends BaseComponent implements OnInit {
  showErrorBlock: boolean = false;
  constructor(inj: Injector, private envservice: EnvService) {
    super(inj)

    this.getConfigId()

    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      this.getUserDetailsByName(id)
    })

    this.setToken('count', 0)
  }

  public userData: any = {};
  ngOnInit(): void {
    this.getRoleDetails();

    if (this.getToken('payment') == 'true') {
      this.router.navigate(['/cancel'])
    }
  }

  /****************************************************************************
 @PURPOSE      : To get userdetails by username
 @PARAMETERS   : username
 @RETURN       : UserDetailsDTO
 ****************************************************************************/

  registerData: any

  getUserDetailsByName(username) {
    this.commonService.callApi("authenticate/" + username, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      this.registerData = successData
      this.setToken("username", this.registerData.userName)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })

  }
  /****************************************************************************/

  getPaymentDetails(): void {

    let data: any = {
      amount: this.applConfigData.configValue,
      order_id: this.registerData.orderId,
      username: this.registerData.userName,
      payment_type: "REGFEE",
      payment_option: this.applConfigData.paymentMode
    }


    if (data.payment_option == 'OPTUPI') {
      this.setToken("bankCharges", 0)
    } else {
      this.setToken("bankCharges", this.bankCharges.configValue)
    }

    this.setToken("paymentMode", this.applConfigData.paymentMode)
    console.log(data);
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





  /****************************************************************************
   @PURPOSE      : To get trust member fee
   @PARAMETERS   : NA
   @RETURN       : member Fee
 ****************************************************************************/
  applConfigData: any = {}
  getConfigId() {
    this.commonService.callApi('config/Trust_Member_Fee', '', 'get', false, true, 'LOG').then(success => {
      this.applConfigData = success;
      this.getBankCharges()
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /***********************************************************************/

  bankCharges: any = {}
  Contribute: number
  getBankCharges() {
    this.commonService.callApi('config/Bank_Processing_Fee', '', 'get', false, true, 'LOG').then(success => {
      this.bankCharges = success;

      let value = Math.round(Number(this.bankCharges.configValue) * 100);
      this.bankCharges.configValue = (Number(this.applConfigData.configValue) * value / 100);



    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }


  /****************************************************************************
    @PURPOSE      : To get List of Roles
    @PARAMETERS   : NA
    @RETURN       : Roles List
  ****************************************************************************/
  listofRoles: any = []
  public roleDetailList: any = {};
  getRoleDetails() {
    this.commonService.callApi('mastercode/active/ROLE', '', 'get', false, true, 'LOG').then(success => {
      this.listofRoles = success
      this.listofRoles = this.listofRoles.masterCodeResultDTOs;

      this.roleDetailList = this.listofRoles.find(x => x.code == this.getToken('role'));
      console.log(this.roleDetailList);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

}
