import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[NsplSmall]'
})
export class NsplSmallDirective {

  constructor() { }
  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
    event.target.value = initalValue.replace(/[^a-zA-Z0-9]/g, '');
    if (initalValue !== event.target.value) {
      event.stopPropagation();
    }
  }
}
