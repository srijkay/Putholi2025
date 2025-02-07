import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonationAmountReportComponent } from './donation-amount-report.component';

describe('DonationAmountReportComponent', () => {
  let component: DonationAmountReportComponent;
  let fixture: ComponentFixture<DonationAmountReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonationAmountReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DonationAmountReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
