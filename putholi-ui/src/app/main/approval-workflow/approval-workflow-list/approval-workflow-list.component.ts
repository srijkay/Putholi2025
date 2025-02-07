import { Component, Injector, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-approval-workflow-list',
  templateUrl: './approval-workflow-list.component.html',
  styleUrls: ['./approval-workflow-list.component.css']
})
export class ApprovalWorkflowListComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }

  constructor(inj: Injector ) {
    super(inj)
  }

  ngOnInit(): void {

    this.getfeatureManagementDTO(this.data)
  }

  // Module List
  public modulesList = [
    {
      "name": "Manage School",
      "code": "MNS"
    },
    {
      "name": "Manage Beneficiary",
      "code": "MNB"
    },
    {
      "name": "Manage Requirement",
      "code": "MRQ"
    },
    {
      "name": "Pending Workflow",
      "code": "PWF"
    },
    {
      "name": "Review Invoice",
      "code": "REV"
    },
    {
      "name": "Change Volunteer",
      "code": "CNV"
    },
    {
      "name": "Manage User",
      "code": "MNC"
    },
    {
      "name": "Manage Role",
      "code": "MNR"
    },
    {
      "name": "Code Maintenance",
      "code": "CEM"
    },
    {
      "name": "Product Configuration",
      "code": "PCN"
    },
    {
      "name": "Application Configuration",
      "code": "APC"
    },
    {
      "name": "Announcement",
      "code": "ANN"
    },
    {
      "name": "Email Settings",
      "code": "EMS"
    },
  ]


  /****************************************************************************
        @PURPOSE      : Search Module List details
        @PARAMETERS   : pageno. pagesize
        @RETURN       : FeatureManagementDTO
 ****************************************************************************/
  public moduleList = [];
  public roleDesc: any = [];
  public loading: boolean = false;
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  getfeatureManagementDTO(data) {

    this.loading = true
    this.ngxLoader.start();
    setTimeout(() => {
      this.commonService.callApi("featuremanagement/search", data, "post", false, false, 'REG').then(success => {
        let successData: any = success
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.moduleList = successData.body.content;
        } else {
          this.moduleList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }, 1000)

  }
  /********************************************************************************/

  /****************************************************************************
     @PURPOSE      : To retrive the modules List
     @PARAMETERS   : pageNumber,PageSize,loggedinuser
     @RETURN       : NA
  ****************************************************************************/
  public currentPage = 1;
  public showBoundaryLinks = true;
  public rangeList = [5, 10, 25, 100];
  public isFilterApplied: boolean = false;
  public filterObj: any = {};

  pageChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageNumber = e.page;
      this.filterObj.pageSize = e.itemsPerPage;
      this.getfeatureManagementDTO(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getfeatureManagementDTO(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getfeatureManagementDTO(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getfeatureManagementDTO(this.data);
    }
  }
  /****************************************************************************/


  /****************************************************************************
      @PURPOSE      : filters.
      @PARAMETERS   : NA
      @RETURN       : NA
  ****************************************************************************/

  applyFilter() {

    if (JSON.stringify(this.filterObj) == "{}") {
      if (localStorage.getItem('Language') === 'en') {
        this.toastr.errorToastr("Please Select Any Value  Data to Filter", 'Oops!');
        return 0;
      } else {
        this.toastr.errorToastr("Please Select Any Value  Data to Filter-tm", 'Oops!');
        return 0;
      }

    }
    this.isFilterApplied = true;
    this.currentPage = 1;
    this.filterObj.pageNumber = 1;
    this.filterObj.pageSize = this.pagesize;
    console.log(this.filterObj);

    this.getfeatureManagementDTO(this.filterObj);
  }
  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getfeatureManagementDTO(this.data);
  }

  /******************************************************************************/

}
