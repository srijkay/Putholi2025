import { Component, Injector, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-announcement-list',
  templateUrl: './announcement-list.component.html',
  styleUrls: ['./announcement-list.component.css']
})
export class AnnouncementListComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }
  constructor(inj: Injector) {
    super(inj);
    this.getRoles();
  }

  ngOnInit(): void {
    this.getAnnouncementList(this.data)
  }

  /****************************************************************************
        @PURPOSE      : Search Announcement details
        @PARAMETERS   : pageno. pagesize
        @RETURN       : AnnouncementDTO
 ****************************************************************************/
  public announcementList = [];
  public roleDesc: any = [];
  public loading: boolean = false;
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  getAnnouncementList(data) {
    this.loading = true
    this.ngxLoader.start();
    setTimeout(() => {
      this.commonService.callApi("announcement/search", data, "post", false, false, 'REG').then(success => {
        let successData: any = success
        // let role: any = this.rolesList
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.announcementList = successData.body.content;
        } else {
          this.announcementList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }, 1000)
  }
  /********************************************************************************/

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
      this.getAnnouncementList(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getAnnouncementList(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getAnnouncementList(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getAnnouncementList(this.data);
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
    this.getAnnouncementList(this.filterObj);
  }
  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getAnnouncementList(this.data);
  }
  /******************************************************************************/
}