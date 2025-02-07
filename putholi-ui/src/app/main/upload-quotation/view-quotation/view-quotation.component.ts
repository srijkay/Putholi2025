import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { BsModalRef, ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';
import * as moment from 'moment';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
@Component({
  selector: 'app-view-quotation',
  templateUrl: './view-quotation.component.html',
  styleUrls: ['./view-quotation.component.css']
})
export class ViewQuotationComponent extends BaseComponent implements OnInit {
  @ViewChild('viewQuotate') viewQuotate: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();

  @Input()
  public quotationId: any;
  public uploadImage: any



  monthList = [
    {
      "name": "Days",
      "code": "D"
    },
    {
      "name": "Months",
      "code": "M"
    },
    {
      "name": "Years",
      "code": "Y"
    }
  ]

  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj);
    this.callStatusApi();
    this.getRoles()
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.quotationInfoByQuotateId(this.quotationId)
    this.viewQuotate.show()
  }

  clickCancel() {
    this.viewQuotate.hide();
    this.modalStatus.emit(false)
  }

  /**************************************************************************
         @PURPOSE      : get quotation info by quotate id
         @PARAMETERS   : quotate ID
         @RETURN       : UploadQuotationDTO
  ****************************************************************************/
  quotateData: any = {}
  approvalHistDtlsDTOs: any = []
  quotationInfoByQuotateId(id) {
    this.commonService.callApi('quotation/approval/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.quotateData = successData.quotationInfo;
      this.approvalHistDtlsDTOs = successData.schoolApprovalHistoryDetails
      this.quotateData.quotationDate = new Date(this.quotateData.quotationDate);
      this.quotateData.quotationValidDate = new Date(this.quotateData.quotationValidDate);

      let warranty = this.quotateData.warranty.match(/[0-9]+/)[0]

      let years = this.monthList.find(e => e.code === this.quotateData.warranty.match(/[a-zA-Z]+/)[0]).name

      this.quotateData.warranty = warranty + years

      let type = this.rolesList.filter(o1 => this.approvalHistDtlsDTOs.some(o2 => o1.code === o2.role))
      console.log(type);

      this.approvalHistDtlsDTOs.forEach(function (checkbox) {
        type.forEach(e => {
          if (e.code == checkbox.role)
            checkbox.role = e.description
        });
      })

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /******************************************************************************/

  options: any = {
    backdrop: 'static',
    keyboard: false
  };
  /****************************************************************************
     @PURPOSE      :open modal for quotations
     @PARAMETERS   : template id
     @RETURN       : NA
****************************************************************************/
  modalRef: any
  openImages(template) {
    this.quotationImagesByQuotationId(this.quotationId)
    this.viewQuotate.hide();
    this.modalRef = template
    template.show()
  }
  /***********************************************************************************/
  //Call this method in the image source, it will sanitize it.
  base64Image: any;
  base64PDF: any;
  transform(): SafeResourceUrl {
    if (this.base64Image) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
    } else if (this.base64PDF) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64PDF);
    }
  }

  /**************************************************************************
         @PURPOSE      : get quotation images by quotate id
         @PARAMETERS   : quotate ID
         @RETURN       : AttachmentDTO
  ****************************************************************************/
  quotateAttachment: any
  quotationImagesByQuotationId(id) {
    this.commonService.callApi('attachments/quotate/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.quotateAttachment = successData
      this.uploadImage = this.quotateAttachment.fileData

      // Determine the file type based on the file extension
      const filename = this.quotateAttachment.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + this.quotateAttachment.fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + this.quotateAttachment.fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/
  closeImages() {
    this.modalRef.hide()
    this.viewQuotate.show()
  }
}