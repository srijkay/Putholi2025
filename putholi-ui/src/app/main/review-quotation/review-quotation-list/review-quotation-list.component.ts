import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-review-quotation-list',
  templateUrl: './review-quotation-list.component.html',
  styleUrls: ['./review-quotation-list.component.css']
})
export class ReviewQuotationListComponent extends BaseComponent implements OnInit {


  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize,
  }

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
  }

  ngOnInit(): void {
    this.getRequirement(this.data)
  }

  /****************************************************************************
   @PURPOSE      : To retrive the school Info
   @PARAMETERS   : pageNumber,PageSize,loggedinuser
   @RETURN       : NA
****************************************************************************/

  public totalItem: any;
  public number: 0;
  reqInfo: any = []
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;
  getRequirement(data) {
    this.loading = true
    this.ngxLoader.start();
    if (this.getToken('role') == 'APPRV') {
      this.data.reqStatus = ["APRQUO"]
    } else if (this.getToken('role') == 'REVIEW') {
      this.data.reqStatus = ["REVQUO"]
    } else if (this.getToken('role') == 'ADMIN') {
      this.data.reqStatus = ['ADMQUO']
    }
    data.active = 'Y'
    setTimeout(() => {
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
          this.reqInfo = successData.body.content;
        } else {
          this.reqInfo = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)
  }

  /***************************************************************************** */

  /****************************************************************************
  @PURPOSE      : paginations
  @PARAMETERS   : pageNumber,PageSize
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
      this.getRequirement(this.filterObj);


    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getRequirement(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getRequirement(this.filterObj);

    } else {
      this.data.pageSize = e;
      this.getRequirement(this.data);

    }
  }
  /****************************************************************************/

  onClick(schoolid, reqid) {
    this.router.navigate(['/main/review-quotate/review-quotate-details/', schoolid, reqid])
  }

}
