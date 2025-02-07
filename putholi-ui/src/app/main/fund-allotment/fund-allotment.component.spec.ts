import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FundAllotmentComponent } from './fund-allotment.component';

describe('FundAllotmentComponent', () => {
  let component: FundAllotmentComponent;
  let fixture: ComponentFixture<FundAllotmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FundAllotmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FundAllotmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
