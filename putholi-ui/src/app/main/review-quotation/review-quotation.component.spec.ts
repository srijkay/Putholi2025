import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewQuotationComponent } from './review-quotation.component';

describe('ReviewQuotationComponent', () => {
  let component: ReviewQuotationComponent;
  let fixture: ComponentFixture<ReviewQuotationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewQuotationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewQuotationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
