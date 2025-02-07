import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FundAllotmentListComponent } from './fund-allotment-list.component';

describe('FundAllotmentListComponent', () => {
  let component: FundAllotmentListComponent;
  let fixture: ComponentFixture<FundAllotmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FundAllotmentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FundAllotmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
