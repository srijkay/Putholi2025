import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
import { NgxUiLoaderService } from "ngx-ui-loader";
import { NgPopupsService } from 'ng-popups';

@Component({
  selector: 'app-manage-school-list',
  templateUrl: './manage-school-list.component.html',
  styleUrls: ['./manage-school-list.component.css']
})
export class ManageSchoolListComponent extends BaseComponent implements OnInit {
  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
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

  constructor(inj: Injector, private ngPopups: NgPopupsService) {
    super(inj)
    this.getListofDistrict()
    this.removeToken("schoolInfoId")
    this.getStatusList()
    this.getSchoolTypeList()
    this.callStatusApi();

  }

  ngOnInit(): void {

    this.getSchoolList(this.data)

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
    data.active = 'Y'
    if (this.getToken('role') == 'ADMIN') {
      data.status = "PENADM"
    } else if (this.getToken('role') == 'APPRV') {
      data.status = "PENAPR"
    } else if (this.getToken('role') == 'REVIEW') {
      data.status = "PENREV"
    } else if (this.getToken('role') == 'BENIF') {
      data.loggedUser = this.getToken('username')
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
        this.removeToken("schoolInfoId")
        if (successData.body.content.length) {
          this.schoolList = successData.body.content;

          for (let i = 0; i < this.schoolList.length; i++) {
            this.schoolListById(this.schoolList[i].schoolInfoId)
          }
          setTimeout(() => {
            let type = this.DistrictList.filter(o1 => this.schoolList.some(o2 => o1.code === o2.district))

            this.schoolList.forEach(function (checkbox) {
              type.forEach(e => {
                if (e.code == checkbox.district)
                  checkbox.district = e.description
              });
            })
          }, 500)
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
    console.log(this.filterObj);


    this.getSchoolList(this.filterObj);
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
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
  deleteMsg: any
  deleteSchool(id) {
    this.deleteMsg = "Reasons for Deletion"
    this.removeToken('schoolInfoId')
    this.ngPopups.prompt(this.deleteMsg)
      .subscribe(res => {
        let successData: any = res
        console.log(successData);

        if (successData.result) {
          if (successData.value) {
            console.log(res);

            let manageSchoolData: any = {}
            manageSchoolData.type = "School Deletion"
            manageSchoolData.schoolInfoId = id;
            manageSchoolData.status = "APR"
            manageSchoolData.actionBy = this.getToken('username');
            manageSchoolData.role = this.getToken('role')
            manageSchoolData.remarks = successData.value
            this.commonService.callApi('schoolinfo/deletion', manageSchoolData, 'post', false, false, 'REG').then(success => {
              let successData: any = success
              console.log(successData);
              if (successData.body.apiStatusCode === "SUCCESS") {
                this.getSchoolList(this.data)
                this.removeToken("schoolInfoId")
                this.toastr.successToastr("School info deleted successfully", 'Success')
              } else {
                this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
              }

            }).catch(e => {
              this.toastr.errorToastr(e.message, 'Oops!')
            })
          } else {
            this.toastr.warningToastr("Please enter the reasond for deletion", "Error")
          }
        }
      })
  }
}
