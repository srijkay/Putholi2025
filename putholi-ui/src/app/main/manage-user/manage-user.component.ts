import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manage-user',
  template:  `<router-outlet></router-outlet>`,
  styleUrls: ['./manage-user.component.css']
})
export class ManageUserComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
