import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackDonationComponent } from './track-donation.component';

describe('TrackDonationComponent', () => {
  let component: TrackDonationComponent;
  let fixture: ComponentFixture<TrackDonationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrackDonationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackDonationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
