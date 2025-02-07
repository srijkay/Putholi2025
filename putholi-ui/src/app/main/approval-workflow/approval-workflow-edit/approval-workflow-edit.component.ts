import { Component, Injector, OnInit, ViewChild } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
import { concat, Observable, of, Subject } from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, switchMap, tap } from 'rxjs/operators';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { NgxUiLoaderService } from 'ngx-ui-loader';
@Component({
  selector: 'app-approval-workflow-edit',
  templateUrl: './approval-workflow-edit.component.html',
  styleUrls: ['./approval-workflow-edit.component.css']
})
export class ApprovalWorkflowEditComponent extends BaseComponent implements OnInit {
  @ViewChild('staticTabs', { static: true }) staticTabs: TabsetComponent;
  public submitted: boolean = false;
  public featureManageData: any = {};
  public featureMgmtId: any
  public isNew: any = false
  public approverLevels: any = [];
  public approverForm: any = {}

  // Status List
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

  //  Filter Type List
  public filterTypeList = [
    {
      "name": "User",
      "code": "USR"
    },
    {
      "name": "Role",
      "code": "ROL"
    },
    {
      "name": "Organisation",
      "code": "ORG"
    },

  ]

  //  Organisation List
  public organisationList = [
    {
      "name": "Newrta",
      "code": "NWR"
    },
    {
      "name": "Tcs",
      "code": "TCS"
    },
    {
      "name": "Wipro",
      "code": "WIP"
    },
    {
      "name": "Capgemini",
      "code": "CPG"
    },
  ]

  //  Auto approval List
  public isAutoApprovalList = [
    {
      "name": "Yes",
      "code": "Y"
    },
    {
      "name": "No",
      "code": "N"
    },


  ]

  //  Is approval applicable List
  public isApprovalApplicableList = [
    {
      "name": "Yes",
      "code": "Y"
    },
    {
      "name": "No",
      "code": "N"
    },


  ]

  // Type List
  public typesList = [
    {
      "name": "Parallel Either",
      "code": "PER"
    },
    {
      "name": "Parallel Both",
      "code": "PAB"
    },
    {
      "name": "Sequence",
      "code": "SEQ"
    },


  ]

  // Level of approval list
  public levelofApprovalList = [
    {
      "name": "Level 1",
      "code": "LE1"
    },
    {
      "name": "Level 2",
      "code": "LE2"
    },
    {
      "name": "Level 3",
      "code": "LE3"
    }, {
      "name": "Level 4",
      "code": "LE4"
    },


  ]

  constructor(inj: Injector) {
    super(inj)
    this.getRoles();
  }

  ngOnInit(): void {
    this.fetchMenu()
    this.fetchUser()
    this.activatedRoute.params.subscribe((params) => {
      if (params['id'] === 'New') {
        this.isNew = true;
      } else {
        this.featureMgmtId = params['id'];
        this.isNew = false;
        this.getFeatureDetails(this.featureMgmtId)
        this.getApproverConfig(this.featureMgmtId);
      }
    })
  }

  selectApplicable(event) {
    if (event.code == "N") {
      this.featureManageData.isAutoApproval = null
      this.featureManageData.levelofApproval = null
    }
  }

  selectApprover(event) {
    if (event.code == "Y") {
      this.featureManageData.levelofApproval = null
    }
  }

