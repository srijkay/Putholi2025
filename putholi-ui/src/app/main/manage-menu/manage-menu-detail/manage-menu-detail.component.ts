import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-menu-detail',
  templateUrl: './manage-menu-detail.component.html',
  styleUrls: ['./manage-menu-detail.component.css']
})
export class ManageMenuDetailComponent extends BaseComponent implements OnInit {

  public menuDetails: any = {};
  public isNew: boolean = false;
  public menuId: any;
  submitted: boolean = false;

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


  constructor(inj: Injector) {
    super(inj)
    this.activatedRoute.params.subscribe((params) => {
      if (params['id'] === 'new') {
        this.isNew = true;
      } else {
        this.menuId = params['id']
        this.fetchMenu(this.menuId)
        this.isNew = false
      }
    })
  }

  ngOnInit(): void {

    this.fetchModule()
  }

  /****************************************************************************
   @PURPOSE      : To submit menu details.
   @PARAMETERS   : form,menuDetails
   @RETURN       : NA
****************************************************************************/

  public successData: any;
  submitForm(form, menuDetails) {
    if (this.isNew) {
      if (form.valid) {
        this.menuDetails.createdBy = "admin"
        this.commonService.callApi('menu', menuDetails, 'post', false, false, 'REG').then(success => {
          this.successData = success;
          if (this.successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/menu/menu-list"]);
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
      this.menuDetails.updatedBy = "admin";
      this.menuDetails.menuId = this.menuId;
      this.commonService.callApi('menu', menuDetails, 'put', false, false, 'REG').then(success => {
        this.successData = success;
        if (this.successData.body.apiStatusCode === "SUCCESS") {
          this.router.navigate(["/main/menu/menu-list"]);
          this.toastr.successToastr(this.successData.body.apiStatusDesc, 'Success')
        } else {
          this.toastr.errorToastr(this.successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }
  }
  /**********************************************************************************/



  /****************************************************************************
    @PURPOSE      : Fetch menu details.
    @PARAMETERS   : form,menuDetails
    @RETURN       : MenuManagement object
 ****************************************************************************/
  fetchMenu(id) {
    this.commonService.callApi('menu/' + id, '', 'get', false, false, 'REG').then(success => {
      this.menuDetails = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /******************************************************************************/



  data: any = {};
  moduleDetails: any = [];

  fetchModule() {
    this.data.pageNumber = 1;
    this.data.pageSize = 10;
    this.commonService.callApi('module/search', this.data, 'post', false, false, 'REG').then(success => {
      this.moduleDetails = success;
      this.moduleDetails = this.moduleDetails.body
      console.log(this.moduleDetails);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
}
