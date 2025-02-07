import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewQuotationEditComponent } from './review-quotation-edit.component';

describe('ReviewQuotationEditComponent', () => {
  let component: ReviewQuotationEditComponent;
  let fixture: ComponentFixture<ReviewQuotationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewQuotationEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewQuotationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
