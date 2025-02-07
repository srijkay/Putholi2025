import { Component, Injector, Input, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-requirement-list',
  templateUrl: './manage-requirement-list.component.html',
  styleUrls: ['./manage-requirement-list.component.css']

})


export class ManageRequirementListComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }


  public requiredEditData: any = {};
  public consolidateData: any = {};

  constructor(inj: Injector) {
    super(inj)
    this.removeToken('consolidateId')
    this.callStatusApi()
  }

  ngOnInit(): void {
    this.removeToken('consolidateId')
    this.getListofDistrict()
    this.getschoolList(this.data)
    this.getConsolidateInfo(this.data)
  }

  public statusList = [
    {
      name: "Active",
      code: "Y"
    },
    {
      name: "InActive",
      code: "N"
    }

  ]
  /************************************************************************
          @PURPOSE      : Search Consolidate details
          @PARAMETERS   : pageno. pagesize
          @RETURN       : ConsolidateReferenceDTO
  **************************************************************************/

  consolidateList: any = []
  consolidateStatusList: any = []
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;

  getConsolidateInfo(data) {
    this.ngxLoader.start()
    this.loading = true
    data.active = 'Y'
    if (this.getToken('role') == 'ADMIN') {
      this.data.consolidateStatusCode = ["ADMREQ"]
    } else if (this.getToken('role') == 'APPRV') {
      this.data.consolidateStatusCode = ["APRREQ"]
    } else if (this.getToken('role') == 'REVIEW') {
      this.data.consolidateStatusCode = ["REVREQ"]
    }
    setTimeout(() => {
      this.commonService.callApi('consolidate/search', data, 'post', false, false, 'REG').then(success => {
        let successData: any = success;
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop()
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          let data: any = successData.body.content;
          this.consolidateStatusList = data.filter(x => x.consolidateStatusCode != 'CMPLTD')
          this.consolidateList = data.filter(x => x.consolidateStatusCode == 'CMPLTD')
        } else {
          this.consolidateList = []
          this.consolidateStatusList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)

  }
  /********************************************************************/

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
      this.getConsolidateInfo(this.filterObj);

    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getConsolidateInfo(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getConsolidateInfo(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getConsolidateInfo(this.data);
    }
  }

  /**************************************************************************
         @PURPOSE      : Get School Info details
         @PARAMETERS   : pageno, page size, loggeduser
         @RETURN       : NA
  ****************************************************************************/
  SchoolInfoList: any;
  schoolList: any = []
  getschoolList(data) {
    if (this.getToken('role') == 'BENIF') {
      data.loggedUser = this.getToken('username')
    }
    data.active = 'Y'
    this.commonService.callApi('schoolinfo/search', data, 'post', false, false, 'REG').then(success => {
      let successdata: any = success;
      this.schoolList = successdata.body.content
      let type = this.DistrictList.filter(o1 => this.schoolList.some(o2 => o1.code === o2.district))

      this.schoolList.forEach(function (checkbox) {
        type.forEach(e => {
          if (e.code == checkbox.district)
            checkbox.district = e.description
        });
      })


      this.SchoolInfoList = successdata.body.content[0]
      if (successdata.body.content[0])
        this.setToken('schoolStatus', this.SchoolInfoList.schoolStatus)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /**************************************************************************/


  /************************************************************************************/

  addRequirements() {
    if (this.SchoolInfoList) {
      // if (!this.consolidateList) {
      if (this.SchoolInfoList.schoolStatus == 'APPROVED' || this.SchoolInfoList.schoolStatus == 'Open For Requirements') {
        this.setToken("schoolId", this.SchoolInfoList.schoolInfoId)
        this.router.navigate(["/main/manage-requirement/require-edit", 'New']);
      } else {
        this.toastr.errorToastr("School Not Approved Yet", "Error")
      }
      // }else{
      //   this.router.navigate(["/main/manage-requirement/require-edit", 'New']);
      // }
    } else {
      this.toastr.errorToastr("You need to add school details first", "Error")
    }
  }

  onEdit(id, schoolId) {
    this.setToken("schoolId", schoolId)
    this.router.navigate(["/main/manage-requirement/require-edit/", id])
  }



}
