import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BaseComponent } from 'src/app/common/commonComponent';
import * as moment from 'moment';
import { BsModalRef, ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-invoice-view',
  templateUrl: './invoice-view.component.html',
  styleUrls: ['./invoice-view.component.css']
})
export class InvoiceViewComponent extends BaseComponent implements OnInit {
  @ViewChild('viewInvoice') viewInvoice: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();
  @Input() public invoiceId: any;

  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.callStatusApi();
    this.getListofsports()
    this.getAccountDetails()
    this.getPaymentDetails()
    this.getCardDetails()
    this.getRoles()
    this.getBankName()
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.getInvoiceById(this.invoiceId)
    this.viewInvoice.show()
  }

  clickCancel() {
    this.viewInvoice.hide();
    this.modalStatus.emit(false)
  }

  /****************************************************************************
    @PURPOSE      : get invoice details by invoiceId
    @PARAMETERS   : invoiceId
    @RETURN       : InvoiceDetailsDTO
 ****************************************************************************/
  invoiceData: any = {}
  public approvalHistDtlsDTOs: any = []
  getInvoiceById(id) {
    this.commonService.callApi('invoice/approval/' + id + '/Invoice Approval', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.invoiceData = successData.invoiceDetails
      this.invoiceData.bankName = this.bankList.find(x => x.code == this.invoiceData.bankName)
      this.invoiceData.workStatus = this.PaymentDetails.find(x => x.code == this.invoiceData.workStatus)
      this.invoiceData.accountType = this.AccountDetails.find(x => x.code == this.invoiceData.accountType)
      this.invoiceData.paymentMode = this.cardDetails.find(x => x.code == this.invoiceData.paymentMode)


      this.approvalHistDtlsDTOs = successData.schoolApprovalHistoryDetails;

      let type = this.rolesList.filter(o1 => this.approvalHistDtlsDTOs.some(o2 => o1.code === o2.role))
      console.log(type);

      this.approvalHistDtlsDTOs.forEach(function (checkbox) {
        type.forEach(e => {
          if (e.code == checkbox.role)
            checkbox.role = e.description
        });
      })
      this.invoiceData.invoiceDate = moment(this.invoiceData.invoiceDate).format("DD/MM/YYYY");

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*******************************************************************************************/

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

  uploadImage: any;
  /**************************************************************************
         @PURPOSE      : get Invoice images by invoice id
         @PARAMETERS   : invoice ID
         @RETURN       : AttachmentDTO
  ****************************************************************************/
  invoiceImages: any = {}
  getImagesByInvoiceId(id) {
    this.commonService.callApi('attachments/invoice/' + id + '/II', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.invoiceImages = successData
      this.uploadImage = this.invoiceImages.fileData
      
      // Determine the file type based on the file extension
      const filename =  this.invoiceImages[0].fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' +  this.invoiceImages[0].fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' +  this.invoiceImages[0].fileData;
      }
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
     @PURPOSE      :open modal to view invoice image
     @PARAMETERS   : template id
     @RETURN       : NA
****************************************************************************/
  modalRef: any
  openImage(template) {
    this.getImagesByInvoiceId(this.invoiceId)
    this.viewInvoice.hide();
    this.modalRef = template
    template.show()
  }
  /***********************************************************************************/

  closeImages() {
    this.modalRef.hide()
    this.viewInvoice.show()
  }

}
