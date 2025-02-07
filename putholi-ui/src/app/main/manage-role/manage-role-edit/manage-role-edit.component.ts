import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-role-edit',
  templateUrl: './manage-role-edit.component.html',
  styleUrls: ['./manage-role-edit.component.css']
})
export class ManageRoleEditComponent extends BaseComponent implements OnInit {

  // statusList
  public statusList = [
    {
      "name": "Active",
      "code": "Y"
    },
    {
      "name": "Inactive",
      "code": "N"
    },
  ]


  constructor(inj: Injector) {
    super(inj)
  }


  public submitted: boolean = false;
  public roleEditData: any = {};
  roleId: any
  isNew: any = false

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['id']);

      if (params['id'] === 'New') {
        this.isNew = true;
      } else {
        this.roleId = params['id'];
        this.getRoleById(this.roleId)
        this.isNew = false




      }
    })
  }



  /****************************************************************************
     @PURPOSE      : to submit Role details
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ****************************************************************************/
  public roleDetails: any;
  onRole(form, roleEditData) {
    if (this.isNew) {
      if (form.valid) {
        this.roleEditData.createdBy = this.getToken('role')

        console.log(roleEditData);

        this.commonService.callApi('role', roleEditData, 'post', false, false, 'REG').then(sucess => {
          let successData: any = sucess
          if (successData.body.apiStatusCode === "SUCCESS") {
            console.log("success");
            this.router.navigate(["/main/role-mgnt"]);
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })

      }
    } else {
      this.roleEditData.updatedBy = this.getToken('role')
      this.commonService.callApi('role', roleEditData, 'put', false, false, "REG").then(success => {
        let successData: any = success
        if (successData.body.apiStatusCode === "SUCCESS") {
          console.log("success");

          this.router.navigate(["/main/role-mgnt"]);
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        } else {
          console.log("error");

          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }
  }
  /****************************************************************************/

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
