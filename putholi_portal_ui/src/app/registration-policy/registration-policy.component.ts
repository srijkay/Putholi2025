import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../common/commonComponent';

@Component({
  selector: 'app-registration-policy',
  templateUrl: './registration-policy.component.html',
  styleUrls: ['./registration-policy.component.css']
})
export class RegistrationPolicyComponent extends BaseComponent implements OnInit {

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
