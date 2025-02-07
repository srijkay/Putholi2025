import { Component, Injector, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-user-list',
  templateUrl: './manage-user-list.component.html',
  styleUrls: ['./manage-user-list.component.css']
})
export class ManageUserListComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }


  isExportMode = false;
  exportOption = '';
  pageIndex = 1;
  noOfRecordsPerPageCopy = 0;
  totalRecordCount: any;
  manageUserTable: any = "manageUserTable";
  nameofthefile: any = "UserReports";
  Username: any = "Username"
  noOfRecordsPerPage: any = this.getNoOfRecordsPerPageInPagination();
  page: any = 1;
  pageSize = this.getNoOfRecordsPerPageInPagination();

  public modalRef: BsModalRef;
  options: any = {
    backdrop: 'static',
    keyboard: false
  };
  userList: any = []
  public adminStatusList = [
    {
      "name": "Approved",
      "code": "APR"
    },
    {
      "name": "Deletion Initiated By Admin",
      "code": "DELADM"
    },
    {
      "name": "Change Role Initiated By Admin",
      "code": "CHRADM"
    }
  ]

  public reviewStatusList = [

    {
      "name": "Change Role Pending By Reviewer",
      "code": "CHRREV"
    },
    {
      "name": "Deletion Pending By reviewer",
      "code": "DELREV"
    }

  ]

  public approverStatusList = [
    {
      "name": "Change Role Pending By Approver",
      "code": "CHRAPR"
    },
    {
      "name": "Deletion Pending By Approver",
      "code": "DELAPR"
    }
  ]


  rolesDetails: any = []
  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    this.getRoles()

    if (this.getToken('role') == 'ADMIN') {
      this.data.roleCode = ['ADMIN', 'REVIEW', 'APPRV', 'SUUSR', 'SUADM']
      this.data.statusCode = ['APR', 'DELADM', 'CHRADM']

    } else if (this.getToken('role') == 'REVIEW') {
      this.data.statusCode = ['CHRREV', 'DELREV']

    } else if (this.getToken('role') == 'APPRV') {
      this.data.statusCode = ["CHRAPR", 'DELAPR']

    } else if (this.getToken('role') == 'SUUSR') {
      this.data.userRole = ['ADMIN', 'REVIEW', 'APPRV', 'TRUSTM']
      this.data.statusCode = ['APR', 'PENSUS', 'DELSUA']

    } else if (this.getToken('role') == 'SUADM') {
      this.data.userRole = ['ADMIN', 'REVIEW', 'APPRV', 'TRUSTM']
      this.data.statusCode = ['PENSUA', 'DELUSR']
    }
  }

  ngOnInit(): void {
    this.getUserList(this.data)
    setTimeout(() => {
      if (this.getToken('role') == 'ADMIN' || this.getToken('role') == 'REVIEW' || this.getToken('role') == 'APPRV') {
        this.rolesDetails = this.rolesList.filter(x => x.code != 'ADMIN' && x.code != 'REVIEW' && x.code != 'APPRV' && x.code != 'SUUSR' && x.code != 'SUADM')
      } else {
        this.rolesDetails = this.rolesList.filter(x => x.code == 'ADMIN' || x.code == 'REVIEW' || x.code == 'APPRV' || x.code == 'TRUSTM')
      }
    }, 1000)

  }


  /****************************************************************************
      @PURPOSE      : Search Role details
      @PARAMETERS   : pageno. pagesize
      @RETURN       : RoleDetailsDto
 ****************************************************************************/

  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;
  getUserList(data) {
    this.loading = true
    this.ngxLoader.start()
    data.loggedUser = this.getToken('username')
    console.log(data);

    setTimeout(() => {
      this.commonService.callApi('usermgmt/search', data, 'post', false, false, 'REG').then(success => {
        console.log(success);
        let successData: any = success
        console.log(successData.content);
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.userList = successData.body.content;
          this.totalRecordCount = this.userList.length
        } else {
          this.userList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    })
  }

  /*********************************************************************************/

  /****************************************************************************
      @PURPOSE      : filters.
      @PARAMETERS   : NA
      @RETURN       : NA
  ****************************************************************************/
  applyFilter() {
    if (JSON.stringify(this.filterObj) == "{}") {
      if (localStorage.getItem('Language') === 'en') {
        this.toastr.errorToastr("Please Select Any Value Data to Filter", 'Oops!');
        return 0;
      } else {
        this.toastr.errorToastr("வடிகட்டுவதற்கு ஏதேனும் மதிப்புத் தரவைத் தேர்ந்தெடுக்கவும்", 'Oops!');
        return 0;
      }

    }
    this.isFilterApplied = true;
    this.currentPage = 1;
    this.filterObj.pageNumber = 1;
    this.filterObj.pageSize = this.pagesize;
    console.log(this.filterObj);

    if (!this.filterObj.role) {
      this.filterObj.userRole = this.data.userRole
    } else {
      this.filterObj.userRole = [this.filterObj.role]
    }
    if (!this.filterObj.status) {
      this.filterObj.statusCode = this.data.statusCode
    } else {
      this.filterObj.statusCode = [this.filterObj.status]
    }

    this.getUserList(this.filterObj);
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getUserList(this.data);
  }
  /****************************************************************************/

  /****************************************************************************
      @PURPOSE      : To retrive the roles List
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
      this.getUserList(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getUserList(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getUserList(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getUserList(this.data);
    }
  }
  /****************************************************************************/
}
