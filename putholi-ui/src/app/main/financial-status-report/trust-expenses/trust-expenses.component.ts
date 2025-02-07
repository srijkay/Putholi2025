import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-trust-expenses',
  templateUrl: './trust-expenses.component.html',
  styleUrls: ['./trust-expenses.component.css']
})
export class TrustExpensesComponent extends BaseComponent implements OnInit {
  @ViewChild('expenses') expenses: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();

  @Input()
  public trustExpenses: any = []


  constructor(inj: Injector) {
    super(inj);
  }


  data: any = {
    pageNumber: 1,
    pageSize: 10
  }
  id: any
  ngOnInit(): void {

    console.log(this.trustExpenses);

    this.trustExpenses.forEach(e => {

      const input = e.remarks
      this.id = input.match(/(\d+)/)
      if (this.id != null) {
        console.log(this.id[1]);
        this.data.consolidateId = this.id[1]
        this.getConsolidateInfo(this.data)
      }

    });



  }


  ngAfterViewInit() {
    this.expenses.show()
  }

  clickCancel() {
    this.modalStatus.emit(false)
  }

  /****************************************************************************
     @PURPOSE      : To get consolidate Info
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ***************************************************************************/
  getConsolidateInfo(data) {
    this.commonService.callApi('consolidate/search', data, 'post', false, true, 'LOG').then(success => {
      let successData: any = success;

      setTimeout(() => {
        this.trustExpenses.forEach(e => {
          const input = e.remarks
          const projId = input.match(/(\d+)/)
          successData.content.forEach(c => {
            if (projId != null) {
              if (projId[1] == c.consolidateId) {
                e.schoolName = c.schoolName
              }
            }
          });
        });
      }, 100);
      console.log(this.trustExpenses);


    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })

  }
  /*******************************************************************************/


}
