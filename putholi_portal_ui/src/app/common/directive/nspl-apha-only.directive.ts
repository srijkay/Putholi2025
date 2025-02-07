import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[NsplAphaOnly]'
})
export class NsplAphaOnlyDirective {

  constructor(private _el: ElementRef) { }

  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
    event.target.value = initalValue.replace(/[^a-zA-Z]/g, '');
    if (initalValue !== event.target.value) {
      event.stopPropagation();
    }
  }

}


