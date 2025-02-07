import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-corporate-donation',
  templateUrl: './corporate-donation.component.html',
  styleUrls: ['./corporate-donation.component.css']
})
export class CorporateDonationComponent extends BaseComponent implements OnInit {

  @ViewChild('corporate') corporate: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();

  @Input()
  public corporateDetails: any = []

  @Input()
  public donorDetails: any = []


  constructor(inj: Injector) {
    super(inj);
  }

  ngOnInit(): void {
    this.donorDetails.forEach(d => {
      this.corporateDetails.forEach(e => {
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
    this.corporate.show()
  }

  clickCancel() {
    this.modalStatus.emit(false)
  }

}
