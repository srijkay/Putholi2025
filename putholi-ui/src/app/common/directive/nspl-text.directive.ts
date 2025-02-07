import { Directive, Input, ElementRef, Renderer2, HostListener } from '@angular/core';



@Directive({
  selector: '[nsplText]'
})


export class NsplTextDirective {

  @Input() csplField: any;
  private element:HTMLInputElement;
  constructor(private renderer: Renderer2, private targetElem: ElementRef) {
      this.element=targetElem.nativeElement;


  }


  ngOnInit(){
    if(this.csplField === "shortName"){
        this.renderer.addClass(this.targetElem.nativeElement,'cspl-text-color')
    }
  }


}