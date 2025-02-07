import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeVolunteerListComponent } from './change-volunteer-list.component';

describe('ChangeVolunteerListComponent', () => {
  let component: ChangeVolunteerListComponent;
  let fixture: ComponentFixture<ChangeVolunteerListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeVolunteerListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeVolunteerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
