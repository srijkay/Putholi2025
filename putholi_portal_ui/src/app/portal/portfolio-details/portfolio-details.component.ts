import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
declare var $: any
@Component({
  selector: 'app-portfolio-details',
  templateUrl: './portfolio-details.component.html',
  styleUrls: ['./portfolio-details.component.css']
})
export class PortfolioDetailsComponent extends BaseComponent implements OnInit {
  width: any = 40;
  ngOnInit(): void {
    this.loadScript('assets/js/main.js');
  }

  schoolDetailsId: any
  constructor(inj: Injector) {
    super(inj)
    this.getAllMasterList()
    this.getListofDistrict()
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id']
      this.getSchoolImages(id)
      this.getSchoolList(id)
      this.schoolDetailsId = id
    })
    window.onscroll = function () { scrollFunction() };
    function scrollFunction() {

    }


  }


  /****************************************************************************
          @PURPOSE      : To get School Info
          @PARAMETERS   : form,formdata
          @RETURN       : schoolInfoDTO
  ***************************************************************************/
  quotateAmount: number = 0
  schoolList: any = {}
  requirementInfo: any = []
  getSchoolList(id) {
    this.commonService.callApi('schoolinfo/' + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      this.schoolList = successData
      let id = this.schoolList.consolidateRefInfo.find(x => x.status != 'CMPLTD')
      console.log(id);
      this.schoolList.addressInfo.district = this.DistrictList.find(x => x.code == this.schoolList.addressInfo.district)

      this.paymentDetails(id.consolidateId)

      this.requirementInfo = id.requirementInfo.filter(x => x.reqStatus == 'QUOARV')

      let result = this.allActiveList.filter(o1 => this.requirementInfo.some(o2 => o1.code === o2.assetName))
      this.requirementInfo.forEach(function (checkbox) {
        result.forEach(e => {
          if (e.code == checkbox.assetName)
            checkbox.assetName = e.description
        });
      })
      this.requirementInfo.forEach(e => {
        let sum = 0;
        let quotData: any = e.quotationInfo.filter(x => x.quotateStatus == 'QUOARV')
        for (let i = 0; i < quotData.length; i++) {
          console.log(quotData[i].totalAmount);
          sum = quotData[i].totalAmount;
        }
        Math.round(Number(this.quotateAmount += sum) * 100);
      });

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /***************************************************************************/

  collectedAmount: any
  paymentDetails(id) {
    this.commonService.callApi("projectbook/" + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      console.log(successData);
      let sum = 0
      for (let i = 0; i < successData.length; i++) {
        sum += successData[i].amount;
      }
      Math.round(Number(this.collectedAmount = sum) * 100);
      this.setToken('collectedTillNow', sum);

      // let contribute = 0
      this.progressBar()

    })
  }

  /***************************************************************************/
  TotalProgress: any = []
  progressBar() {
    this.TotalProgress = Math.round((Number(this.collectedAmount / this.quotateAmount) * 100));
    console.log(this.TotalProgress);
  }

  /****************************************************************************
   @PURPOSE      : To get all School Images
   @PARAMETERS   : form,formdata
   @RETURN       : NA
 ***************************************************************************/
  schoolImages: any = []
  getSchoolImages(id) {
    this.commonService.callApi('attachments/school/' + id + '/SI', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      for (let i = 0; i < successData.length; i++) {
        successData[i].fileData = 'data:image/png;base64,' + successData[i].fileData;
      }
      this.schoolImages = successData
      console.log(this.schoolImages);


    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })
  }


  /*****************************************************************************/

  /****************************************************************************
   @PURPOSE      : captcha validation
   @PARAMETERS   : NA
   @RETURN       : NA
  ****************************************************************************/
  isValidCaptcha: boolean
  getCaptcha(event) {
    console.log(event);
    this.isValidCaptcha = event
  }

  /******************************************************************/
  public loadScript(url: string) {
    const body = <HTMLDivElement>document.body;
    const script = document.createElement('script');
    script.innerHTML = '';
    script.src = url;
    script.async = false;
    script.defer = true;
    body.appendChild(script);
  }
}
