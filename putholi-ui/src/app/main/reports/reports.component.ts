import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj);
    this.getStatusList()

    this.getCompanyName()
    this.getUnapprovedVendors()
  }

  ngOnInit(): void {
  }

  isExportMode = false;
  exportOption = '';
  pageIndex = 1;
  noOfRecordsPerPageCopy = 0;
  totalRecordCount: any;
  quotationTableList: any = "quotationTableList";
  nameofthefile: any = "VendorReports";
  noOfRecordsPerPage: any;
  page: any = 1;
  pageSize: any

  /****************************************************************************
           @PURPOSE      : get the vendor Details
           @PARAMETERS   : NA
           @RETURN       : quotationINfoDTO
  ****************************************************************************/
  quotationInfoList: any = []
  getCompanyName() {
    let quotateStatus = ['QUOARV']
    this.commonService.callApi('quotation/vendordetails/' + quotateStatus, "", 'get', false, false, 'REG').then(success => {
      let successData: any = success
      console.log(successData);

      let uniqueArray: [] = successData.filter((obj, index, self) =>
        index === self.findIndex((o) => o.companyName === obj.companyName))
      this.quotationInfoList = uniqueArray

      this.totalRecordCount = this.quotationInfoList.length
      this.noOfRecordsPerPage = this.quotationInfoList.length
      this.pageSize = this.quotationInfoList.length

    }).catch(e => {
      this.ngxLoader.stop()
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /******************************************************************/



  pageIndexes = 1;
  noOfRecordsPerPageCopied = 0;
  totalunapprovedCount: any;
  unapprovedInfoList: any = "unapprovedInfoList";
  unapprovedNameofthefile: any = "unapprovedVendorReports";
  unapprovednoOfRecordsPerPage: any;
  unapprovedpageSize: any

  pageNumber:any = 1
  pagesize:any = 10

  /****************************************************************************
          @PURPOSE      : get the unapproved vendor Details
          @PARAMETERS   : NA
          @RETURN       : quotationINfoDTO
 ****************************************************************************/
  unapprovedVendorsInfo: any = []
  getUnapprovedVendors() {
    let quotateStatus = ['ADMQUO', 'REJQUO']
    this.commonService.callApi('quotation/vendordetails/' + quotateStatus, "", 'get', false, false, 'REG').then(success => {
      let successData: any = success
      console.log(successData);
      

      let uniqueArray: [] = successData.filter((obj, index, self) =>
        index === self.findIndex((o) => o.companyName === obj.companyName))
      this.unapprovedVendorsInfo = uniqueArray

      this.totalunapprovedCount = this.unapprovedVendorsInfo.length
      this.unapprovednoOfRecordsPerPage = this.unapprovedVendorsInfo.length
      this.unapprovedpageSize = this.unapprovedVendorsInfo.length

    }).catch(e => {
      this.ngxLoader.stop()
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /******************************************************************/
}
