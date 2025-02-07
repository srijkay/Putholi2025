import { Component, Injector, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-application-configuration-list',
  templateUrl: './application-configuration-list.component.html',
  styleUrls: ['./application-configuration-list.component.css']
})
export class ApplicationConfigurationListComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }

  constructor(inj: Injector) {
    super(inj);
  }

  ngOnInit(): void {
    this.getApplicationConfig(this.data);
  }

  /****************************************************************************
          @PURPOSE      : Search Application Configuration details
          @PARAMETERS   : pageno. pagesize
          @RETURN       : ApplicationConfigDTO
   ****************************************************************************/
  public configList = [];
  public loading: boolean = false;
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  getApplicationConfig(data) {
    this.loading = true
    this.ngxLoader.start();
    setTimeout(() => {
      this.commonService.callApi('config/search', data, 'post', false, false, 'REG').then(success => {
        let successData: any = success
        console.log(success);
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.configList = successData.body.content;
          console.log(this.configList);
        } else {
          this.configList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)
  }
  /*****************************************************************************/
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
      this.getApplicationConfig(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getApplicationConfig(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getApplicationConfig(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getApplicationConfig(this.data);
    }
  }
  /****************************************************************************/
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
    console.log(this.filterObj);
    this.getApplicationConfig(this.filterObj);
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getApplicationConfig(this.data);
  }
  /****************************************************************************/
}