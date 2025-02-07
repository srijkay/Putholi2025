import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndividualDonationComponent } from './individual-donation.component';

describe('IndividualDonationComponent', () => {
  let component: IndividualDonationComponent;
  let fixture: ComponentFixture<IndividualDonationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndividualDonationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndividualDonationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
