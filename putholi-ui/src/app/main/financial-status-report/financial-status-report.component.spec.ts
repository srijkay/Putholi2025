import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinancialStatusReportComponent } from './financial-status-report.component';

describe('FinancialStatusReportComponent', () => {
  let component: FinancialStatusReportComponent;
  let fixture: ComponentFixture<FinancialStatusReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinancialStatusReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FinancialStatusReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
