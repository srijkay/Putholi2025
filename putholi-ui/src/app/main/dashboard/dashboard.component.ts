import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
declare var $: any;
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent extends BaseComponent implements OnInit {

  constructor(inj:Injector) {
    super(inj)
    this.removeToken('acceptVolunteer')
    this.removeToken('uploadquotate')
    this.callStatusApi();
   }

  ngOnInit(): void {

    if ($('li.active')) {
      $('li').removeClass('nav-item-open')
      $('ul.nav-group-sub').css('display', 'none')
    }
  }




}
