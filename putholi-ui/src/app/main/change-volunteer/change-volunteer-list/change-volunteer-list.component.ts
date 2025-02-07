import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-change-volunteer-list',
  templateUrl: './change-volunteer-list.component.html',
  styleUrls: ['./change-volunteer-list.component.css']
})
export class ChangeVolunteerListComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }
  constructor(inj: Injector) {
    super(inj)
  }
  ngOnInit(): void {
    this.getSchoolList(this.data)
  }

  /****************************************************************************
        @PURPOSE      : Search Role details
        @PARAMETERS   : pageno. pagesize
        @RETURN       : RoleDetailsDto
  ****************************************************************************/
  schoolList: any = []
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;
  getSchoolList(data) {
    this.loading = true
    this.ngxLoader.start();
    setTimeout(() => {
      // data.status = "Assigned Volunteer"
      data.active = 'Y'
      data.consolidateStatusCode= ['ASGVOL','VOLACP','QUOARV', 'ADMQUO', 'REVQUO', 'APRQUO', 'REDALL', 'GNRORD', 'ORDINI', 'PARAPR', "ADMINV", 'INVAPR', 'INVREV','PARPAY', 'PAYINI', 'PARPAY', 'PARREJ', 'PARREC', 'ADMREC', 'RECREJ', 'APRREC', 'PROCES']
      this.commonService.callApi('consolidate/search', data, 'post', false, false, 'REG').then(success => {
        console.log(success);
        let successData: any = success
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.schoolList = successData.body.content;
          console.log(this.schoolList);

        } else {
          this.schoolList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)
  }
  /*********************************************************************************/
  /****************************************************************************
        @PURPOSE      : filters.
        @PARAMETERS   : NA
        @RETURN       : NA
 ****************************************************************************/
  applyFilter() {
    this.isFilterApplied = true;
    this.currentPage = 1;
    this.filterObj.pageNumber = 1;
    this.filterObj.pageSize = this.pagesize;
    this.getSchoolList(this.filterObj);
  }
  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getSchoolList(this.data);
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
      this.getSchoolList(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getSchoolList(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getSchoolList(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getSchoolList(this.data);
    }
  }
  /****************************************************************************/
  onClick(schoolid) {
    console.log(schoolid);
    this.router.navigate(['/main/change-volunteer/assign-change-volunteer/', schoolid])
  }
}