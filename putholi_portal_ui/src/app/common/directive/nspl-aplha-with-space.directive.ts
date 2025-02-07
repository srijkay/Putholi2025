
import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[NsplAplhaWithSpac]'
})
export class NsplAplhaWithSpaceDirective {

  constructor(private _el: ElementRef) { }

  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
    event.target.value = initalValue.replace(/[^a-zA-Z\s]/g, '');
    if ( initalValue !== event.target.value) {
      event.stopPropagation();
    }
  }

}
