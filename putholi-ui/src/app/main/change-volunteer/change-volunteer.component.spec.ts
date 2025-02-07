import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeVolunteerComponent } from './change-volunteer.component';

describe('ChangeVolunteerComponent', () => {
  let component: ChangeVolunteerComponent;
  let fixture: ComponentFixture<ChangeVolunteerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeVolunteerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeVolunteerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
