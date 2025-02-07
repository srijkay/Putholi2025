import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-trust-member-income',
  templateUrl: './trust-member-income.component.html',
  styleUrls: ['./trust-member-income.component.css']
})
export class TrustMemberIncomeComponent extends BaseComponent implements OnInit {
  @ViewChild('member') member: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();

  @Input()
  public memberDetails: any = []

  constructor(inj: Injector) {
    super(inj);
  }

  ngOnInit(): void {
  }


  ngAfterViewInit() {
    this.member.show()
  }

  clickCancel() {
    this.modalStatus.emit(false)
  }

}
