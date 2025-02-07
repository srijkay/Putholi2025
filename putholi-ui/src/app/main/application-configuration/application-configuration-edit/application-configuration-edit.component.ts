import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-application-configuration-edit',
  templateUrl: './application-configuration-edit.component.html',
  styleUrls: ['./application-configuration-edit.component.css']
})
export class ApplicationConfigurationEditComponent extends BaseComponent implements OnInit {

  public submitted: boolean = false;
  public applConfigData: any = {};
  applId: any
  isNew: any = false

  constructor(inj: Injector) {
    super(inj)
  }

  ngOnInit(): void {
    this.applConfigData = {}
    this.activatedRoute.params.subscribe((params) => {
      if (params['id'] === 'New') {
        this.isNew = true;
      } else {
        this.applId = params['id'];
        this.isNew = false;
        this.getConfigId(this.applId);
      }
    })
  }

  //ModuleList
 


  /****************************************************************************
    @PURPOSE      : To Submit Application Configuration details
    @PARAMETERS   : form,formdata
    @RETURN       : NA
 ****************************************************************************/
  onApplication(form, applConfigData) {
    console.log("form.valid", applConfigData);
    if (this.isNew) {
      if (form.valid) {
        applConfigData.module=""
        applConfigData.createdBy = "admin"
        this.commonService.callApi('config', applConfigData, 'post', false, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/appl-config"]);
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }
    } else {
      applConfigData.lastModifiedBy = "admin"
      this.commonService.callApi('config', applConfigData, 'put', false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.router.navigate(["/main/appl-config"]);
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }
  }
  /*****************************************************************************/

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