
import { Component, OnInit, forwardRef } from "@angular/core";
import {
  ControlValueAccessor,
  FormControl,
  NG_VALUE_ACCESSOR
} from "@angular/forms";

@Component({
  selector: 'app-nspl-checkbox',
  templateUrl: './nspl-checkbox.component.html',
  styleUrls: ['./nspl-checkbox.component.css'],

  // Step 1: copy paste this providers property
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => NsplCheckboxComponent),
      multi: true
    }
  ]
})
// Step 2: Add "implements ControlValueAccessor"
export class NsplCheckboxComponent implements ControlValueAccessor, OnInit {
  // Step 3: Copy paste this stuff here
  onChange: any = () => {};
  onTouch: any = () => {};

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouch = fn;
  }

  constructor() {}

  ngOnInit() {}

  // Step 4: Define what should happen in this component, if something changes outside
  checked: boolean = false;
  writeValue(checked: boolean) {
    this.checked = checked;
  }


  onModelChange(e: boolean) {
    // Step 5a: bind the changes to the local value
    this.checked = e;

    // Step 5b: Handle what should happen on the outside, if something changes on the inside
    this.onChange(e);
  }
}
