import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-deo-code-view',
  templateUrl: './deo-code-view.component.html',
  styleUrls: ['./deo-code-view.component.css']
})
export class DeoCodeViewComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
  }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      let CodeId: any = params['id']
      this.getDeoView(CodeId);
    })
  }


  /****************************************************************************
      @PURPOSE      : To get deo details by using Id
      @PARAMETERS   : deoId
      @RETURN       : NA
  ****************************************************************************/

  DeoListView: any = []
  getDeoView(id) {
    this.commonService.callApi('deomastercode/' + id, '', 'get', false, false, 'REG').then(success => {
      this.DeoListView = success
      console.log(this.DeoListView);
    })
  }
  /****************************************************************************/

}
