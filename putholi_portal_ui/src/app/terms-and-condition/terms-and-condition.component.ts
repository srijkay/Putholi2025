import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../common/commonComponent';

@Component({
  selector: 'app-terms-and-condition',
  templateUrl: './terms-and-condition.component.html',
  styleUrls: ['./terms-and-condition.component.css']
})
export class TermsAndConditionComponent extends BaseComponent implements OnInit {

  constructor(inj:Injector) { 
    super(inj)
  }

  ngOnInit(): void {
    window.onscroll = function () { scrollFunction() };
    function scrollFunction() {
      this.loadScript('assets/js/main.js');
    }
  }

}
