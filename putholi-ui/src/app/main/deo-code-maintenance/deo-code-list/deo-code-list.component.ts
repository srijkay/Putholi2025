import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-deo-code-list',
  templateUrl: './deo-code-list.component.html',
  styleUrls: ['./deo-code-list.component.css']
})
export class DeoCodeListComponent extends BaseComponent implements OnInit {

  public maintenanceData: any = {};
  public totalItem: any;
  pagesize: number = 10
  public codeType: any;

  public data: any = {
    pageNumber: 1,
    pageSize: this.pagesize,
  }

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


  deoSelectCode: any = {
    "district": this.getToken('district'),
    "city": this.getToken('city')

  }
  constructor(inj: Injector) {
    super(inj)
  }

  ngOnInit(): void {
    this.getListofDistrict()
    this.getDeoCodeList(this.data)
    this.getDeoCodes()
  }


  /****************************************************************************
    @PURPOSE      : To retrive the codes List
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
      this.getDeoCodeList(this.filterObj)
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getDeoCodeList(this.data)
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      console.log(e)
      this.filterObj.pageSize = e;
      this.getDeoCodeList(this.filterObj)
    } else {
      this.data.pageSize = e;
      this.getDeoCodeList(this.data)
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
    this.getDeoCodeList(this.filterObj)
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getDeoCodeList(this.data)
  }
  /****************************************************************************/

  /****************************************************************************
   @PURPOSE      : To retrive List of Countries.
   @PARAMETERS   : data
   @RETURN       : List of Country Details
****************************************************************************/
  deoCodesList: any = []
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;


  getDeoCodeList(data) {
    this.loading = true
    this.ngxLoader.start()
    setTimeout(() => {
      this.commonService.callApi('deomastercode/search', data, 'post', false, false, 'REG').then(success => {
        let successData: any = success
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop()
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.deoCodesList = successData.body.content;
          console.log(this.deoCodesList);

        } else {
          this.deoCodesList = []
        }

      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)

  }
  /****************************************************************************/


  /****************************************************************************
      @PURPOSE      : To retreive the data of all Code Types
      @PARAMETERS   : NA
      @RETURN       : Codes Array
  ****************************************************************************/

  DeoCodesArray: any = []
  getDeoCodes() {
    this.commonService.callApi('deomastercode/active', '', 'get', false, false, 'REG').then(success => {
      this.DeoCodesArray = success
      console.log(this.DeoCodesArray);

      this.DeoCodesArray = this.DeoCodesArray.deoMasterCodeResultDTO
    })
  }
  /****************************************************************************/

}
