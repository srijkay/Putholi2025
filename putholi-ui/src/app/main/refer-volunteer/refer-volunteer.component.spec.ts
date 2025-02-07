import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReferVolunteerComponent } from './refer-volunteer.component';

describe('ReferVolunteerComponent', () => {
  let component: ReferVolunteerComponent;
  let fixture: ComponentFixture<ReferVolunteerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReferVolunteerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReferVolunteerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
