import { Component, ElementRef, Injector, OnInit, ViewChild } from '@angular/core';
import { BsModalRef, ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';
import html2canvas from 'html2canvas';
import * as jsPDF from 'jspdf'
@Component({
  selector: 'app-fund-process-work-order',
  templateUrl: './fund-process-work-order.component.html',
  styleUrls: ['./fund-process-work-order.component.css']
})
export class FundProcessWorkOrderComponent extends BaseComponent implements OnInit {
  @ViewChild('content') content: ElementRef;

  constructor(inj: Injector) {
    super(inj)
    this.callStatusApi();
    this.getListofsports()
    this.getSportsDesc()
    setTimeout(() => {
      this.activatedRoute.params.subscribe(params => {
        let schoolId = params['id']
        this.schoolListById(schoolId);
        this.requirementInfoList(params['id2']);
      })
    }, 800)

  }

  public submitted: boolean = false;
  public requirementData: any = {}

  ngOnInit(): void {
  }



  /****************************************************************************
      @PURPOSE      : To get requirement by unsing consolidate Id
      @PARAMETERS   : consolidateId 17
      @RETURN       : RequirementInfoDTO 
   ****************************************************************************/
  requirementTypeList: any = []
  selectRequirementList: any = []
  requirementInfoList(id) {
    this.commonService.callApi('requirement/all/' + id + '/GNRORD', '', 'get', false, false, 'REG').then(success => {
      this.requirementTypeList = success

      let require = this.infrastructreList.filter(o1 => this.requirementTypeList.some(o2 => o1.description === o2.assetName))
      let assetType = this.sportsDescription.filter(o1 => this.requirementTypeList.some(o2 => o1.description === o2.assetType))

      console.log(this.requirementTypeList, assetType);


      this.requirementTypeList.forEach(function (checkbox) {

        assetType.forEach(f => {
          if (f.description == checkbox.assetType)
            checkbox.assetType = f.code
        })
      })
      this.requirementTypeList.forEach(function (checkbox) {
        console.log(checkbox.assetType);

        if (checkbox.assetType == 'OTH') {
          checkbox.assetNameDescription = checkbox.assetName
        } else {
          require.forEach(e => {
            if (e.description == checkbox.assetName) {
              checkbox.assetNameDescription = e.description
              checkbox.assetName = e.code
            }
          });
        }

      })

      console.log(this.requirementTypeList);
      this.selectRequirementList = this.requirementTypeList
      console.log(this.selectRequirementList);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops')
    })
  }
  /***********************************************************************************/
  requirementInfo: any = {}
  selectRequirement(event) {
    this.requirementInfo = event
    this.quotationList(event.requirementId)
    this.fethUser(this.schoolInfo.volunteerName)
  }

  /****************************************************************************
        @PURPOSE      : To get quotation info by unsing requirementId
        @PARAMETERS   : requirementId
        @RETURN       : QuotationInfoDTO 
  ****************************************************************************/
  quotationData: any = {}
  tax: any
  quotationList(id) {
    this.commonService.callApi('quotation/info/' + id, '', 'get', false, false, 'REG').then(success => {
      let SuccessData: any = success
      let quotation = SuccessData.filter(x => x.quotateStatus == 'QUOARV')
      this.quotationData = quotation[0]
      this.tax = Math.round((Number(this.quotationData.totalAmount) * Number(this.quotationData.tax)) / 100)

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops')
    })
  }
  /***********************************************************************************/


  modalRef: ModalDirective

  openWorkOrder(template: ModalDirective) {
    this.modalRef = template
    template.show();
    // this.updatedRequiremnt(this.requirementTypeList[0])
  }

  /****************************************************************************
    @PURPOSE      : To get userDetails based on username
    @PARAMETERS   : username
    @RETURN       : UserDetailsDTO
 ****************************************************************************/
  userData: any = {}
  fethUser(username) {
    this.commonService.callApi('authenticate/' + username, '', 'get', false, false, 'REG').then(success => {
      this.userData = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops')
    })
  }
  /**************************************************************************/

  generateWorkOrder(type) {
    let DATA: any = document.getElementById('htmlData');
    html2canvas(DATA).then((canvas) => {
      let fileWidth = 250;
      let fileHeight = (canvas.height * fileWidth) / canvas.width;
      const FILEURI = canvas.toDataURL('image/png');
      let PDF = new jsPDF('l', 'mm', 'a4');
      let position = 0;
      PDF.addImage(FILEURI, 'PNG', 0, position, fileWidth, fileHeight);
      PDF.save('work_order.pdf');
    });
    if (type == 'DOWNLOAD') {
      this.updatedRequiremnt(this.requirementTypeList[0])
      this.modalRef.hide();
    }
  }

  updatedRequiremnt(requirementData) {

    requirementData.lastModifiedBy = this.getToken('username')
    requirementData.reqStatus = "ORDINI";
    delete requirementData.quotationInfo;
    delete requirementData.invoiceDetails;
    requirementData.consolidateRef = {
      "consolidateId": this.schoolInfo.consolidateRefInfo.consolidateId,
    }

    delete requirementData.assetNameDescription
    this.commonService.callApi('requirement', requirementData, 'put', false, false, 'REG').then(success => {
      let successData: any = success
      this.requirementInfoList(this.schoolInfo.consolidateRefInfo.consolidateId);
      this.requirementData.requirement = null
      this.quotationData.companyName = ""

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops');
    });
  }

  closeWorkOrder() {
    this.modalRef.hide();
    this.requirementTypeList = []
  }

}
