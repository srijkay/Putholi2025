
import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { NgControl, NgModel } from '@angular/forms';

@Directive({
  selector: '[NsplNumbersOnly]'
})
export class NumbersOnlyDirective {

  constructor(private _el: ElementRef, private ngModel: NgModel) { }

  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
    const newValue = initalValue.replace(/[^0-9]/g, '');
    if (initalValue !== newValue) {
      event.stopPropagation();
      this._el.nativeElement.value = newValue;
      this.ngModel.update.emit(newValue); // Update the model value
    }
  }

}