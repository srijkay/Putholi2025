
import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from '../common/commonComponent';
@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.css']
})
export class ErrorPageComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
  }

  ngOnInit(): void {

  }

}
