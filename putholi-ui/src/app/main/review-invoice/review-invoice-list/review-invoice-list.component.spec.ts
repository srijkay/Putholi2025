import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewInvoiceListComponent } from './review-invoice-list.component';

describe('ReviewInvoiceListComponent', () => {
  let component: ReviewInvoiceListComponent;
  let fixture: ComponentFixture<ReviewInvoiceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewInvoiceListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewInvoiceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
