import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BsModalRef, ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-upload-receipt',
  templateUrl: './upload-receipt.component.html',
  styleUrls: ['./upload-receipt.component.css']
})
export class UploadReceiptComponent extends BaseComponent implements OnInit {

  @ViewChild('receiptTenplate') receiptTenplate: ModalDirective;
  @Output() invoiceStatus = new EventEmitter<any>();
  @Input() requirementId: any
  @Input()
  public invoiceId: any
  public isSubmitting: boolean = false;
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
  }

  ngOnInit(): void {
    this.callStatusApi();
  }
  isDisabled: boolean = true

  ngAfterViewInit() {
    this.receiptTenplate.show();
    this.getReceiptInfo(this.invoiceId, 'UR')
    this.getInvoiceById(this.invoiceId)

  }

  options: any = {
    backdrop: 'static',
    keyboard: false
  };

  clickCancel() {
    this.receiptTenplate.hide();
    this.invoiceStatus.emit(false)
  }

  isAddImage: boolean
  attachmentId: any
  addModelRef: BsModalRef
  addReceipt(template, type, id) {
    this.isSubmitting = false
    if (type == 'EDIT') {
      this.attachmentId = id
      this.isAddImage = false
      this.receiptImages(id)
    } else if (type == 'ADD') {
      this.isAddImage = true
    }
    this.addModelRef = this.modalService.show(template, this.options)
  }



  /**************************************************************************
          @PURPOSE      : ADD/UPDATE pre-images by requirement id
          @PARAMETERS   : requirement ID, image
          @RETURN       : NA
   ****************************************************************************/
  requirementPreImages: any = {}
  uploadImage: any
  uploadReceipts() {
    const sendData = new FormData();
    let api_Type: any;
    if (!this.isSubmitting) {
      this.isSubmitting = true

      if (this.uploadImage[0].type) {
        if (this.isAddImage) {
          this.requirementPreImages.invoiceId = this.invoiceId;
          let data = JSON.stringify(this.requirementPreImages);
          sendData.append('QuotationAttachmentsDTO', data);
          sendData.append('fileType ', 'UR')
          sendData.append('quotation', this.uploadImage[0])
          api_Type = 'post'
        } else {
          let quotData: any = {}
          quotData.invoiceId = this.invoiceId;
          quotData.attachmentId = this.attachmentId;
          let data = JSON.stringify(quotData);
          sendData.append('QuotationAttachmentsDTO', data);
          sendData.append('fileType ', 'UR')
          sendData.append('quotation', this.uploadImage[0])
          api_Type = 'put'
        }

        this.commonService.callApi('attachments', sendData, api_Type, true, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode == "SUCCESS") {
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            this.getReceiptInfo(this.invoiceId, 'UR');
            this.addModelRef.hide()
            this.isDisabled = false
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
            this.isSubmitting = false
          }
          this.uploadImage = {}
        }).catch(e => {
          this.isSubmitting = false
          this.toastr.errorToastr(e.message, 'Oops!')
        });
      } else {
        this.addModelRef.hide()
      }
    }
  }
  /********************************************************************************/

  /**************************************************************************
          @PURPOSE      : delete pre images by requirement id
          @PARAMETERS   : requirement ID
          @RETURN       : NA
   ****************************************************************************/
  deleteReceipt(id) {
    this.commonService.callApi('attachments/' + id, '', 'delete', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
        this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        this.getReceiptInfo(this.invoiceId, 'UR');
        this.updateInvoice('DELETE')
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*********************************************************************************/

  viewReceipt: BsModalRef
  clickReceipt(template, id) {
    this.receiptImages(id);
    this.viewReceipt = this.modalService.show(template)
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
   @PURPOSE      : get pre-images by attachment id
   @PARAMETERS   : attachment ID
   @RETURN       : pre-images
  ****************************************************************************/
  receiptImages(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.uploadImage = [
        { name: successData.fileName, size: successData.fileSize }
      ]

      // Determine the file type based on the file extension
      const filename = successData.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + successData.fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + successData.fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /************************************************************************************/

  /**************************************************************************
          @PURPOSE      : get List of pre-images by invoice id
          @PARAMETERS   : invoice ID, upload for
          @RETURN       : attachmentDTO
   ****************************************************************************/
  receiptList: any = []
  getReceiptInfo(id, uploadFor) {
    this.commonService.callApi('attachments/invoice/' + id + '/' + uploadFor, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.receiptList = successData
      console.log(this.receiptList);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/
  public invoiceData: any = {}
  updateInvoice(type) {
    this.invoiceData.invoiceStatus = type == 'UPLOAD' ? "ADMREC" : 'PAYINI'
    this.invoiceData.lastModifiedBy = this.getToken('username');
    this.invoiceData.requirementDTO = {
      requirementId: this.requirementId
    }

    this.commonService.callApi("invoice", this.invoiceData, 'put', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
        this.invoiceStatus.emit(false)
        this.receiptTenplate.hide();
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  /****************************************************************************
    @PURPOSE      : get invoice details by invoiceId
    @PARAMETERS   : invoiceId
    @RETURN       : InvoiceDetailsDTO
 ****************************************************************************/
  getInvoiceById(id) {
    this.commonService.callApi('invoice/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.invoiceData = successData
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*******************************************************************************************/
  receiptData: any = {}
  isClickedOnce: boolean = false
  approveReceipt(receiptData) {
    if (!this.isClickedOnce) {
      this.isClickedOnce = true

      receiptData.invoiceId = this.receiptList[0].invoiceId
      receiptData.type = "Receipt Approval"
      receiptData.actionBy = this.getToken('username');
      receiptData.role = 'ADMIN'
      this.commonService.callApi('receipt/approval', receiptData, 'post', false, false, 'REG').then(success => {
        let successData: any = success
        console.log(successData);
        if (successData.body.apiStatusCode === "SUCCESS") {
          if (receiptData.status == 'APR') {
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.warningToastr("Receipt Details Rejected Successfully", "Rejected")
          }
          this.invoiceStatus.emit(false)
          this.receiptTenplate.hide();
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }

      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }

  }
}