  /****************************************************************************
  @PURPOSE      : To Show the filter type roles based upon change event
  @PARAMETERS   : event
  @RETURN       : NA
  ****************************************************************************/
  public filterInfo: any
  filterChange(event) {
    this.filterInfo = event.code
    console.log(this.filterInfo);

    if (this.filterInfo == 'USR') {
      this.featureManageData.role = null;
      this.featureManageData.organisation = null;
    } else if (this.filterInfo == 'ROL') {
      this.featureManageData.userName = null;
      this.featureManageData.organisation = null;
    } else if (this.filterInfo == "ORG") {
      this.featureManageData.userName = null;
      this.featureManageData.role = null;
    }
  }
  /****************************************************************************/
  onPrevious() {
    this.staticTabs.tabs[0].active = true

  }
  /****************************************************************************
    @PURPOSE      : To Add or Update Feature Management Info
    @PARAMETERS   : form, formdata
    @RETURN       : NA
****************************************************************************/
  onFeature(form, featureManageData) {
    if (this.isNew) {
      if (form.valid) {
        console.log(featureManageData);
        featureManageData.createdBy = "admin"
        this.commonService.callApi("featuremanagement", featureManageData, 'post', false, false, 'REG').then(success => {
          let successData: any = success
          if (successData.body.apiStatusCode == "SUCCESS") {
            this.setToken("featureId", successData.body.id)
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            if (featureManageData.levelofApproval != null) {
              this.staticTabs.tabs[1].disabled = false;

              this.staticTabs.tabs[1].active = true;
            }
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch((e) => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })

      }
    } else {
      featureManageData.lastModifiedBy = "admin"
      console.log(featureManageData);
      if (this.approverLevels.length && featureManageData.levelofApproval == null) {
        this.toastr.errorToastr("Please Remove Approval Levels", 'Error')
        // this.staticTabs.tabs[1].disabled = false;
      } else {
        console.log("else");
        this.commonService.callApi("featuremanagement", featureManageData, 'put', false, false, 'REG').then(success => {
          let successData: any = success
          if (successData.body.apiStatusCode == "SUCCESS") {
            this.setToken("featureId", successData.body.id)
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            if (featureManageData.levelofApproval != null) {
              this.getApproverConfig(this.featureMgmtId)
              // this.staticTabs.tabs[1].disabled = false;
              this.staticTabs.tabs[1].active = true;
            } else {
              this.router.navigate(["main/approval-workflow/approval-workflow-list"]);
            }
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch((e) => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }
    }
  }
  /*************************************************************************************/

  /****************************************************************************
    @PURPOSE      : Open popup for ApproverLevels Info
    @PARAMETERS   : template, type, id
    @RETURN       : NA
****************************************************************************/
  modelRef: BsModalRef
  isAdd: boolean
  approverId: any
  openModel(template, event, id) {
    if (event == "ADD") {
      this.isAdd = true
      this.approverForm = {}
    } else {
      this.isAdd = false
      this.approverId = id
      this.getApproverDetails(id)
    }
    this.modelRef = this.modalService.show(template);
  }

  /****************************************************************************/
  /****************************************************************************
    @PURPOSE      : To Add or Update ApproverLevels Info
    @PARAMETERS   : form, formdata
    @RETURN       : NA
  ****************************************************************************/
  onReqEdit(form, approverForm) {
    let id = this.featureMgmtId ? this.featureMgmtId : this.getToken('featureId');
    var arrpvalues: any;
    if (!Array.isArray(approverForm.approverRoles)) {
      arrpvalues = [approverForm.approverRoles]
    } else {
      arrpvalues = approverForm.approverRoles
    }

    if (this.isAdd) {
      if (form.valid) {
        this.approverForm = {
          "approverRoles": arrpvalues,
          "featureManagementDTO": {
            "featureId": id
          },
          "level": approverForm.level,
          "type": approverForm.type
        }
        this.commonService.callApi("approverinfo", this.approverForm, 'post', false, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode == "SUCCESS") {
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            this.getApproverConfig(id);
            this.modelRef.hide();
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch((e) => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }
    } else {

      this.approverForm = {
        "approverId": this.approverId,
        "approverRoles": arrpvalues,
        "featureManagementDTO": {
          "featureId": id
        },
        "level": approverForm.level,
        "type": approverForm.type
      }
      this.commonService.callApi("approverinfo", this.approverForm, 'put', false, false, 'REG').then(success => {
        let successData: any = success;

        // this.approverLevels[i].approverRoles.replace(/\[|\]/g, '').split(',')
        if (successData.body.apiStatusCode == "SUCCESS") {
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          this.getApproverConfig(id);
          this.modelRef.hide();
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch((e) => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }
  }
  /****************************************************************************/

  /****************************************************************************
    @PURPOSE      : To get FeatureManagement INFO
    @PARAMETERS   : id
    @RETURN       : FeatureManagementDTO
  ****************************************************************************/
  getFeatureDetails(id) {
    this.commonService.callApi('featuremanagement/' + id, '', 'get', false, false, 'REG').then(success => {
      this.featureManageData = success
      this.filterInfo = this.featureManageData.filterType
      this.approverLevels = this.featureManageData.approverLevels
      this.getApproverConfig(id)

    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /********************************************************************************/
  /****************************************************************************
   @PURPOSE      : To get ApproverLevels INFO
   @PARAMETERS   : id
   @RETURN       : ApproverLevelsDTO
  ****************************************************************************/
  public array: any = []
  getApproverDetails(id) {
    this.commonService.callApi('approverinfo/' + id, '', 'get', false, false, 'REG').then(success => {
      this.approverForm = success
      console.log(this.approverForm.approverRoles, this.approverForm.approverRoles.length);
      if (this.approverForm.type != 'SEQ') {
        this.approverForm.approverRoles = this.approverForm.approverRoles.replace(/\[|\]/g, '').split(',')
        this.approverForm.approverRoles = this.approverForm.approverRoles.map(function (el) {
          return el.trim();
        });
      } else {
        this.approverForm.approverRoles = this.approverForm.approverRoles.replace(/\[|\]/g, '')
      }
      this.approverRoleName()
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /********************************************************************************/
  /****************************************************************************
     @PURPOSE      : To Delete ApproverLevels based on Id
     @PARAMETERS   : id
     @RETURN       : NA
  ****************************************************************************/
  deleteLevels(id) {
    let featureId = this.featureMgmtId ? this.featureMgmtId : this.getToken('featureId');
    this.commonService.callApi('approverinfo/' + id, '', 'delete', false, false, 'REG').then(success => {
      let successData: any = success
      if (successData.body.apiStatusCode == "SUCCESS") {
        this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        this.getApproverConfig(featureId)
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /******************************************************************************************/
  /****************************************************************************
          @PURPOSE      : Search Approver Levels details
          @PARAMETERS   : pageno. pagesize
          @RETURN       : ApproverLevelsDTO
  ****************************************************************************/
  getApproverConfig(id) {
    this.commonService.callApi('approverinfo/all/' + id, '', 'get', false, false, 'REG').then(success => {
      this.approverLevels = success
      this.approverRoleName()
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /********************************************************************************/
  approverRoleName() {
    for (let i = 0; i < this.approverLevels.length; i++) {
      this.approverLevels[i]['approverRoles'] = this.approverLevels[i].approverRoles.replace(/\[|\]/g, '').trim();
      let myFilter = this.approverLevels[i].approverRoles.split(',');
      const myArrayFiltered = this.rolesList.filter(ar => myFilter.some(fl => fl.trim() == ar.code));
      let forString = myArrayFiltered.map(el => el.description)
      this.approverLevels[i]['approverRolesNames'] = forString.join(', ');
    }
  }
  /**************************************************************************
   @PURPOSE      : To Fetch menu names from menuDetails
   @PARAMETERS   : menuDetails
   @RETURN       : NA
   ****************************************************************************/
  data: any = {};
  menuDetails: any = [];

  fetchMenu() {
    this.data.pageNumber = 1;
    this.data.pageSize = 10;
    this.commonService.callApi('menu/search', this.data, 'post', false, false, 'REG').then(success => {
      this.menuDetails = success;
      this.menuDetails = this.menuDetails.body
      console.log(this.menuDetails);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /**************************************************************************/

  /**************************************************************************
   @PURPOSE      : To Fetch username from userEditData
   @PARAMETERS   : userEditData
   @RETURN       : NA
   ****************************************************************************/
  userEditData: any = [];
  fetchUser() {
    this.data.pageNumber = 1;
    this.data.pageSize = 10;
    this.commonService.callApi('usermgmt/search', this.data, 'post', false, false, 'REG').then(success => {
      this.userEditData = success;
      this.userEditData = this.userEditData.body
      console.log(this.userEditData);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/
}



