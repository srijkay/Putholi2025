import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewInvoiceDetailsComponent } from './review-invoice-details.component';

describe('ReviewInvoiceDetailsComponent', () => {
  let component: ReviewInvoiceDetailsComponent;
  let fixture: ComponentFixture<ReviewInvoiceDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewInvoiceDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewInvoiceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
