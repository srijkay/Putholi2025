import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-role-view',
  templateUrl: './manage-role-view.component.html',
  styleUrls: ['./manage-role-view.component.css']
})
export class ManageRoleViewComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
  }
  public roleEditData: any = {};
  roleId: any
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      {
        this.roleId = params['id'];
        this.getRoleById(this.roleId)
      }
    })
  }


  /****************************************************************************
    @PURPOSE      : to get role info based on roleId
    @PARAMETERS   : rolelId
    @RETURN       : NA
  ****************************************************************************/
  getRoleById(roleId) {
    this.commonService.callApi('role/' + roleId, '', 'get', false, false, 'REG').then(success => {
      console.log(success);
      this.roleEditData = success;
      console.log(this.roleEditData);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/

}
