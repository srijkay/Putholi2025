import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-delete-school',
  templateUrl: './delete-school.component.html',
  styleUrls: ['./delete-school.component.css']
})
export class DeleteSchoolComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    this.getSchoolList(this.data)
  }

  ngOnInit(): void {
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

    if (this.getToken('role') == "ADMIN") {
      data.schoolStatusCode = ["APR", 'DSCADM']
      data.active = 'Y'
    } else if (this.getToken('role') == 'REVIEW') {
      data.schoolStatusCode = ['DSCREV']
    } else if (this.getToken('role') == 'APPRV') {
      data.schoolStatusCode = ['DSCAPR']
    }
    setTimeout(() => {
      this.commonService.callApi('schoolinfo/search', data, 'post', false, false, 'REG').then(success => {
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
    this.data.status = undefined
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

}
