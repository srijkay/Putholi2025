import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedSchoolViewComponent } from './assigned-school-view.component';

describe('AssignedSchoolViewComponent', () => {
  let component: AssignedSchoolViewComponent;
  let fixture: ComponentFixture<AssignedSchoolViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignedSchoolViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignedSchoolViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
