import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-application-configuration-view',
  templateUrl: './application-configuration-view.component.html',
  styleUrls: ['./application-configuration-view.component.css']
})
export class ApplicationConfigurationViewComponent extends BaseComponent implements OnInit {

  applConfigData: any = {};
  applConfigId: any

  constructor(inj: Injector) {
    super(inj);
    this.activatedRoute.params.subscribe((params) => {
      this.applConfigId = params['id']
      this.getConfigId(this.applConfigId)
    })
  }

  ngOnInit(): void {
  }

 /****************************************************************************
   @PURPOSE      : to get configuration info based on configId
   @PARAMETERS   : configId
   @RETURN       : NA
 ****************************************************************************/
  getConfigId(configId) {
    this.commonService.callApi('config/' + configId, '', 'get', false, false, 'REG').then(success => {
      this.applConfigData = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/
}