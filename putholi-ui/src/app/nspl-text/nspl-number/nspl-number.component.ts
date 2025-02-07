import { DecimalPipe } from '@angular/common';
import { ChangeDetectorRef, Component, Injector, Input, OnInit, Optional, Self } from '@angular/core';
import { ControlValueAccessor, NgControl } from '@angular/forms';
import { BaseComponent } from 'src/app/common/commonComponent';
declare var $: any
@Component({
  selector: 'app-nspl-number',
  templateUrl: './nspl-number.component.html',
  styleUrls: ['./nspl-number.component.css']
})
export class NsplNumberComponent extends BaseComponent implements OnInit, ControlValueAccessor {

  controlTabIndex: any
  @Input()
  public controlId: string;

  @Input() isErrorMsg = true

  @Input()
  public controlType: any;

  @Input()
  public name: any;

  @Input()
  public controlInput: any;

  @Input()
  public helpText: string


  @Input()
  public label: string;

  @Input()
  public placeholder: string;
  @Input()
  public required = false;

  @Input()
  public disabled = false;

  @Input()
  public data: any;

  @Input()
  public minlength = 0;

  @Input()
  public maxlength = 13;

  @Input()
  public minvalue: any;

  @Input()
  public maxvalue: any

  @Input()
  public submitted: boolean = false

  @Input()
  public noOfDecimals: any = '';
  private errorMessages = new Map<string, () => string>();

  public onChangeFn = (_: any) => { };

  public onTouchedFn = () => {
  };


  angularCurrency: any;
  currencyCode: any;
  currencySymbol: any



  autoComplete: any;
  showHelpText: boolean = false;
  textTransformation: any = "none";
  boColor = "#cccc";
  bgColor = "#ffffff";
  controlPattern: any
  errorText: any
  showCurrencyCode = false;
  showPayOrReceive = false;
  payOrReceive = 'Receive'
  showToggle = false;
  themeType = 'dark';
  controlValueChange: '='
  controlModified: '='
  controlPlaceHolder: '@'
  controlInvalid: any
  constructor(inj: Injector, @Self() @Optional() public control: NgControl, private ref: ChangeDetectorRef, private decimalpipe: DecimalPipe) {
    super(inj);
    this.control && (this.control.valueAccessor = this);

  }

  onFocus() {
    this.boColor = "#cccc";
    this.showHelpText = true;
  }

  ngOnInit(): void {
  }

  keyPress(event) {
    var inp = String.fromCharCode(event.keyCode);
    if (/[0-9]/.test(inp)) {
      return true;
    } else if (this.noOfDecimals != "") {
      if (/[_'.0-9]/.test(inp)) {
        return true
      } else {
        return false
      }
    } else {
      return false
    }
  }

  Error: any = '';
  public showErrorText: any = false
  formValue: any
  onBlur(value) {
    this.formValue = value
    if (this.required == true) {
      this.boColor = "#cccc"
      if (value == undefined || value == null || value.toString() == '') {
        this.Error = "This field cannot be left blank";
        if (this.showErrorText != true) {
          this.Error = "This field cannot be left blank";
        }
        if (this.isErrorMsg == false) {
          this.Error = ''
        }
        this.boColor = "#ac0c0c";
      } else {
        this.Error = ""

        // for decimal points
        if (this.data != undefined && this.noOfDecimals != '') {
          if (this.noOfDecimals == 2) {
            this.data = this.decimalpipe.transform(this.data, '1.2-2')
          } else {
            this.data = this.decimalpipe.transform(this.data, '1.1-2')
          }
        }

        if (value != undefined && value != null && value.toString() != '') {


          if ((this.minlength == this.maxlength) && (value.toString().length != this.maxlength)) {
            this.Error = "This field should be of " + this.maxlength + " digits";
            this.boColor = "#ac0c0c";
          } else if (value.toString().length < this.minlength) {
            this.Error = "This field should have minimum of " + this.minlength + " digits";
            this.boColor = "#ac0c0c";
          } else if (value.toString().length > this.maxlength) {
            this.Error = "This field should have maximum of " + this.maxlength + " digits";
            this.boColor = "#ac0c0c";
          } else if (Number(this.maxvalue) < Number(value)) {
            this.Error = "Maximum value for this field is " + this.maxvalue;
            this.boColor = "#ac0c0c";
          } else if (Number(value) < Number(this.minvalue)) {
            this.Error = "Minimum value for this field is " + this.minvalue;
            this.boColor = "#ac0c0c";
          } else {
            this.Error = "";
          }
        }
      }
    } else {
      this.boColor = "#cccc"
    }
    this.showHelpText = false;
  }

  negateControlModel() {
    this.data = -1 * this.data;
  }

  styleObject() {


    if ((this.formValue == undefined || this.formValue == null || this.formValue.toString() == '')) {
      if (this.submitted == true) {
        this.Error = "This field cannot be left blank";
        this.boColor = "#ac0c0c";
      }

    } else {
      this.boColor = "#cccc";
    }


    if (this.disabled == false) {
      return { 'background-color': this.bgColor, 'border-color': this.boColor, "text": this.textTransformation, "text-align": "right" }
    } else if (this.disabled == true) {
      this.bgColor = "#f5f5f5"
      return { 'background-color': this.bgColor, 'border-color': this.boColor, "text": this.textTransformation, "text-align": "right" }
    }
  }

  public registerOnChange(fn: any): void {
    this.onChangeFn = fn;
  }

  public registerOnTouched(fn: any): void {
    this.onTouchedFn = fn;
  }

  public setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }

  public writeValue(obj: any): void {
    this.data = obj;

    if (this.data != undefined && this.noOfDecimals != '') {
      if (this.noOfDecimals == 2) {
        this.data = this.decimalpipe.transform(this.data, '1.2-2')
      } else {
        this.data = this.decimalpipe.transform(this.data, '1.1-2')
      }
    }
  }

  public onChange() {
    let value = this.maxvalue
    this.onChangeFn(this.data);

    if (value != undefined) {
      $('.numberfield').on(function (e) {

        if ($(this).val() < value) {
          e.preventDefault();

          $(this).val(value);
        }
      });
    }

    if (this.data != undefined && this.noOfDecimals != '') {
      var decimallength = this.noOfDecimals
      $('input').on('keyup keydown change', function (e) {
        var text = $(this).val();
        if ((text.indexOf('.') != -1) && (text.substring(text.indexOf('.')).length > decimallength) && (e.which != 0 && e.which != 8)) {
          e.preventDefault();
        }
      })
    }
  }
}
