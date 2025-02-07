import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FundAllotmentDetailsComponent } from './fund-allotment-details.component';

describe('FundAllotmentDetailsComponent', () => {
  let component: FundAllotmentDetailsComponent;
  let fixture: ComponentFixture<FundAllotmentDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FundAllotmentDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FundAllotmentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
