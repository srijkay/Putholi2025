import { Directive, ElementRef, HostListener, Renderer2 } from '@angular/core';
import { NgModel } from '@angular/forms';

@Directive({
  selector: '[NsplShortname]'
})
export class NsplShortnameDirective {
  domElement: any;
  constructor(private _el: ElementRef, private ngModel: NgModel) { }
  
  @HostListener('input', ['$event']) onInputChange(event) {
    const initalValue = event.target.value;
    event.target.value = initalValue.replace(/[^a-z0-9]/g, '');
    if (initalValue !== event.target.value) {
      event.stopPropagation();
      this._el.nativeElement.value = event.target.value;
      this.ngModel.update.emit(event.target.value); // Update the model value
    }
  }


}
