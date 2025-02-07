import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-code-maintenance-edit',
  templateUrl: './code-maintenance-edit.component.html',
  styleUrls: ['./code-maintenance-edit.component.css']
})
export class CodeMaintenanceEditComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)



  }
  public submitted: boolean = false;
  public codeMaintenanceData: any = {};
  isNew: any = false
  codeId: any


  id: any
  ngOnInit(): void {
    this.codeMaintenanceData = {}
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['id']);

      if (params['id'] === 'New') {
        this.isNew = true;
      } else {
        this.codeId = params['id'];
        this.isNew = false

        this.getCodeById(this.codeId)

      }
    })
  }

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


  /****************************************************************************
        @PURPOSE      : Edit code maintenance details
        @PARAMETERS   : form,formdata
        @RETURN       : NA
   ****************************************************************************/


  onCode(form, codeMaintenanceData) {
    if (this.isNew) {
      if (form.valid) {
        codeMaintenanceData.createdBy = "Admin";
        codeMaintenanceData.codeType = this.getToken('codeType')
        this.commonService.callApi('mastercode', codeMaintenanceData, 'post', false, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/code-maintenance"]);
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }

    } else {
      this.codeMaintenanceData.updatedBy = this.getToken('role');
      this.commonService.callApi('mastercode/v1/api/mastercode', codeMaintenanceData, 'put', false, false, 'REG').then(success => {
        let successData: any = success
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.router.navigate(["/main/code-maintenance"]);
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
