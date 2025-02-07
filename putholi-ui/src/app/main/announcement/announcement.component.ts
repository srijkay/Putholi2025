import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-announcement',
  template: `<router-outlet></router-outlet>`,
  styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj);
   
  }

  ngOnInit(): void {
  }


}
