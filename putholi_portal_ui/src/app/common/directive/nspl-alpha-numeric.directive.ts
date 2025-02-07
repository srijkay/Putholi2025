
import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[NsplAlphaNumeric]'
})
export class NsplAlphaNumericDirective {

  constructor(private _el: ElementRef) { }

  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
    event.target.value = initalValue.replace(/[^a-zA-Z0-9]/g, '');
    if (initalValue !== event.target.value) {
      event.stopPropagation();
    }
  }

}
