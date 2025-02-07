import { Component, EventEmitter, Injector, Input, OnInit, Optional, Output, Self } from '@angular/core';
import { ControlValueAccessor, NgControl, NgForm } from '@angular/forms';
import { FileRestrictions } from '@progress/kendo-angular-upload';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-nspl-file-browse',
  templateUrl: './nspl-file-browse.component.html',
  styleUrls: ['./nspl-file-browse.component.css']
})
export class NsplFileBrowseComponent extends BaseComponent implements OnInit, ControlValueAccessor {

  @Input()
  public data: any = {};

  @Input()
  public maxFileSize: any = 1000000

  @Input()
  public allowedExtensions: any = []

  @Input()
  public accept: any;

  @Input()
  public required: boolean = true;

  @Input()
  public disabled: boolean = false;

  @Input()
  public helpText: string;

  @Input()
  public isErrorMsg: boolean = true;

  @Input()
  public maxlength: any;

  @Input()
  public minlength: any

  @Input()
  public submitted: boolean = false

  public onChangeFn = (_: any) => { };

  public onTouchedFn = () => { };

  showHelpText: boolean = false;

  boColor = "#cccc";
  bgColor = "#ffffff";

  form: NgForm
  controlValueChange: '='

  constructor(inj: Injector, @Self() @Optional() public control: NgControl) {
    super(inj);
    this.control && (this.control.valueAccessor = this);

    this.accept = "image/png, image/jpg, image/jpeg, application/pdf"
    this.allowedExtensions = ['.png', '.jpg', '.jpeg', '.pdf']
  }
  ngOnInit(): void {

    setTimeout(() => {
      this.myRestrictions = {
        maxFileSize: this.maxFileSize,
        allowedExtensions: this.allowedExtensions,
      }

      console.log(this.myRestrictions);
    }, 500);


  }


  public myRestrictions: FileRestrictions;


  Error: any = '';
  public showErrorText: any = false
  formValue: any
  blur(data) {
    this.formValue = data
    this.boColor = "#cccc";
    if (this.required == true) {
      if (data == undefined || data == null || data.length == '') {
        if (this.isErrorMsg == true)
          this.Error = "This field cannot be left blank";
        else
          this.Error = ''
        this.boColor = "#ac0c0c";

      } else {
        this.Error = ""

        if (data != undefined && data != null && data != '') {
          if ((this.minlength == this.maxlength) && (data.length != this.maxlength)) {
            this.Error = "This field should be of " + this.maxlength + " files";
            this.boColor = "#ac0c0c";

          } else if (data.length < this.minlength) {
            this.Error = "This field should have minimum of " + this.minlength + " files";
            this.boColor = "#ac0c0c";

          } else if (data.length > this.maxlength) {
            this.Error = "This field should have maximum of " + this.maxlength + " files";
            this.boColor = "#ac0c0c";

          } else {
            this.Error = "";
          }
        }
      }
    }
  }



  public writeValue(obj: any): void {
    this.data = obj;
  }

  public onChange() {
    this.onChangeFn(this.data);
  }

  public registerOnChange(fn: any): void {
    this.onChangeFn = fn;
  }

  public registerOnTouched(fn: any): void {
    this.onTouchedFn = fn;
  }

  styleObject() {


    // if ((this.formValue == undefined || this.formValue == null || this.formValue.toString() == '')) {
    //   if (this.submitted == true) {
    //     this.Error = "This field cannot be left blank";
    //     this.boColor = "#ac0c0c";
    //   }

    // } else {
    //   this.boColor = "#cccc";
    // }


    if (this.disabled == false) {
      return { 'background-color': this.bgColor, 'border-color': this.boColor, }
    } else if (this.disabled == true) {
      this.bgColor = "#f5f5f5"

      return { 'background-color': this.bgColor, 'border-color': this.boColor }
    }
  }
}
