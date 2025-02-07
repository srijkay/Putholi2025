import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-product-configuration-edit',
  templateUrl: './product-configuration-edit.component.html',
  styleUrls: ['./product-configuration-edit.component.css']
})
export class ProductConfigurationEditComponent extends BaseComponent implements OnInit {


  constructor(inj: Injector) {
    super(inj)

    this.getProductInfo()

    this.callStatusApi()
  }

  public submitted: boolean = false;
  public productConfigData: any = {};

  batchId: any
  ngOnInit(): void {
  }

  /****************************************************************************
       @PURPOSE      : Update Product Configuration details
       @PARAMETERS   : form,formdata
       @RETURN       : NA
  ****************************************************************************/

  uploadProof: any
  onproductConfig(form, productConfigData) {
    if (form.valid) {
      const sendData = new FormData();
      this.productConfigData.lastModifiedBy = this.getToken('role');
      delete productConfigData.companyLogo;
      let data = JSON.stringify(productConfigData)
      if (this.uploadProof[0].type) {
        sendData.append('companyLogo ', this.uploadProof[0])
      }
      sendData.append('ProductConfigDTO', data)
      this.commonService.callApi('productconfig', sendData, 'put', true, false, 'REG').then(success => {
        let successData: any = success
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }

  }
  /****************************************************************************/


  /****************************************************************************
    @PURPOSE      : to get product config info based on companyId
    @PARAMETERS   : companyId
    @RETURN       : NA
  ****************************************************************************/
  getProductInfo() {
    this.commonService.callApi('productconfig', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.productConfigData = successData[0]

      this.uploadProof = [
        { name: "image/png", size: this.productConfigData.companyLogo }
      ]
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /************************************************************************/
}
