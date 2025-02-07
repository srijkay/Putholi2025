import { Component, Injector, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-assigned-school-list',
  templateUrl: './assigned-school-list.component.html',
  styleUrls: ['./assigned-school-list.component.css']
})
export class AssignedSchoolListComponent extends BaseComponent implements OnInit {

  schoolInfoList: any = []
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize,
    genericSearchData: this.getToken('username')
  }

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    this.consolidateInfo(this.data)

  }
  ngOnInit(): void {
  }

  /****************************************************************************
       @PURPOSE      : Search Role details
       @PARAMETERS   : pageno. pagesize
       @RETURN       : RoleDetailsDto
  ****************************************************************************/
  public totalItem: any;
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;
  consolidateInfo(data) {
    this.loading = true
    this.ngxLoader.start();
    setTimeout(() => {
      data.consolidateStatusCode = ["ASGVOL"]
      data.active = 'Y'
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
          this.schoolInfoList = successData.body.content;
        } else {
          this.schoolInfoList = []
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
    this.consolidateInfo(this.filterObj);
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
      this.consolidateInfo(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.consolidateInfo(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.consolidateInfo(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.consolidateInfo(this.data);
    }
  }
  /****************************************************************************/
}