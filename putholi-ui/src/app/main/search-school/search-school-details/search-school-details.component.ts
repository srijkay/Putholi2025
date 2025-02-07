import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-search-school-details',
  templateUrl: './search-school-details.component.html',
  styleUrls: ['./search-school-details.component.css']
})
export class SearchSchoolDetailsComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id']
      this.schoolListById(id)
      this.getStatusList()
      this.getCountrList()
    })
  }

  ngOnInit(): void { }
}
