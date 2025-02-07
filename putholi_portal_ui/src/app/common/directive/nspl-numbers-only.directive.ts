
import { Directive, ElementRef, HostListener, Input } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[NsplNumbersOnly]'
})
export class NumbersOnlyDirective {

  constructor(private _el: ElementRef) { }

  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
   event.target.value = initalValue.replace(/[^0-9]/g, '');
    if ( initalValue !==event.target.value) {
      event.stopPropagation();
    }
  }

}