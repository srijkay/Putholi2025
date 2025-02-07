import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild, Optional, Self, HostListener } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { BaseComponent } from '../common/commonComponent';
import { ControlValueAccessor, NgControl } from "@angular/forms";
declare var $: any;
@Component({
  selector: 'app-nspl-text',
  templateUrl: './nspl-text.component.html',
  styleUrls: ['./nspl-text.component.css']
})
export class NsplTextComponent extends BaseComponent implements OnInit, ControlValueAccessor {
  @Output() email: EventEmitter<any> = new EventEmitter();

  @Input() controlId: string;
  @Input() controlType: any = "text";

  @Input() isErrorMsg = true

  @Input()
  public controlInput: any;

  @Input()
  public helpText: string

  @Input()
  public isStyleApplied = false;

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
  public maxlength = 500;

  @Input()
  public minvalue: any;

  @Input()
  public maxvalue: any

  @Input()
  public submitted: boolean = false

  private errorMessages = new Map<string, () => string>();

  public onChangeFn = (_: any) => { };

  public onTouchedFn = () => { };




  autoComplete: any;
  showHelpText: boolean = false;
  textTransformation: any = "none";
  boColor = "#cccc";
  bgColor = "#ffffff";
  controlPattern: any

  form: NgForm
  controlValueChange: '='

  themeType = 'dark'

  controlModified: '='
  controlPlaceHolder: '@'

  constructor(inj: Injector, @Self() @Optional() public control: NgControl) {
    super(inj);
    this.control && (this.control.valueAccessor = this);
  }



  ngOnInit(): void {
    if (this.controlInput === 'ShortName') {
      this.textTransformation = "uppercase";
    }
    if (this.controlInput === "Small") {
      this.textTransformation = "lowercase";
    }
    if (this.required == true && this.required != undefined) {
      this.boColor = "#cccc";
      this.bgColor = "#ffffff";
    } else {
      this.boColor = "#cccc";
      this.bgColor = "#ffffff";
    }
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
      return { 'background-color': "#fff", 'border-color': this.boColor, "text-transform": this.textTransformation }
    } else if (this.disabled == true) {
      this.bgColor = "#f5f5f5"
      if (this.isStyleApplied == true) {
        this.boColor = "#BB8557"
      }
      return { 'background-color': this.bgColor, 'border-color': this.boColor, "text-transform": this.textTransformation }
    }
  }

  key: any;
  restrictionType(e) {
    if (this.controlInput === 'ShortName') {
      this.textTransformation = "uppercase";
      var inp = String.fromCharCode(e.keyCode);
      if (/[a-zA-Z]/.test(inp)) {
        return true;
      } else {
        return false;
      }
    } else if (this.controlInput === "AlphaOnly") {
      var inp = String.fromCharCode(e.keyCode);
      if (/[a-zA-Z]/.test(inp)) {
        return true;
      } else {
        return false;
      }
    } else if (this.controlInput === "Small") {
      this.textTransformation = "lowercase";
      var inp = String.fromCharCode(e.keyCode);
      if (/[a-z0-9]+$/i.test(inp)) {
        return true;
      } else {
        return false;
      }
    } else if (this.controlInput === "Number") {
      var inp = String.fromCharCode(e.keyCode);
      if (/[0-9]/.test(inp)) {
        return true;
      } else {
        return false;
      }
    } else if (this.controlInput === "AlphaWithSpace") {
      var inp = String.fromCharCode(e.keyCode);
      if (/[a-zA-Z\s]/.test(inp)) {
        return true;
      } else {
        return false;
      }
    } else if (this.controlInput === "Alphanumeric") {
      var inp = String.fromCharCode(e.keyCode);
      if (/[a-zA-Z0-9]/g.test(inp)) {
        return true;
      } else {
        return false;
      }
    } else if (this.controlInput === "alphanumericWithCaps") {
      this.textTransformation = "uppercase";
      var inp = String.fromCharCode(e.keyCode);
      if (/[a-zA-Z0-9]/g.test(inp)) {
        return true;
      } else {
        return false;
      }
    }
  }

  onFocus() {
    this.boColor = "#3f51b5";
    this.showHelpText = true;
  }

  isValidEmail: boolean = false;

  Error: any = '';
  formValue: any
  onBlur(value) {




    this.boColor = "#cccc";
    if (this.required == true) {
      if (value == undefined || value == null || value.toString() == '') {
        this.Error = "This field cannot be left blank";
        this.boColor = "#ac0c0c";
      } else {
        this.Error = ""
        this.boColor = "#cccc"
        
        console.log(value);
        value = value.trim(); // Trim leading and trailing spaces
        console.log(value);

      }

    } else {
      this.boColor = "#cccc"
    }


    if (value != undefined && value != null && value.toString() != '' && value.trim().length != 0) {

      if ((this.minlength == this.maxlength) && (value.toString().length != this.maxlength)) {
        this.Error = "This field should be of " + this.maxlength + " digits";
        this.boColor = "#ac0c0c";
      } else if (value.toString().length < this.minlength) {
        this.Error = "This field should have minimum of " + this.minlength + " digits";
        this.boColor = "#ac0c0c";
      } else if (value.toString().length > this.maxlength) {
        this.Error = "This field should have maximum of " + this.maxlength + " digits";
        this.boColor = "#ac0c0c";
      } else if (Number(value) > Number(this.maxvalue)) {
        this.Error = "Maximum value for this field is " + this.maxvalue;
        this.boColor = "#ac0c0c";
      } else if (Number(value) < Number(this.minvalue)) {
        this.Error = "Minimum value for this field is " + this.minvalue;
        this.boColor = "#ac0c0c";
      } else {
        this.Error = "";
      }
      if (this.controlInput == "Email") {
        if (!((value.indexOf('@') > -1) && (value.indexOf('.') > -1))) {
          this.isValidEmail = false
          this.email.emit(false)
          this.Error = "Invalid email id";
          this.boColor = "#ac0c0c";
        } else {
          this.Error = "";
          this.isValidEmail = true
          this.email.emit(true)
        }
      }
    }
    this.helpText = "";
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
  }

  public onChange() {
    this.onChangeFn(this.data);
  }

  // @HostListener('input', ['$event']) onInputChange(event) {
  //   console.log("event", event.target.value);

  //   if (event.target.value.length == 1) {
  //     event.stopPropagation();
  //     event.target.value = event.target.value.trim();
  //     console.log(event.target.value.trim());

  //   }
  // }
}