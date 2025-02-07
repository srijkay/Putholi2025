import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedSchoolComponent } from './assigned-school.component';

describe('AssignedSchoolComponent', () => {
  let component: AssignedSchoolComponent;
  let fixture: ComponentFixture<AssignedSchoolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignedSchoolComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignedSchoolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
