import { DatePipe } from '@angular/common';
import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-collection-amount-report',
  templateUrl: './collection-amount-report.component.html',
  styleUrls: ['./collection-amount-report.component.css']
})
export class CollectionAmountReportComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector, public datepipe: DatePipe) {
    super(inj)
    this.getCountrList()
  }

  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }

  ngOnInit(): void {
    this.getCollectionAmountList(this.data)
  }


  minDate: any = []

  colorTheme = 'theme-blue';
  datePickerConfig = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
  }

  todatePicker = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
    minDate: new Date(),
    // showWeekNumbers: false,
  }


  isExportMode = false;
  exportOption = '';
  pageIndex = 1;
  noOfRecordsPerPageCopy = 0;
  totalRecordCount: any;
  collectionAmountTable = "collectionAmountTable"
  nameofthefile = "CollectionAmountReport"
  noOfRecordsPerPage: any = this.getNoOfRecordsPerPageInPagination();
  page: any = 1;
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
  collectionList: any = []
  public totalItem: any

  getCollectionAmountList(data) {
    this.loading = true
    this.ngxLoader.start()
    data.feeType = 'INC'
    setTimeout(() => {
      this.commonService.callApi('userreports/search', data, 'post', false, false, 'REG').then(success => {
        let successData: any = success
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.collectionList = successData.body.content;
          this.totalRecordCount = this.collectionList.length

          let type = this.countryList.filter(o1 => this.collectionList.some(o2 => o1.code === o2.country))

          this.collectionList.forEach(function (checkbox) {
            type.forEach(e => {
              if (e.code == checkbox.country)
                checkbox.country = e.description
            });
          })
        } else {
          this.collectionList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    })
  }

  /****************************************************************************
    @PURPOSE      : filters for users.
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
    console.log(this.isFrom, this.isToDate);

    if (this.isFrom && this.isToDate) {
      this.isFilterApplied = true;
      this.currentPage = 1;
      this.filterObj.pageNumber = 1;
      this.filterObj.pageSize = this.pagesize;
      this.getCollectionAmountList(this.filterObj);
    }

  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getCollectionAmountList(this.data);
  }

  /**************************************************************************** */

  /****************************************************************************
      @PURPOSE      : paginations for users.
      @PARAMETERS   : NA
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
      this.getCollectionAmountList(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getCollectionAmountList(this.data);
    }
  }


  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getCollectionAmountList(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getCollectionAmountList(this.data);
    }
  }

  /***************************************************************************/

  public isFrom: boolean = true
  public isToDate: boolean = true
  selectDate(e) {

    this.todatePicker.minDate = e
    if (e > new Date()) {
      this.isFrom = false
      this.toastr.errorToastr("From date cannot be in the future", "Error")
    } else {
      this.isFrom = true
      this.selectToDate(this.filterObj.todate, e)
    }
  }

  selectToDate(e, fromDate) {
    if (fromDate == null || fromDate == undefined) {
      this.isFrom = false
      this.toastr.errorToastr("From date cannot be null", "Error")
    } else {
      let fromDatee = this.datepipe.transform(fromDate, "yyyy-MM-dd")
      let todate = this.datepipe.transform(e, "yyyy-MM-dd")
      if (todate < fromDatee) {
        this.toastr.errorToastr("From date cannot be greater than the To date", "Error")
        this.isToDate = false
      }
      else if (e > new Date()) {
        this.toastr.errorToastr("To date cannot be in the future", "Error")
        this.isToDate = false
      } else if (e == null || e == undefined) {
        this.isToDate = false
      } else {
        this.isToDate = true
      }

    }
  }
}
