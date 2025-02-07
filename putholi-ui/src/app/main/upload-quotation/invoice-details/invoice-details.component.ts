import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';
import * as moment from 'moment';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { NgPopupsService } from 'ng-popups';

@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrls: ['./invoice-details.component.css']
})
export class InvoiceDetailsComponent extends BaseComponent implements OnInit {

  @ViewChild('invoice') invoice: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();
  @Input() public invoiceId: any;
  @Input() public invoiceType: any
  @Input() public requirementId: any
  @Input() public quotationInfo: any
  @Input() invoiceTotalAmount: any

  new_date: any;
  minDate = new Date();
  colorTheme = 'theme-blue'
  invoiceValidDate: Partial<BsDatepickerConfig>;

  constructor(inj: Injector, private ngPopups: NgPopupsService) {
    super(inj)
    this.callStatusApi();
    this.getAccountDetails()
    this.getCardDetails()
    this.getPaymentDetails()
    this.getBankName();
    this.getStatusList()
    this.invoiceValidDate = Object.assign({}, { containerClass: this.colorTheme, customTodayClass: 'custom-today-class', showWeekNumbers: false, dateInputFormat: 'DD-MM-YYYY', adaptivePosition: true, maxDate: new Date() });

  }

  ngOnInit(): void {
    if (this.invoiceType != 'ADD') {
      this.getInvoiceById(this.invoiceId)
      this.getImagesByInvoiceId(this.invoiceId)
    }

    let data: any = JSON.parse(this.quotationInfo)
    this.invoiceData = data
    this.invoiceData.quotedUnitPrice = data.unitPrice;
    this.invoiceData.quotedTax = data.tax
    this.invoiceData.quotedShippingCost = data.shippingCost
    this.invoiceData.quotedAmount = data.totalAmount
  }

  ngAfterViewInit() {
    this.invoice.show()
  }


  clickCancel() {
    this.invoice.hide();
    this.modalStatus.emit(false)
  }
  /****************************************************************************
    @PURPOSE      :open modal for quotations
    @PARAMETERS   : template id
    @RETURN       : NA
****************************************************************************/
  apiMethod: any;
  invoiceData: any = {}
  onSubmitInvoice(form, invoiceData) {
    setTimeout(() => {
      invoiceData.requirementDTO = {
        requirementId: this.requirementId
      }
      !this.workStatus ? this.checkworkStatus() : ''

      if (this.workStatus) {

        invoiceData.invoiceDate = new Date(invoiceData.invoiceDate)
        if (this.invoiceType == 'ADD') {
          invoiceData.invoiceStatus = "ADMINV"
          invoiceData.createdBy = this.getToken('username');
          this.apiMethod = "post"
        } else {
          invoiceData.invoiceStatus = "ADMINV";
          invoiceData.lastModifiedBy = this.getToken('username');
          this.apiMethod = "put"
        }
        this.commonService.callApi("invoice", invoiceData, this.apiMethod, false, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode == "SUCCESS") {
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            this.uploadPreImages(successData.body.id)
            this.invoice.hide()
            this.modalStatus.emit(false)
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch((e) => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }
    }, 500);
  }
  /*************************************************************************************/

  /**************************************************************************
            @PURPOSE      : ADD/UPDATE pre-images by requirement id
            @PARAMETERS   : requirement ID, image
            @RETURN       : NA
  ****************************************************************************/
  requirementPreImages: any = {}
  uploadImage: any
  fileType: any
  uploadPreImages(id) {
    const sendData = new FormData();
    let api_Type: any;
    if (this.uploadImage[0].type) {
      if (this.invoiceType == 'ADD') {
        this.requirementPreImages.requirementId = this.requirementId;
        this.requirementPreImages.invoiceId = id

        let data = JSON.stringify(this.requirementPreImages);
        sendData.append('QuotationAttachmentsDTO', data);
        sendData.append('fileType ', "II")
        sendData.append('quotation', this.uploadImage[0])
        api_Type = 'post'
      } else {
        let quotData: any = {}
        quotData.requirementId = this.requirementId;
        quotData.attachmentId = this.invoiceImages.attachmentId;
        quotData.invoiceId = id

        let data = JSON.stringify(quotData);
        sendData.append('QuotationAttachmentsDTO', data);
        sendData.append('fileType', "II")
        sendData.append('quotation', this.uploadImage[0])
        api_Type = 'put'
      }
      this.commonService.callApi('attachments', sendData, api_Type, true, false, 'REG').then(success => {
        let successData: any = success;
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }
  }

  /***************************************************************************/

  /****************************************************************************
    @PURPOSE      : get invoice details by invoiceId
    @PARAMETERS   : invoiceId
    @RETURN       : InvoiceDetailsDTO
 ****************************************************************************/
  getInvoiceById(id) {
    this.commonService.callApi('invoice/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.invoiceData = successData
      this.invoiceData.invoiceDate = new Date(this.invoiceData.invoiceDate);
      this.invoiceTotalAmount += this.invoiceData.invoiceAmount

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /**************************************************************************/

  base64Image: any
  /**************************************************************************
         @PURPOSE      : get Invoice images by invoice id
         @PARAMETERS   : invoice ID
         @RETURN       : AttachmentDTO
  ****************************************************************************/
  invoiceImages: any
  getImagesByInvoiceId(id) {
    this.commonService.callApi('attachments/invoice/' + id + '/II', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.invoiceImages = successData[0]
      this.base64Image = 'data:image/png;base64,' + this.invoiceImages.fileData;
      this.uploadImage = [
        { name: this.invoiceImages.fileName, size: this.invoiceImages.fileSize }
      ]
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/

  workStatus: boolean = false
  checkworkStatus() {
    if (this.invoiceData.invoiceAmount == this.invoiceTotalAmount) {
      if (this.invoiceData.workStatus == 'FC') {
        this.workStatus = true
      } else {
        this.toastr.errorToastr("Please choose the proper Work status", "Error")
        this.workStatus = false
      }
    } else if (this.invoiceData.invoiceAmount != this.invoiceTotalAmount) {
      if (this.invoiceData.workStatus == 'PC') {
        this.workStatus = true
      } else {
        let deleteMsg: any = "Entered invoice amount is  " + this.invoiceData.invoiceAmount + "  and you have chosen status as fully completed. Are you sure you want to proceed? if so, please note you can't raise invoice for this requirement anymore."
        this.ngPopups.confirm(deleteMsg)
          .subscribe(res => {
            if (res == true) {
              this.workStatus = true
              this.onSubmitInvoice(true, this.invoiceData)
            } else {
              this.workStatus = false
            }
          })
      }
    }
    return this.workStatus
  }
}
