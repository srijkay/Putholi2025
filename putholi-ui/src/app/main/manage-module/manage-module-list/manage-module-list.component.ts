import { Component, Injector, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-module-list',
  templateUrl: './manage-module-list.component.html',
  styleUrls: ['./manage-module-list.component.css']
})
export class ManageModuleListComponent extends BaseComponent implements OnInit {

  public pagesize = 10;
  public totalItem: any;
  public data: any = {
    pageNumber: 1,
    pageSize: this.pagesize,
  }

  moduleDetails: any = [];
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

  constructor(inj: Injector ) {
    super(inj)
  }

  ngOnInit(): void {
    this.getModuleDetails(this.data)
  }

  refreshPage() {
    this.getModuleDetails(this.data)
  }

  /****************************************************************************
         @PURPOSE      : To retrive the menu List
         @PARAMETERS   : pageNumber,PageSize
         @RETURN       : systemFAQSearchDTO object
      ****************************************************************************/
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean;


  getModuleDetails(data) {
    this.loading = true;
    this.ngxLoader.start();

    setTimeout(() => {
      this.commonService.callApi('module/search', data, 'post', false, false, 'REG').then(success => {
        let successData: any = success;
        this.totalItem = successData.totalElements;
        this.number = successData.number;
        this.size = successData.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.numberOfElements;
        this.totalElements = successData.totalElements;

        if (successData.body.content.length) {
          this.moduleDetails = successData.body.content          
        }else{
          this.moduleDetails=[]
        }

      }).catch(e => {
        this.ngxLoader.stop();
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000);
  }

  /******************************************************************************/



  /****************************************************************************
      @PURPOSE      : To retrive the Module List
      @PARAMETERS   : pageNumber,PageSize,loggedinuse
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
      this.getModuleDetails(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getModuleDetails(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
    } else {
      this.data.pageSize = e;
    }
  }
  /*******************************************************************************/

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
    this.getModuleDetails(this.filterObj);

  }
  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getModuleDetails(this.data);
  }

}
