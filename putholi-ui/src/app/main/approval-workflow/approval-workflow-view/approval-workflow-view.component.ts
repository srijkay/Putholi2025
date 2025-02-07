import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-approval-workflow-view',
  templateUrl: './approval-workflow-view.component.html',
  styleUrls: ['./approval-workflow-view.component.css']
})
export class ApprovalWorkflowViewComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
  }

  public featureManageData: any = {};
  featureId: any

  ngOnInit(): void {
    this.getRoles();

    this.activatedRoute.params.subscribe((params) => {
      {
        this.featureId = params['id'];
        this.getfeatureDetails(this.featureId)
      }
    })
  }

  /****************************************************************************
    @PURPOSE      : Get feature Details by Id
    @PARAMETERS   : id
    @RETURN       : featureManagementDTO
 ****************************************************************************/
  levelNo: any = ''
  approverLevels: any = []
  role: any
  getfeatureDetails(id) {
    this.commonService.callApi('featuremanagement/' + id, '', 'get', false, false, 'REG').then(success => {
      this.featureManageData = success
      this.approverLevels = this.featureManageData.approverLevels
      for (let i = 0; i < this.approverLevels.length; i++) {
        this.approverLevels[i]['approverRoles'] = this.approverLevels[i].approverRoles.replace(/\[|\]/g, '').trim();

        let myFilter = this.approverLevels[i].approverRoles.split(',');
        const myArrayFiltered = this.rolesList.filter(ar => myFilter.some(fl => fl.trim() == ar.code));
        let forString = myArrayFiltered.map(el => el.description)
        this.approverLevels[i]['approverRolesNames'] = forString.join(', ');
      }
      this.role = this.rolesList.find(x => x.code == this.featureManageData.role);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })

  }
  /******************************************************************************************/

}
