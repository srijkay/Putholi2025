
import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[NsplNegativeNumber]'
})
export class NsplNegativeNumberDirective {

  constructor(private _el: ElementRef) { }

  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = this._el.nativeElement.value;
    this._el.nativeElement.value = initalValue.replace(/(?!-)[^0-9]/g, '');
    if ( initalValue !== this._el.nativeElement.value) {
      event.stopPropagation();
    }
  }

}
