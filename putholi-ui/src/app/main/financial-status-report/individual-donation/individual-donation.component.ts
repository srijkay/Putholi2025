import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-individual-donation',
  templateUrl: './individual-donation.component.html',
  styleUrls: ['./individual-donation.component.css']
})
export class IndividualDonationComponent extends BaseComponent implements OnInit {

  @ViewChild('individual') individual: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();

  @Input()
  public individualDetails: any = []

  @Input()
  public donorDetails: any = []

  constructor(inj: Injector) {
    super(inj);
  }

  ngOnInit(): void {
    this.donorDetails.forEach(d => {
      this.individualDetails.forEach(e => {
        if (e.donorId == d.emailId) {
          e.name = d.firstName;
        }
        if (e.donorId === 'srijay@yahoo.com') {
          e.name = 'Jagankumar'
        }
      });
    });
  }


  ngAfterViewInit() {
    this.individual.show()
  }

  clickCancel() {
    this.modalStatus.emit(false)
  }

}
