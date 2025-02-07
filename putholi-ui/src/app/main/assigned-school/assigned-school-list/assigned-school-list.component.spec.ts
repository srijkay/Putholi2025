import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedSchoolListComponent } from './assigned-school-list.component';

describe('AssignedSchoolListComponent', () => {
  let component: AssignedSchoolListComponent;
  let fixture: ComponentFixture<AssignedSchoolListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignedSchoolListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignedSchoolListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
