import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectionAmountReportComponent } from './collection-amount-report.component';

describe('CollectionAmountReportComponent', () => {
  let component: CollectionAmountReportComponent;
  let fixture: ComponentFixture<CollectionAmountReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CollectionAmountReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CollectionAmountReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
