import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-fund-allotment-details',
  templateUrl: './fund-allotment-details.component.html',
  styleUrls: ['./fund-allotment-details.component.css']
})
export class FundAllotmentDetailsComponent extends BaseComponent implements OnInit {

  public submitted: boolean = false;
  public fundAllotmentData: any = {}

  schoolId: any
  constructor(inj: Injector) {
    super(inj);
    this.callStatusApi();
    this.getListofsports()
    this.activatedRoute.params.subscribe((params) => {
      this.schoolId = params['id']
      this.schoolListById(this.schoolId)
    });
  }

  public expenseCategoryList = [
    {
      "name": "School Expense",
      "code": "School Expense"
    },
    {
      "name": "Trust Expense",
      "code": "Trust Expense"
    },
  ]

  requirementDetails: any = []
  collectedAmount = 0

  ngOnInit(): void {
    setTimeout(() => {
      this.getDashboardStatistics(this.schoolInfo.consolidateRefInfo.consolidateId);
      this.requirementDetails = this.schoolInfo.consolidateRefInfo.requirementInfo.filter(x => x.reqStatus == "REDALL")

      let require = this.infrastructreList.filter(o1 => this.requirementDetails.some(o2 => o1.code === o2.assetName))
      let sum = 0
      this.requirementDetails.forEach(function (checkbox) {
        require.forEach(e => {
          if (e.code == checkbox.assetName)
            checkbox.assetName = e.description
        });
        let quotate = checkbox.quotationInfo.find(x => x.quotateStatus == "QUOARV")
        sum += quotate.totalAmount
      })
      this.collectedAmount = sum;
    }, 1000)
  }


  dashboardStatistics: any = {}
  getDashboardStatistics(id) {
    this.commonService.callApi('dashboard/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.dashboardStatistics = successData;
    }).catch(e => {
      this.toastr.errorToastr(e.message);
    })
  }


  trustAmount: any;
  quotationAmount(data, last, index) {
    let sum = 0
    const requiredAmount: any = data.find(x => x.quotateStatus == "QUOARV")
    sum += requiredAmount.totalAmount;
    this.trustAmount = (requiredAmount.totalAmount) - (this.quotationfundAmount(data, last, index))
    return requiredAmount.totalAmount
  }

  quotationfundAmount(data, last, index) {
    let sum = 0
    let quotation: any

    quotation = data.find(x => x.quotateStatus == "QUOARV")
    if (last) {
      for (let i = 0; i < this.requirementDetails.length; i++) {
        if (index != i) {
          let quotate = this.requirementDetails[i].quotationInfo.find(x => x.quotateStatus == "QUOARV")
          sum += quotate.totalAmount
        }
      }
      return this.dashboardStatistics.collectedAmount - sum;
    } else {
      return quotation.totalAmount;
    }
  }

  onFund(form, fundAllotmentData) {
    if (form.valid) {
      this.saveProjectAccountBook(fundAllotmentData);

      console.log( this.dashboardStatistics.trustAmount);
     
      if (this.trustAmount != 0 && this.trustAmount != null && this.trustAmount != undefined) {
        this.saveTrustMemberBook(fundAllotmentData)
      }
    }
  }

  saveProjectAccountBook(fundAllotmentData) {
    fundAllotmentData.amount = this.dashboardStatistics.collectedAmount;
    fundAllotmentData.createdBy = this.getToken('username');
    fundAllotmentData.description = "Fund alloted to the school requirements";
    fundAllotmentData.feeType = "EXP";
    fundAllotmentData.projectId = this.schoolInfo.consolidateRefInfo.consolidateId
    fundAllotmentData.remarks = fundAllotmentData.expenseCategory

    this.commonService.callApi('projectbook', fundAllotmentData, 'post', false, false, 'REG').then(success => {
      let successData: any = success
      this.saveTrustAccountBook(fundAllotmentData);
    }).catch(e => {
      this.toastr.errorToastr(e.message);
    })
  }

  saveTrustAccountBook(fundAllotmentData) {

    fundAllotmentData.amount = this.dashboardStatistics.collectedAmount;
    fundAllotmentData.createdBy = this.getToken('username');
    fundAllotmentData.description = "Fund alloted to the school requirements";
    fundAllotmentData.feeType = "EXP";
    fundAllotmentData.donorId = this.getToken('emailId')
    fundAllotmentData.projectId = this.schoolInfo.consolidateRefInfo.consolidateId
    fundAllotmentData.remarks = fundAllotmentData.expenseCategory

    this.commonService.callApi('trustaccountbook', fundAllotmentData, 'post', false, false, 'REG').then(success => {
      let successData: any = success
      if (successData.body.apiStatusCode == 'SUCCESS') {
        this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        this.router.navigate(['/main/fund-allotment']);
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message);
    })
  }

  saveTrustMemberBook(fundAllotmentData) {

    fundAllotmentData.amount = this.trustAmount;
    fundAllotmentData.createdBy = this.getToken('username');
    fundAllotmentData.description = "Fund alloted to the school requirements";
    fundAllotmentData.feeType = "EXP";
    fundAllotmentData.remarks = fundAllotmentData.expenseCategory + this.schoolInfo.consolidateRefInfo.consolidateId

    this.commonService.callApi('trustmember', fundAllotmentData, 'post', false, false, 'REG').then(success => {
      let successData: any = success
    }).catch(e => {
      this.toastr.errorToastr(e.message);
    })
  }


}
