import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-financial-status-report',
  templateUrl: './financial-status-report.component.html',
  styleUrls: ['./financial-status-report.component.css']
})
export class FinancialStatusReportComponent extends BaseComponent implements OnInit {

  colorTheme = 'theme-blue';
  selectYearPicker = {
    dateInputFormat: 'YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
  }
  showDetails: boolean = false

  constructor(inj: Injector) {
    super(inj);
    this.getDonorDetails()
  }

  ngOnInit(): void {
  }

  filterObj: any = {}

  /****************************************************************************
    @PURPOSE      : Applying filters
    @PARAMETERS   : filterObj
    @RETURN       : NA
 ****************************************************************************/
  applyFilter() {
    this.getDataBasedOnYear(new Date(this.filterObj.year))
    this.getTrustDetails(new Date(this.filterObj.year))

  }
  /*************************************************************************/

  /****************************************************************************
    @PURPOSE      : To get the data based on filters.
    @PARAMETERS   : year
    @RETURN       : List of TrustAccountBook
 ****************************************************************************/
  public SchoolExpenses: number;
  public individual: number;
  public corporate: number;
  public individualDetails: any = []
  public corporateDetails: any = []
  public schoolDetails: any = []
  getDataBasedOnYear(year: any) {
    this.commonService.callApi('projectbook/year/' + year, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      console.log(this.donorDetails);

      let project = 0
      let inddonor = 0
      let corpDonor = 0
      successData.forEach(e => {
        if (e.feeType == 'INC') {
          project = project + e.amount
          this.schoolDetails.push(e)
        }

        this.donorDetails.forEach(d => {
          if (d.emailId == e.donorId) {

            //get the individual donors
            if (d.organizationType == null) {
              inddonor = inddonor + e.amount
              this.individualDetails.push(e)
            } else {

              //get the corporate donors
              corpDonor = corpDonor + e.amount
              this.corporateDetails.push(e)
            }
          }
        })
      });

      this.SchoolExpenses = project
      this.individual = inddonor;
      this.corporate = corpDonor;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  /******************************************************************************/

  /****************************************************************************
    @PURPOSE      : To get the data based on filters.
    @PARAMETERS   : year
    @RETURN       : List of TrustAccountBook
 ****************************************************************************/
  public trustMemeber: number;
  public expenses: number
  public memberDetails: any = []
  public trustExpenses: any = []
  getTrustDetails(year: any) {
    this.commonService.callApi('trustmember/year/' + year, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;

      let trust = 0
      let trustExep = 0
      successData.forEach(e => {
        //get the member registration amount
        if (e.feeType == 'INC') {
          trust = trust + e.amount
          this.memberDetails.push(e)

          //get the trust expenses
        } else if (e.feeType == 'EXP') {
          trustExep = trustExep + e.amount
          this.trustExpenses.push(e)
        }
      });
      this.trustMemeber = trust
      this.expenses = trustExep
      this.showDetails = true
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /***************************************************************************************/

  donorDetails: any = []
  getDonorDetails() {
    this.commonService.callApi('donorauthenticate', '', 'get', false, true, 'REG').then(success => {
      let successData: any = success;
      this.donorDetails = successData
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }


  /****************************************************************************
    @PURPOSE      : showing the year only in calender.
    @PARAMETERS   : NA
    @RETURN       : NA
 ****************************************************************************/
  onOpenCalendar(container) {
    container.yearSelectHandler = (event: any): void => {
      container._store.dispatch(container._actions.select(event.date), container._actions.select(event.month));
    };
    container.setViewMode('year');
  }
  /*******************************************************************/
  isShowSchool: boolean = false
  showSchoolDetails() {
    this.isShowSchool = true
  }

  isShowIndividual: boolean = false
  showIndividualDonors() {
    this.isShowIndividual = true
  }

  isShowCorporate: boolean = false
  showCorporateDonors() {
    this.isShowCorporate = true
  }

  isShowExpenses: boolean = false
  showTrustExpenses() {
    this.isShowExpenses = true
  }

  isShowMembers: boolean = false
  showTrustMembers() {
    this.isShowMembers = true
  }

  modalStatus(event: any) {
    this.isShowSchool = event;
    this.isShowIndividual = event;
    this.isShowCorporate = event;
    this.isShowExpenses = event;
    this.isShowMembers = event
  }
}
