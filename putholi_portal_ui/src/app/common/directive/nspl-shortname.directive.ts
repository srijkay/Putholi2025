import { Directive, ElementRef, HostListener, Renderer2 } from '@angular/core';

@Directive({
  selector: '[NsplShortname]'
})
export class NsplShortnameDirective {
  domElement: any;
  constructor() {  }
  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
    event.target.value = initalValue.replace(/[^a-zA-Z]/g, '');
    if (initalValue !== event.target.value) {
      event.stopPropagation();
    }
  }


}
