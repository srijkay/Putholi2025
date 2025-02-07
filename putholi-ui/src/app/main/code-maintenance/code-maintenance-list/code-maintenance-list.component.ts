import { Component, Injector, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-code-maintenance-list',
  templateUrl: './code-maintenance-list.component.html',
  styleUrls: ['./code-maintenance-list.component.css']
})
export class CodeMaintenanceListComponent extends BaseComponent implements OnInit {
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

  selectCode: any = {
    "codeType": this.getToken('codeType')
  }
  constructor(inj: Injector ) {
    super(inj)
  }

  ngOnInit(): void {
    this.getCodes()
    this.getCodesList(this.data)
  }

  /****************************************************************************
    @PURPOSE      : To retrive List of Countries.
    @PARAMETERS   : data
    @RETURN       : List of Country Details
 ****************************************************************************/
  codesList: any = []
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;

  getCodesList(data) {
    if (this.getToken('codeType')) {
      this.loading = true
      this.ngxLoader.start()
      this.listHeader = this.getToken('description');
      data.codeType = this.getToken('codeType');
      setTimeout(() => {
        this.commonService.callApi('mastercode/search', data, 'post', false, false, 'REG').then(success => {
          let successData: any = success
          this.totalItem = successData.body.totalElements;
          this.number = successData.body.number;
          this.size = successData.body.size;
          this.isSelected = true
          this.loading = false;
          this.ngxLoader.stop()
          this.noofelements = successData.body.numberOfElements;
          this.totalElements = successData.body.totalElements;
          if (successData.body.content.length) {
            this.codesList = successData.body.content;
          } else {
            this.codesList = []
          }

        }).catch(e => {
          this.ngxLoader.stop()
          this.toastr.errorToastr(e.message, 'Oops!')
        });
      }, 1000)
    }
  }
  /****************************************************************************/


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
      this.getCodesList(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getCodesList(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      console.log(e)
      this.filterObj.pageSize = e;
      this.getCodesList(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getCodesList(this.data);
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
        this.toastr.errorToastr("Veuillez sélectionner n'importe quelle valeur de données pour filtrer", 'Oops!');
        return 0;
      }

    }
    this.isFilterApplied = true;
    this.currentPage = 1;
    this.filterObj.pageNumber = 1;
    this.filterObj.pageSize = this.pagesize;
    this.getCodesList(this.filterObj);
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getCodesList(this.data);
  }
  /****************************************************************************/

  /****************************************************************************
       @PURPOSE      : To retreive the data of all Code Types
       @PARAMETERS   : NA
       @RETURN       : Codes Array
    ****************************************************************************/

  codesArray: any = []
  getCodes() {
    this.commonService.callApi('mastercodetype/active', '', 'get', false, false, 'REG').then(success => {
      this.codesArray = success
      console.log(this.codesArray);

      this.codesArray = this.codesArray.masterCodeResultDTOs
      console.log(this.codesArray);
      
    })
  }
  /****************************************************************************/

  /****************************************************************************
       @PURPOSE      : To display the List of selected code
       @PARAMETERS   : Event selected in Dropdwon
       @RETURN       : NA
    ****************************************************************************/
  isSelected: boolean = false
  listHeader: any;

  onChange(data) {
    this.listHeader = data.description;
    this.data.codeType = data.code
    this.codeType = data.code
    this.setToken('codeType', data.code)
    this.setToken('description', this.listHeader)
    this.isSelected = true;
    this.getCodesList(this.data);
  }
  /****************************************************************************/

}
