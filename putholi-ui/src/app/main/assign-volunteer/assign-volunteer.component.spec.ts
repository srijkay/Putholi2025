import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignVolunteerComponent } from './assign-volunteer.component';

describe('AssignVolunteerComponent', () => {
  let component: AssignVolunteerComponent;
  let fixture: ComponentFixture<AssignVolunteerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignVolunteerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignVolunteerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
