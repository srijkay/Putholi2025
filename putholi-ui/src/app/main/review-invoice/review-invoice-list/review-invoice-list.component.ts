import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-review-invoice-list',
  templateUrl: './review-invoice-list.component.html',
  styleUrls: ['./review-invoice-list.component.css']
})
export class ReviewInvoiceListComponent extends BaseComponent implements OnInit {


  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize,
  }

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    this.getListofsports()
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
  reviewInvoice: any = []
  getRequirement(data) {
    this.loading = true
    this.ngxLoader.start();
    if (this.getToken('role') == 'APPRV') {
      this.data.reqStatus = ["INVAPR", "PARAPR", "PARREJ"]
    } else if (this.getToken('role') == 'REVIEW') {
      this.data.reqStatus = ["INVREV", "PARAPR", "PARREJ"]
    } else if (this.getToken('role') == 'ADMIN') {
      this.data.reqStatus = ["ADMINV", "PARAPR", "ADMREC", "PARREJ", "PARPAY", "PARREC", "PAYFAL", "PARFAL", "PAYINI", "PROCES"]
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
          this.reqInfo.forEach(e => {
            this.getInvoiceByRequirementId(e.requirementId)
          });
        } else {
          this.reviewInvoice = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 300)
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
      // this.getSchoolList(this.filterObj);
      this.getRequirement(this.filterObj);


    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      // this.getSchoolList(this.data);
      this.getRequirement(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      // this.getSchoolList(this.filterObj);
      this.getRequirement(this.filterObj);

    } else {
      this.data.pageSize = e;
      // this.getSchoolList(this.data);
      this.getRequirement(this.data);

    }
  }
  /****************************************************************************/

  onClick(schoolid, type, reqid) {
    if (type == 'IA') {
      this.router.navigate(['/main/review-invoice/review-invoice-details/', schoolid, reqid])
    } else if (type == 'UR') {
      this.router.navigate(['/main/review-invoice/review-invoice-details/', schoolid, reqid])
    }
  }


  /****************************************************************************
    @PURPOSE      : get invoice details by requirementId
    @PARAMETERS   : requirementId
    @RETURN       : InvoiceDetailsDTO
 ****************************************************************************/
  invoiceList: any = []
  successData: any
  getInvoiceByRequirementId(id) {
    this.commonService.callApi('invoice/info/' + id, '', 'get', false, false, 'REG').then(success => {
      this.successData = success;
      if (this.getToken('role') == 'ADMIN') {
        this.invoiceList = this.successData.filter(x => x.invoiceStatus == 'ADMINV' || x.invoiceStatus == 'ADMREC'|| x.invoiceStatus == 'PAYINI')
      } else if (this.getToken('role') == 'REVIEW') {
        this.invoiceList = this.successData.filter(x => x.invoiceStatus == 'INVREV')
      }
      else if (this.getToken('role') == 'APPRV') {
        this.invoiceList = this.successData.filter(x => x.invoiceStatus == 'INVAPR')
      }



      console.log("kjhg", this.invoiceList);


      if (this.invoiceList.length != 0) {
        console.log(this.reqInfo);
        this.reqInfo.forEach(e => {
          this.invoiceList.forEach(i => {
            i.requirementId = id
            if (i.requirementId == e.requirementId) {
              this.reviewInvoice.push(e)

              function getUniqueListBy(arr, key) {
                return [...new Map(arr.map(item => [item[key], item])).values()]
              }

              const arr1 = getUniqueListBy(this.reviewInvoice, 'requirementId')
              this.reviewInvoice = (arr1)
            }
          })
        })
      }
      console.log(this.reviewInvoice);



    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /************************************************************************************/

}
