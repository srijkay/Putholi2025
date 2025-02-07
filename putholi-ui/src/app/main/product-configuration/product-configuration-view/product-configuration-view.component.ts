import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-product-configuration-view',
  templateUrl: './product-configuration-view.component.html',
  styleUrls: ['./product-configuration-view.component.css']
})
export class ProductConfigurationViewComponent extends BaseComponent implements OnInit {
  public productConfigData: any
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj);
  }

  ngOnInit(): void {
    this.getProductInfo()
  }
  /****************************************************************************
      @PURPOSE      : to get product config info based on companyId
      @PARAMETERS   : companyId
      @RETURN       : NA
    ****************************************************************************/
  uploadProof: any
  base64Image:any
  getProductInfo() {
    this.commonService.callApi('productconfig', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.productConfigData = successData[0]
      this.base64Image = 'data:image/png;base64,' + this.productConfigData.companyLogo;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /************************************************************************/
  transform() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
  }
}
