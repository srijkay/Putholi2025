import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-deo-code-edit',
  templateUrl: './deo-code-edit.component.html',
  styleUrls: ['./deo-code-edit.component.css']
})
export class DeoCodeEditComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
    this.getListofDistrict()
  }

  public submitted: boolean = false;
  public deoCodeData: any = {};
  isNew: any = false
  deoCodeId: any



  id: any
  ngOnInit(): void {
    this.deoCodeData = {}
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['id']);

      if (params['id'] === 'New') {
        this.isNew = true;

      } else {
        this.id = params['id'];
        this.isNew = false
        this.getCodeById(this.id)
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
       @PURPOSE      : Edit deo code maintenance details
       @PARAMETERS   : form,formdata
       @RETURN       : NA
  ****************************************************************************/


  onDeoCode(form, deoCodeData) {
    if (this.isNew) {
      if (form.valid) {
        deoCodeData.createdBy = "Admin";
        this.commonService.callApi('deomastercode', deoCodeData, 'post', false, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/deo-code-maintenance"]);
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }

    } else {
      this.deoCodeData.updatedBy = this.getToken('role');
      this.commonService.callApi('deomastercode/v1/api/deomaster', deoCodeData, 'put', false, false, 'REG').then(success => {
        let successData: any = success
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.router.navigate(["/main/deo-code-maintenance"]);
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
      @PURPOSE      : to get deo codemaintenace info based on Id
      @PARAMETERS   : Id
      @RETURN       : NA
    ****************************************************************************/
  getCodeById(id) {
    this.commonService.callApi('deomastercode/' + id, '', 'get', false, false, 'REG').then(success => {
      this.deoCodeData = success;
      console.log(this.deoCodeData);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/
}