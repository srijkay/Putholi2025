import { Component, OnInit, Injector, Input } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
@Component({
  selector: 'app-nspl-table-export',
  templateUrl: './nspl-table-export.component.html',
  styleUrls: ['./nspl-table-export.component.css']
})
export class NsplTableExportComponent extends BaseComponent implements OnInit {


  constructor(inj: Injector) {
    super(inj)
  }

  ngOnInit(): void {
  }

}
