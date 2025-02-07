import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NsplPercentageChartComponent } from './nspl-percentage-chart.component';

describe('NsplPercentageChartComponent', () => {
  let component: NsplPercentageChartComponent;
  let fixture: ComponentFixture<NsplPercentageChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NsplPercentageChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NsplPercentageChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
