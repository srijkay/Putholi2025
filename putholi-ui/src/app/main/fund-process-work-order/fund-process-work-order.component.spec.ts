import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FundProcessWorkOrderComponent } from './fund-process-work-order.component';

describe('FundProcessWorkOrderComponent', () => {
  let component: FundProcessWorkOrderComponent;
  let fixture: ComponentFixture<FundProcessWorkOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FundProcessWorkOrderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FundProcessWorkOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
