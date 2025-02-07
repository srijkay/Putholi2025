import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-search-requirements',
  templateUrl: './search-requirements.component.html',
  styleUrls: ['./search-requirements.component.css']
})
export class SearchRequirementsComponent extends BaseComponent implements OnInit {

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
  searchRequirementsTable: any = "searchRequirementsTable";
  nameofthefile: any = "RequirementsReports";
  noOfRecordsPerPage: any = this.getNoOfRecordsPerPageInPagination();
  page: any = 1;
  pageSize = this.getNoOfRecordsPerPageInPagination();


  dateList = [
    { "name": "1 Month", "code": "1 Month" },
    { "name": "3 Months", "code": "3 Months" },
    { "name": "6 Months", "code": "6 Months" },
    { "name": "1 Year", "code": "1 Year" }
  ]
  constructor(inj: Injector) {
    super(inj)
    this.getListofDistrict()
    setTimeout(()=>{
      this.getStatusList()

    },500)
    this.data.reqStatus = ["APR"]
    this.isFilterApplied = true;
    this.getRequirementList(this.data)
  }
  reqStatusList: any = []
  ngOnInit(): void {
    setTimeout(() => {
      this.reqStatusList = this.statusList.filter(e => e.code != 'PENADM' && e.code != "PENREV" && e.code != "PENARP")
    }, 1000)

    this.filterObj.status = "APR"
  }

  /****************************************************************************
          @PURPOSE      : Search Role details
          @PARAMETERS   : pageno. pagesize
          @RETURN       : RoleDetailsDto
     ****************************************************************************/
  requirementList: any = []
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;
  getRequirementList(data) {
    this.loading = true
    this.ngxLoader.start();

    if (this.getToken('role') == 'BENIF') {
      data.createdBy = this.getToken('username')
    }
    data.active = 'Y'
    console.log(data);

    setTimeout(() => {
      //data.reqStatus= ['ASGVOL','VOLACP','QUOARV', 'ADMQUO', 'REVQUO', 'APRQUO', 'REDALL', 'GNRORD', 'ORDINI', 'PARAPR', "ADMINV", 'INVAPR', 'INVREV','PARPAY', 'PAYINI', 'PARPAY', 'PARREJ', 'PARREC', 'ADMREC', 'RECREJ', 'APRREC', 'PROCES']

      this.commonService.callApi('requirement/search', data, 'post', false, false, 'REG').then(success => {
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
          this.requirementList = successData.body.content;
          console.log(this.requirementList);
          let type = this.DistrictList.filter(o1 => this.requirementList.some(o2 => o1.code === o2.district))
          console.log(type);

          this.requirementList.forEach(function (checkbox) {
            type.forEach(e => {
              if (e.code == checkbox.district)
                checkbox.district = e.description
            });
          })

          this.totalRecordCount = this.requirementList.length;
        } else {
          this.requirementList = []
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
    let obj: any = {}
    obj.pageNumber = 1;
    obj.pageSize = this.pagesize;

    if (this.filterObj.status){
      obj.reqStatus = [this.filterObj.status]

    }else if(this.filterObj.district)
    {
      obj.district = this.filterObj.district

    }else if(this.filterObj.schoolName){
      obj.schoolName = this.filterObj.schoolName

    }


    // obj.timeInterval = this.filterObj.timeInterval
    // delete this.filterObj.status
    this.getRequirementList(obj);
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    if (!this.filterObj.status)
    delete this.data.reqStatus
    this.getRequirementList(this.data);
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
      this.getRequirementList(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getRequirementList(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getRequirementList(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getRequirementList(this.data);
    }
  }
  /****************************************************************************/
}
