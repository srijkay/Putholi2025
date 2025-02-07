import { Component, Injector, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-search-requrement-details',
  templateUrl: './search-requrement-details.component.html',
  styleUrls: ['./search-requrement-details.component.css']
})
export class SearchRequrementDetailsComponent extends BaseComponent implements OnInit {
  @ViewChild('staticTabs', { static: true }) staticTabs: TabsetComponent;


  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj);
    this.getListofsports()
    this.getSportsDesc()
    this.getStatusList()
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id']
      this.getRequirementInfoById(id)
      this.getPreImagesInfo(id, 'PI')
      this.getPreImagesInfo(id, 'PO')
      this.quotationInfoByRequirementId(id)
      this.getInvoiceByRequirementId(id)
    })

  }

  ngOnInit(): void {
  }


  /**************************************************************************
        @PURPOSE      : get requirements info by requirement id
        @PARAMETERS   : requirement ID
        @RETURN       : RequirementsDTO
 ****************************************************************************/
  requirementInfoById: any = {}
  assetName: any
  getRequirementInfoById(id) {
    this.commonService.callApi('requirement/req/description/' + id, '', 'get', false, false, 'REG').then(success => {
      this.requirementInfoById = success
    }).catch((e) => {

    })
  }
  /***************************************************************************************/

  imagesTemplate: BsModalRef
  clickImages(template, type, id) {
    if (type == 'REQ') {
      this.getRequirementImagesInfo(id)
    } else
      this.ImagesByAttachmentId(id)
    // }else if(type=='PO'){

    // }
    this.imagesTemplate = this.modalService.show(template)
  }

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
             @PURPOSE      : get List of requirement-images by school id
             @PARAMETERS   : requirement ID, upload for
             @RETURN       : AttachmentsDTO
  ****************************************************************************/
  getRequirementImagesInfo(id) {
    this.commonService.callApi('attachments/' + id + '/RI', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success

      // Determine the file type based on the file extension
      const filename = successData[0].fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + successData[0].fileData;;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + successData[0].fileData;;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/


  /**************************************************************************
           @PURPOSE      : get List of pre-images by requirement id
           @PARAMETERS   : requirement ID, upload for
           @RETURN       : UploadQuotationDTO
    ****************************************************************************/
  preImages: any = []
  getPreImagesInfo(id, uploadFor) {
    this.commonService.callApi('attachments/' + id + '/' + uploadFor, '', 'get', false, false, 'REG').then(success => {
      this.preImages = success
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/

  /**************************************************************************
        @PURPOSE      : get images by attachment id
        @PARAMETERS   : attachment ID
        @RETURN       : images
 ****************************************************************************/
  ImagesByAttachmentId(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success

      // Determine the file type based on the file extension
      const filename = successData.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + successData.fileData;;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + successData.fileData;;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /***********************************************************************/

  /**************************************************************************
       @PURPOSE      : get List of quotation info by requirement id
       @PARAMETERS   : requirement ID
       @RETURN       : UploadQuotationDTO
****************************************************************************/
  quotationInfoList: any = []
  quotationInfoByRequirementId(id) {
    this.commonService.callApi('quotation/info/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      let result = this.statusList.filter(o1 => successData.some(o2 => o1.code === o2.quotateStatus))
      successData.forEach(function (checkbox) {
        result.forEach(e => {
          if (e.code == checkbox.quotateStatus)
            checkbox.description = e.description
        });
      })
      this.quotationInfoList = successData
      console.log(this.quotationInfoList);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/


  /****************************************************************************
      @PURPOSE      : get invoice details by requirementId
      @PARAMETERS   : requirementId
      @RETURN       : InvoiceDetailsDTO
   ****************************************************************************/
  invoiceList: any = []
  approvedAmount: any = 0
  getInvoiceByRequirementId(id) {
    this.commonService.callApi('invoice/info/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      let result = this.statusList.filter(o1 => successData.some(o2 => o1.code === o2.invoiceStatus))
      successData.forEach(function (checkbox) {
        result.forEach(e => {
          if (e.code == checkbox.invoiceStatus)
            checkbox.description = e.description
        });
      })
      this.invoiceList = successData

      //this.approvedAmount = this.quotationInfoList.filter(x => x.quotateStatus == 'REDALL')
      this.approvedAmount = this.quotationInfoList.filter(x => x.quotateStatus == 'QUOARV')


      console.log(this.approvedAmount);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /************************************************************************************/

  isViewQuotation: boolean = false
  quotationId: any
  viewquotates(id) {
    this.isViewQuotation = true
    this.quotationId = id
  }

  invoiceId: any
  isviewInvoice: boolean = false
  viewInvoice(id) {
    this.isviewInvoice = true
    this.invoiceId = id
  }


  closeQuotation(event) {
    this.isViewQuotation = event
  }

  closeInvoice(event) {
    this.isviewInvoice = event
  }

  onClickNext(type) {
    if (type == 'RI') {
      this.staticTabs.tabs[1].active = true
    } else if (type == 'PI') {
      this.staticTabs.tabs[2].active = true
    } else if (type == 'QI') {
      this.staticTabs.tabs[3].active = true
    } else if (type == 'PO') {
      this.staticTabs.tabs[4].active = true
    } else if (type == 'II') {
      this.staticTabs.tabs[5].active = true
    }
  }

  onClickPrevoius(type) {

    if (type == 'RI') {
      this.staticTabs.tabs[0].active = true
    }
    else if (type == 'PI') {
      this.staticTabs.tabs[1].active = true
    }
    else if (type == 'QI') {
      this.staticTabs.tabs[2].active = true
    }
    else if (type == 'PO') {
      this.staticTabs.tabs[3].active = true
    }
  }

}
