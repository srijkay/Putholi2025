import { DatePipe } from '@angular/common';
import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-deleted-reports',
  templateUrl: './deleted-reports.component.html',
  styleUrls: ['./deleted-reports.component.css']
})
export class DeletedReportsComponent extends BaseComponent implements OnInit {
  public totalItem: any;
  pagesize: number = 10
  pageSize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }

  search: any = {
    pageNumber: 1,
    pageSize: this.pageSize
  }

  constructor(inj: Injector, public datepipe: DatePipe) {
    super(inj)

    this.data.statusCode = ['REJ', 'DEL']

    this.getListofDistrict()
    this.getRoles()
    this.getSchoolTypeList()
  }

  public statusList = [
    {
      "name": "Deleted",
      "code": "DEL"
    },
    {
      "name": "Rejected",
      "code": "REJ"
    },

  ]

  ngOnInit(): void {
    this.getUserList(this.data)
    this.getSchoolList(this.search)
  }


  isExportMode = false;
  exportOption = '';
  pageIndex = 1;
  noOfRecordsPerPageCopy = 0;
  totalRecordCount: any;
  deleteUserTable = "deleteUserTable"
  nameofthefile = "deleteUserReport"
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
  userList: any = []

  getUserList(data) {
    this.loading = true
    this.ngxLoader.start()
    setTimeout(() => {
      this.commonService.callApi('usermgmt/search', data, 'post', false, false, 'REG').then(success => {
        let successData: any = success
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.userList = successData.body.content;
          let type = this.DistrictList.filter(o1 => this.userList.some(o2 => o1.code === o2.district))

          this.userList.forEach(function (checkbox) {
            type.forEach(e => {
              if (e.code == checkbox.district)
                checkbox.district = e.description
            });
          })


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

    if (this.isFrom && this.isToDate) {
      this.isFilterApplied = true;
      this.currentPage = 1;
      this.filterObj.pageNumber = 1;
      this.filterObj.pageSize = this.pagesize;
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
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getUserList(this.data);
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

  /***************************************************************************/





  totalRecordCounts: any;
  deleteSchoolTable = "deleteSchoolTable"
  nameofthefilee = "deleteShoolReport"
  noOfRecordsPerPages: any = this.getNoOfRecordsPerPageInPagination();
  pages: any = 1;
  /****************************************************************************
       @PURPOSE      : Search school details
       @PARAMETERS   : pageno. pagesize
       @RETURN       : RoleDetailsDto
 ****************************************************************************/

  schoolList: any = []

  public schoolNumber: 0;
  public schoolSize: 0;
  public noofSchoolElements: 0;
  public totalSchoolElements: 0;
  public totalSchoolItem: any;

  getSchoolList(search) {
    this.loading = true
    search.schoolStatusCode = ['DEL', 'REJ']
    this.ngxLoader.start();
    setTimeout(() => {
      this.commonService.callApi('schoolinfo/search', search, 'post', false, false, 'REG').then(success => {
        let successData: any = success
        this.totalSchoolItem = successData.body.totalElements;
        this.schoolNumber = successData.body.number;
        this.schoolSize = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofSchoolElements = successData.body.numberOfElements;
        this.totalSchoolElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.schoolList = successData.body.content;

          let type = this.DistrictList.filter(o1 => this.schoolList.some(o2 => o1.code === o2.district))

          this.schoolList.forEach(function (checkbox) {
            type.forEach(e => {
              if (e.code == checkbox.district)
                checkbox.district = e.description
            });
          })

          this.totalRecordCounts = this.schoolList.length
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
     @PURPOSE      : filters for School.
     @PARAMETERS   : NA
     @RETURN       : NA
 ****************************************************************************/

  applySchoolFilter() {
    if (JSON.stringify(this.schoolFilter) == "{}") {
      if (localStorage.getItem('Language') === 'en') {
        this.toastr.errorToastr("Please Select Any Value Data to Filter", 'Oops!');
        return 0;
      } else {
        this.toastr.errorToastr("வடிகட்டுவதற்கு ஏதேனும் மதிப்புத் தரவைத் தேர்ந்தெடுக்கவும்", 'Oops!');
        return 0;
      }

    }
    console.log(this.isSchoolFrom, this.isSchoolToDate);

    if (this.isSchoolFrom && this.isSchoolToDate) {
      this.isSchoolFilter = true;
      this.schoolListPage = 1;
      this.schoolFilter.pageNumber = 1;
      this.schoolFilter.pageSize = this.pageSize;
      this.getSchoolList(this.schoolFilter)
    }
  }
  resetSchoolFilter() {
    this.isSchoolFilter = false;
    this.schoolListPage = 1;
    this.schoolFilter = {};
    this.getSchoolList(this.search)
  }

  /****************************************************************************/
  /****************************************************************************
   @PURPOSE      : Pagination for School.
   @PARAMETERS   : PageNumber, page size
   @RETURN       : NA
 ****************************************************************************/


  public schoolListPage = 1;
  public showSchoolLinks = true;
  public schoolRange = [1, 10, 25, 100];
  public isSchoolFilter: boolean = false;
  public schoolFilter: any = {}
  schoolPageChanged(event) {
    if (this.isSchoolFilter) {
      this.schoolFilter.pageNumber = event.page;
      this.schoolFilter.pageSize = event.itemsPerPage;
      this.getSchoolList(this.schoolFilter)
    } else {
      this.search.pageNumber = event.page;
      this.search.pageSize = event.itemsPerPage;
      this.getSchoolList(this.search)
    }
  }
  schoolRangeChanged(event) {
    if (this.isSchoolFilter) {
      this.schoolFilter.pageSize = event;
      this.getSchoolList(this.schoolFilter)
    } else {
      this.search.pageSize = event;
      this.getSchoolList(this.search)
    }
  }
  /****************************************************************************/
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
    showWeekNumbers: false,
  }

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



  minimumDate: any = []
  SchooldatePickerConfig = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
  }

  SchooltodatePicker = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
    minDate: new Date(),
    showWeekNumbers: false,
  }


  public isSchoolFrom: boolean = true
  public isSchoolToDate: boolean = true

  chooseDate(e) {

    this.SchooltodatePicker.minDate = e
    if (e > new Date()) {
      this.isSchoolFrom = false
      this.toastr.errorToastr("From date cannot be in the future", "Error")
    } else {
      this.isSchoolFrom = true
      this.chooseToDate(this.schoolFilter.todate, e)
    }
  }

  chooseToDate(e, fromDatee) {

    if (fromDatee == null || fromDatee == undefined) {
      this.isSchoolFrom = false
      this.toastr.errorToastr("From date cannot be null", "Error")
    } else {
      let fromDate = this.datepipe.transform(fromDatee, "yyyy-MM-dd")
      let todate = this.datepipe.transform(e, "yyyy-MM-dd")
      if (todate < fromDate) {
        this.toastr.errorToastr("From date cannot be greater than the To date", "Error")
        this.isSchoolToDate = false
      }
      else if (e > new Date()) {
        this.toastr.errorToastr("To date cannot be in the future", "Error")
        this.isSchoolToDate = false
      } else if (e == null || e == undefined) {
        this.isSchoolToDate = false
      } else {
        this.isSchoolToDate = true
      }
    }
  }
}
