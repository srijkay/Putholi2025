import { Component, Injector, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-pending-workflow',
  templateUrl: './pending-workflow.component.html',
  styleUrls: ['./pending-workflow.component.css']
})
export class PendingWorkflowComponent extends BaseComponent implements OnInit {
  consolidateId: any
  page: any = 1;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize,
  }

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    this.getListofsports()
    this.removeToken('pendingAmount');
  }

  ngOnInit(): void {
    if (this.getToken('role') != 'TRUSTV') {

      this.getSchoolList(this.data)
    } else if (this.getToken('role') == 'TRUSTV') {
      this.getRequirement(this.data)

    }
  }

  /****************************************************************************
           @PURPOSE      : Search Role details
           @PARAMETERS   : pageno. pagesize
           @RETURN       : RoleDetailsDto
  ****************************************************************************/

  consolidateList: any = []
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;
  getSchoolList(data) {
    this.loading = true
    this.ngxLoader.start();

    if (this.getToken('role') == 'APPRV') {
      data.consolidateStatusCode = ["APRDEO"]
    } else if (this.getToken('role') == 'REVIEW') {
      data.consolidateStatusCode = ["REVDEO"]
    } else {
      data.consolidateStatusCode = ["APR", 'ADMDEO', 'DEOAPR', 'VOLREJ']
    }
    data.active = 'Y'
    setTimeout(() => {
      this.commonService.callApi('consolidate/search', data, 'post', false, false, 'REG').then(success => {
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
          this.consolidateList = successData.body.content;
          console.log(this.consolidateList);
        } else {
          this.consolidateList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)
  }
  /****************************************************************************/
  /****************************************************************************
       @PURPOSE      : To retrive the school Info
       @PARAMETERS   : pageNumber,PageSize,loggedinuser
       @RETURN       : NA
  ****************************************************************************/

  public totalItem: any;
  reqInfo: any = []

  getRequirement(data) {
    this.loading = true
    this.ngxLoader.start();
    data.loggedUser = this.getToken('username')
    data.consolidateStatus = ['ASGVOL', 'VOLREJ']
    data.reqStatusCode = ['CMPLTD', 'APR']
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

            this.getRequireConfig(e.requirementId)

          });
          // console.log(this.reqInfo);

        } else {
          this.reqInfo = []
        }


      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)

  }
  /******************************************************************************/
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
      this.getToken('role') != 'TRUSTV' ? this.getSchoolList(this.filterObj) : this.getRequirement(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getToken('role') != 'TRUSTV' ? this.getSchoolList(this.data) : this.getRequirement(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getToken('role') != 'TRUSTV' ? this.getSchoolList(this.filterObj) : this.getRequirement(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getToken('role') != 'TRUSTV' ? this.getSchoolList(this.data) : this.getRequirement(this.data);
    }
  }
  /****************************************************************************/
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
  /****************************************************************************/
  onClick(schoolid, reqid, type, amount) {
    if (type == 'Q') {
      this.router.navigate(['/main/upload-quotation/', schoolid, reqid])
      this.setToken('pendingAmount', amount);
    }
  }


  invoiceTotalAmount: any = 0
  requirementInfoById: any
  getRequireConfig(id) {
    this.commonService.callApi('requirement/req/' + id, '', 'get', false, false, 'REG').then(success => {
      this.requirementInfoById = success
      // get the quotation info from the requirement api
      let quotationInfoList: any = this.requirementInfoById.quotationInfo;
      // get the invoice info from the requirement api
      let invoiceList = this.requirementInfoById.invoiceDetails.filter(x => x.invoiceStatus != 'ADMINV' && x.invoiceStatus != 'INVREV' && x.invoiceStatus != 'INVAPR' && x.invoiceStatus != 'REJINV')

      let approvedAmount = quotationInfoList.find(x => x.quotateStatus == 'QUOARV')
      let invoiceWorkStatus = this.requirementInfoById.invoiceDetails.filter(x => x.workStatus == 'FC' && x.invoiceStatus != 'ADMINV' && x.invoiceStatus != 'INVREV' && x.invoiceStatus != 'INVAPR' && x.invoiceStatus != 'REJINV')

      console.log(invoiceWorkStatus);

      this.reqInfo.forEach((e) => {
        if (e.requirementId == this.requirementInfoById.requirementId) {
          if (approvedAmount) {

            if (!invoiceWorkStatus.length) {
              let sum = 0
              for (let i = 0; i < invoiceList.length; i++) {
                sum += invoiceList[i].invoiceAmount
              }
              this.invoiceTotalAmount = approvedAmount.totalAmount - sum
              e.pendingAmount = this.invoiceTotalAmount
            } else {
              e.pendingAmount = 0
            }
          } else {
            e.pendingAmount = 0
          }
        }
      })
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /********************************************************************************** */



}