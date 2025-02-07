import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';
import * as moment from 'moment';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { DatePipe } from '@angular/common';
declare var $: any;
@Component({
  selector: 'app-quotation-details',
  templateUrl: './quotation-details.component.html',
  styleUrls: ['./quotation-details.component.css']
})
export class QuotationDetailsComponent extends BaseComponent implements OnInit {
  @ViewChild('quotate') quotate: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();
  @Input() public quotationId: any;
  @Input() public quotateType: any
  @Input() public requirementId: any

  public uploadImage: any
  new_date: any;
  minDate = new Date();
  colorTheme = 'theme-blue'
  valid_date: any
  dateValidationConfig: Partial<BsDatepickerConfig>;
  dateOfJoinValue: Partial<BsDatepickerConfig>;


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


  constructor(inj: Injector, public datepipe: DatePipe) {
    super(inj);

    this.callStatusApi();

  }

  ngOnInit(): void {
    this.new_date = moment(new Date(), "DD-MM-YYYY").subtract(15, 'days').toDate();
    this.valid_date = moment(new Date(), "DD-MM-YYYY").add(91, 'days').toDate();
    this.dateValidationConfig = Object.assign({}, { containerClass: this.colorTheme, customTodayClass: 'custom-today-class', adaptivePosition: true, showWeekNumbers: false, dateInputFormat: 'DD-MM-YYYY', maxDate: new Date(), minDate: this.new_date });
    this.dateOfJoinValue = Object.assign({}, { containerClass: this.colorTheme, customTodayClass: 'custom-today-class', adaptivePosition: true, showWeekNumbers: false, dateInputFormat: 'DD-MM-YYYY' });

    if (this.quotateType == 'ADD') {
      this.quotateData.quotationDate = undefined
    } else {
      this.quotationInfoByQuotateId(this.quotationId)
      this.quotationImagesByQuotationId(this.quotationId)
    }
  }


  ngAfterViewInit() {
    this.quotate.show()

    var selector = ".form-group"

    if ($(selector).hasClass('AAA')) {
      console.log("iffff098765");

    }
  }


  clickCancel() {
    this.quotate.hide();
    this.modalStatus.emit(false)
  }
  /****************************************************************************
       @PURPOSE      : To add/edit upload quotate
       @PARAMETERS   : form,formdata
       @RETURN       : NA
 ****************************************************************************/
  quotateImage: any
  public quotateData: any = {}
  urlMethod: any
  quotationFormStartsWithZero: boolean = false
  onSubmit(form, quotateData) {
    console.log(quotateData.pincode);


    quotateData.requirementDTO = {
      requirementId: this.requirementId
    }
    quotateData.quotationDate = new Date(quotateData.quotationDate)
    quotateData.quotationValidDate = new Date(quotateData.quotationValidDate)
    quotateData.warranty = quotateData.days + quotateData.months
    if (form.valid) {
      if (this.quotateType == 'ADD') {
        this.urlMethod = "post"
        quotateData.quotateStatus = "ADMQUO"
        quotateData.createdBy = this.getToken('username')
      } else {
        this.urlMethod = "put"

        quotateData.quotateStatus = "ADMQUO"
        quotateData.lastModifiedBy = this.getToken('username')
      }
      console.log(quotateData);

      this.commonService.callApi("quotation", quotateData, this.urlMethod, false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode == "SUCCESS") {
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          this.uploadPreImages(successData.body.id)
          this.quotate.hide();
          this.modalStatus.emit(false)

        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch((e) => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }

  }

  /*******************************************************************/


  /**************************************************************************
          @PURPOSE      : ADD/UPDATE pre-images by requirement id
          @PARAMETERS   : requirement ID, image
          @RETURN       : NA
   ****************************************************************************/
  requirementPreImages: any = {}
  fileType: any
  uploadPreImages(id) {

    if (this.uploadImage[0].type) {
      const sendData = new FormData();
      let api_Type: any;
      this.fileType = 'QI'
      if (this.quotateType == 'ADD') {
        this.requirementPreImages.requirementId = this.requirementId;
        this.requirementPreImages.quotationId = id;
        let data = JSON.stringify(this.requirementPreImages);
        sendData.append('QuotationAttachmentsDTO', data);
        sendData.append('fileType ', this.fileType)
        sendData.append('quotation', this.uploadImage[0])
        api_Type = 'post'
      } else {
        let quotData: any = {}
        quotData.requirementId = this.requirementId;
        quotData.attachmentId = this.quotateAttachment.attachmentId;
        quotData.quotationId = id;
        let data = JSON.stringify(quotData);
        sendData.append('QuotationAttachmentsDTO', data);
        sendData.append('fileType ', this.fileType)
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


  /********************************************************************************/



  /**************************************************************************
         @PURPOSE      : get quotation info by quotate id
         @PARAMETERS   : quotate ID
         @RETURN       : UploadQuotationDTO
  ****************************************************************************/
  approvalHistDtlsDTOs: any = []
  quotationInfoByQuotateId(id) {
    this.commonService.callApi('quotation/approval/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.quotateData = successData.quotationInfo;
      this.approvalHistDtlsDTOs = successData.schoolApprovalHistoryDTO
      this.quotateData.quotationDate = new Date(this.quotateData.quotationDate);
      this.quotateData.quotationValidDate = new Date(this.quotateData.quotationValidDate);
      this.quotateData.days = this.quotateData.warranty.match(/[0-9]+/)[0]
      this.quotateData.months = this.quotateData.warranty.match(/[a-zA-Z]+/)[0]
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/



  /**************************************************************************
       @PURPOSE      : get quotation images by quotate id
       @PARAMETERS   : quotate ID
       @RETURN       : AttachmentDTO
****************************************************************************/
  base64Image: any
  quotateAttachment: any
  quotationImagesByQuotationId(id) {
    this.commonService.callApi('attachments/quotate/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.quotateAttachment = successData
      this.base64Image = 'data:image/png;base64,' + this.quotateAttachment.fileData;
      this.uploadImage = [
        { name: successData.fileName, size: successData.fileSize }
      ]
      console.log(this.uploadImage);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/
  isissueValid: boolean = false;
  isQuotateDate: boolean = false;
  onValueChange(e, type) {

    if (e != null && type == 'VALID') {
      console.log(this.datepipe.transform(this.dateOfJoinValue.minDate, "dd-MM-yyyy") < this.datepipe.transform(e, "dd-MM-yyyy"));

      if (this.datepipe.transform(e, "dd-MM-yyyy") < this.datepipe.transform(this.dateOfJoinValue.minDate, "dd-MM-yyyy")) {
        this.isissueValid = true;
        console.log("if", this.isissueValid);
      } else {
        this.isissueValid = false
        console.log("else", this.isissueValid);
      }
    } else if (e != null && type == 'QUOT') {
      this.quotateData.quotationValidDate = ''
      if (e > new Date() || e < this.datepipe.transform(this.new_date, "dd-MM-yyyy")) {
        this.isQuotateDate = true
      } else {
        this.isQuotateDate = false
      }

      this.dateOfJoinValue.minDate = moment(e, "dd-MM-yyyy").add(91, 'days').toDate()
    }

  }

}
