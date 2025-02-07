import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoivePaymentEditComponent } from './invoive-payment-edit.component';

describe('InvoivePaymentEditComponent', () => {
  let component: InvoivePaymentEditComponent;
  let fixture: ComponentFixture<InvoivePaymentEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvoivePaymentEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoivePaymentEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
