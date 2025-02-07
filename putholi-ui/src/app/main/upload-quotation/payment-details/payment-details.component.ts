import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrls: ['./payment-details.component.css']
})
export class PaymentDetailsComponent extends BaseComponent implements OnInit {

  @ViewChild('invoice') invoice: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();
  @Input() public invoiceId: any;
  @Input() public paymentInvoiceAmount: any
  colorTheme = 'theme-blue'

  dateOfJoinValue: Partial<BsDatepickerConfig>;
  constructor(inj: Injector) {
    super(inj)
    this.dateOfJoinValue = Object.assign({}, { containerClass: this.colorTheme, customTodayClass: 'custom-today-class', showWeekNumbers: false, dateInputFormat: 'DD-MM-YYYY', adaptivePosition: true, maxDate: new Date() });
  }

  ngOnInit(): void {
  }
  ngAfterViewInit() {
    this.invoicePayment.paidAmount = this.paymentInvoiceAmount
    this.invoice.show()
  }


  public invoicePayment: any = {}

  isBank: boolean = false;
  isCheque: boolean = false;
  internetBanking(type) {
    if (type == 'IB') {
      this.isBank = true;
      this.isCheque = false;
    } else {
      this.isBank = false;
      this.isCheque = true;
    }
  }


  saveInvoicePayment(paymentInvoice) {

    paymentInvoice.invoiceId = this.invoiceId
    this.commonService.callApi("invoicepaymentdetails/add", paymentInvoice, 'post', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
        this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        this.invoice.hide()
        this.modalStatus.emit(false)
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch((e) => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }



  clickCancel() {
    this.invoice.hide();
    this.modalStatus.emit(false)
  }
}
