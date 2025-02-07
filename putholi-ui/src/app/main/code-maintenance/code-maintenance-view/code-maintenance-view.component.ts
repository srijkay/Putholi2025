import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-code-maintenance-view',
  templateUrl: './code-maintenance-view.component.html',
  styleUrls: ['./code-maintenance-view.component.css']
})
export class CodeMaintenanceViewComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
  }
  public codeMaintenanceData: any = {};
  codeId: any
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
    this.codeId = params['id'];
    this.getCodeById(this.codeId)
    })
  }

  /****************************************************************************
    @PURPOSE      : to get codemaintenace info based on Id
    @PARAMETERS   : Id
    @RETURN       : NA
  ****************************************************************************/
  getCodeById(id) {
    this.commonService.callApi('mastercode/' + id, '', 'get', false, false, 'REG').then(success => {
      console.log(success);
      this.codeMaintenanceData = success;
      console.log(this.codeMaintenanceData);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/


}
