import { Component, Injector, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-beneficiary-list',
  templateUrl: './manage-beneficiary-list.component.html',
  styleUrls: ['./manage-beneficiary-list.component.css']
})
export class ManageBeneficiaryListComponent extends BaseComponent implements OnInit {
  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize,

  }

  constructor(inj: Injector ) {
    super(inj);
    this.callStatusApi()
  }
  ngOnInit() {
    if (this.getToken('role') == 'ADMIN') {
      this.data.statusCode = ["PENADM"]
    } else if (this.getToken('role') == 'APPRV') {
      this.data.statusCode = ["PENAPR"]
    } else if (this.getToken('role') == 'REVIEW') {
      this.data.statusCode = ["PENREV",]
    }

    this.getBeneficiaryList(this.data)
  }

  /****************************************************************************
         @PURPOSE      : Search manage Beneficiary
         @PARAMETERS   : pageno. pagesize
         @RETURN       : beneficiaryDTO
    ****************************************************************************/
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;
  beneficiaryList: any = []
  getBeneficiaryList(data) {
    this.loading = true
    this.ngxLoader.start();
    data.loggedUser = this.getToken('username')

    setTimeout(() => {
      this.commonService.callApi('usermgmt/search', this.data, 'post', false, false, 'REG').then(success => {
        console.log(success);
        let successData: any = success
        console.log(successData.content);

        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.beneficiaryList = successData.body.content;
          console.log(this.beneficiaryList);

        } else {
          this.beneficiaryList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)
  }
  /************************************************************************************/

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
      this.getBeneficiaryList(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getBeneficiaryList(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getBeneficiaryList(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getBeneficiaryList(this.data);
    }
  }
  /****************************************************************************/
}


