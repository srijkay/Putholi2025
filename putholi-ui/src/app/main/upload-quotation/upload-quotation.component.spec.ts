import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadQuotationComponent } from './upload-quotation.component';

describe('UploadQuotationComponent', () => {
  let component: UploadQuotationComponent;
  let fixture: ComponentFixture<UploadQuotationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadQuotationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadQuotationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
