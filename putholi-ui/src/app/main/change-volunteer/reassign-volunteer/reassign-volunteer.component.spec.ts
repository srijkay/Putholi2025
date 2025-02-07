import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReassignVolunteerComponent } from './reassign-volunteer.component';

describe('ReassignVolunteerComponent', () => {
  let component: ReassignVolunteerComponent;
  let fixture: ComponentFixture<ReassignVolunteerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReassignVolunteerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReassignVolunteerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
