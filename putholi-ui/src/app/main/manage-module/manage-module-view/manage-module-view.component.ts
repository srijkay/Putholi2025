import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-module-view',
  templateUrl: './manage-module-view.component.html',
  styleUrls: ['./manage-module-view.component.css']
})
export class ManageModuleViewComponent extends BaseComponent implements OnInit {

  public moduleId: any;
  public moduleDetails: any = {};

  constructor(inj: Injector) {
    super(inj)
    this.activatedRoute.params.subscribe((params) => {
      this.moduleId = params['id']
      this.fetchModule(this.moduleId)
    })

  }

  ngOnInit(): void {
  }
  
  /****************************************************************************
      @PURPOSE      : fetch Module details.
      @PARAMETERS   : form,formdata
      @RETURN       : Module Management object
   ****************************************************************************/
  fetchModule(id) {
    this.commonService.callApi('module/' + id, '', 'get', false, false, 'REG').then(success => {
      this.moduleDetails = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /********************************************************************************/

}
