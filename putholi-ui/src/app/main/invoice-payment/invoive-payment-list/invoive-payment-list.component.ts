import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-invoive-payment-list',
  templateUrl: './invoive-payment-list.component.html',
  styleUrls: ['./invoive-payment-list.component.css']
})
export class InvoivePaymentListComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    this.getStatusList()
  }

  ngOnInit(): void {
    this.getInvoicePayment(this.data)
  }

  /****************************************************************************
        @PURPOSE      : Search Invoice Payment details
        @PARAMETERS   : pageno. pagesize
        @RETURN       : expenseDetails
 ****************************************************************************/
  public invoicePaymentList = [];
  public loading: boolean = false;
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  getInvoicePayment(data) {
    this.loading = true
    this.ngxLoader.start();
    if (this.getToken('role') == 'APPRV') {
      data.status = "EXPAPR"
    } else if (this.getToken('role') == 'REVIEW') {
      data.status = "EXPREV"
    }
    setTimeout(() => {
      this.commonService.callApi("expenses/search", data, "post", false, false, 'REG').then(success => {
        let successData: any = success
        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.invoicePaymentList = successData.body.content;
          console.log(this.invoicePaymentList);
        } else {
          this.invoicePaymentList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }, 1000)
  }
  /********************************************************************************/

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
      this.getInvoicePayment(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getInvoicePayment(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getInvoicePayment(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getInvoicePayment(this.data);
    }
  }
  /****************************************************************************/

}
