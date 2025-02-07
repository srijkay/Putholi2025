import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoivePaymentViewComponent } from './invoive-payment-view.component';

describe('InvoivePaymentViewComponent', () => {
  let component: InvoivePaymentViewComponent;
  let fixture: ComponentFixture<InvoivePaymentViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvoivePaymentViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoivePaymentViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
