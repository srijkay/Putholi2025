import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { NgModel } from '@angular/forms';

@Directive({
  selector: '[NsplAphaOnly]'
})
export class NsplAphaOnlyDirective {

  constructor(private _el: ElementRef, private ngModel: NgModel) { }

  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
    event.target.value = initalValue.replace(/[^a-zA-Z]/g, '');
    if (initalValue !== event.target.value) {
      event.stopPropagation();
      this._el.nativeElement.value = event.target.value;
      // Update the model value
      this.ngModel.update.emit(event.target.value);
    }
  }

}


