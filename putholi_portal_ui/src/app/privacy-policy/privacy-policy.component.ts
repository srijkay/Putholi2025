import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../common/commonComponent';

@Component({
  selector: 'app-privacy-policy',
  templateUrl: './privacy-policy.component.html',
  styleUrls: ['./privacy-policy.component.css']
})
export class PrivacyPolicyComponent extends BaseComponent implements OnInit {

 
  constructor(inj: Injector) {
    super(inj)

  }

  ngOnInit(): void {
    window.onscroll = function () { scrollFunction() };
    function scrollFunction() {
      this.loadScript('assets/js/main.js');
    }
  }

}
