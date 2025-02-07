import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-module-detail',
  templateUrl: './manage-module-detail.component.html',
  styleUrls: ['./manage-module-detail.component.css']
})
export class ManageModuleDetailComponent extends BaseComponent implements OnInit {

  public moduleDetails: any = {};
  public isNew: boolean = false
  public submitted: boolean = false;
  public moduleId: any;
  public statusDetails = [
    {
      'code': 'Y',
      'name': "Active"
    },
    {
      'code': 'N',
      'name': "Inactive"
    }
  ]
  successData: any;

  constructor(inj: Injector) {
    super(inj);


    this.activatedRoute.params.subscribe((params) => {
      if (params['id'] === 'new') {
        this.isNew = true
      } else {
        this.moduleId = params['id']
        this.fetchModule(this.moduleId)
        this.isNew = false
      }
    })
  }

  ngOnInit(): void {
  }

  /****************************************************************************
   @PURPOSE      : To submit Module details.
   @PARAMETERS   : form,formdata
   @RETURN       : NA
****************************************************************************/
  submitForm(form, moduleDetails) {
    if (this.isNew) {
      if (form.valid) {
        this.moduleDetails.createdBy = "admin";
        this.commonService.callApi('module', moduleDetails, 'post', false, false, "REG").then(success => {
          this.successData = success
          if (this.successData.body.apiStatusCode === 'SUCCESS') {
            this.router.navigate(['/main/module/module-list']);
            this.toastr.successToastr(this.successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.errorToastr(this.successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        });

      } else {
        this.submitted = true;
      }
    } else {
      this.moduleDetails.moduleId = this.moduleId
      this.moduleDetails.updatedBy = "admin";
      this.commonService.callApi('module', moduleDetails, 'put', false, false, 'REG').then(success => {
        this.successData = success
        console.log(this.successData);
        if (this.successData.body.apiStatusCode === 'SUCCESS') {
          this.router.navigate(['/main/module/module-list']);
          this.toastr.successToastr(this.successData.body.apiStatusDesc, 'Success')
        } else {
          this.toastr.errorToastr(this.successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });

    }

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
  /*********************************************************************************/
}
