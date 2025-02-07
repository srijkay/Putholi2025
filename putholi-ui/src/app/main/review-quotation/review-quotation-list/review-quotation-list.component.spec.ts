import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewQuotationListComponent } from './review-quotation-list.component';

describe('ReviewQuotationListComponent', () => {
  let component: ReviewQuotationListComponent;
  let fixture: ComponentFixture<ReviewQuotationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewQuotationListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewQuotationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
