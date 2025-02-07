import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../common/commonComponent';

@Component({
  selector: 'app-refund-cancellation-policy',
  templateUrl: './refund-cancellation-policy.component.html',
  styleUrls: ['./refund-cancellation-policy.component.css']
})
export class RefundCancellationPolicyComponent extends BaseComponent implements OnInit {

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
