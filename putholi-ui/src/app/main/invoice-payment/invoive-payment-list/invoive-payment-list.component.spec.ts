import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoivePaymentListComponent } from './invoive-payment-list.component';

describe('InvoivePaymentListComponent', () => {
  let component: InvoivePaymentListComponent;
  let fixture: ComponentFixture<InvoivePaymentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvoivePaymentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoivePaymentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
