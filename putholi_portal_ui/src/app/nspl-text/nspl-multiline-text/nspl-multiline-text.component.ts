import { Component, Input, OnInit, Optional, Self } from '@angular/core';
import { ControlValueAccessor, NgControl } from '@angular/forms';

@Component({
  selector: 'app-nspl-multiline-text',
  templateUrl: './nspl-multiline-text.component.html',
  styleUrls: ['./nspl-multiline-text.component.css']
})
export class NsplMultilineTextComponent implements OnInit,ControlValueAccessor {
  
  @Input()
  public data: any;

  @Input()
  public isErrorMsg: boolean = true

  @Input()
  public placeholder: any = ""

  @Input()
  public minlength = 0;

  @Input()
  public helpText: string

  @Input()
  public maxlength = 500;

  @Input()
  public minvalue: any;

  @Input()
  public maxvalue: any;

  @Input()
  public required = false;

  @Input()
  public disabled = false;

  @Input()
  public rows = 4;

  @Input()
  public cols = 7;

  private errorMessages = new Map<string, () => string>();

  public onChangeFn = (_: any) => { };

  public onTouchedFn = () => {

  };
  Error: any = ""
  showHelpText = false
  textTransformation: any = "none";
  boColor = "#cccc";
  bgColor = "#ffffff";
  constructor(@Self() @Optional() public control: NgControl) {
    this.control && (this.control.valueAccessor = this);

  }

  ngOnInit(): void {
    if (this.required == true && this.required != undefined) {
      this.boColor = "#cccc";
      this.bgColor = "#ffffff";
    } else {
      this.boColor = "#cccc";
      this.bgColor = "#ffffff";
    }
  }

  onFocus() {
    this.boColor = "#3f51b5";
    this.showHelpText = true;
  }


  styleObject() {
    if (this.disabled == false) {
      return { 'background-color': this.bgColor, 'border-color': this.boColor, "text-transform": this.textTransformation }
    } else if (this.disabled == true) {
      this.bgColor = "#f5f5f5"
      return { 'background-color': this.bgColor, 'border-color': this.boColor, "text-transform": this.textTransformation }
    }
  }
  onBlur(value) {
    if (this.required == true) {
      this.boColor = "#cccc";
      if (value == undefined || value == null || value.toString() == '') {
        this.Error = "This field cannot be left blank";
        this.boColor = "#ac0c0c";
        if (this.isErrorMsg == false) {
          this.Error = ''
        }
      } else {
        this.Error = ""
      }
    } else {
      this.boColor = "#cccc"
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
  }

  public onChange() {
    this.onChangeFn(this.data);
  }


}
