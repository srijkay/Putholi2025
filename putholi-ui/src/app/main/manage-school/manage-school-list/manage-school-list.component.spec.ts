import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageSchoolListComponent } from './manage-school-list.component';

describe('ManageSchoolListComponent', () => {
  let component: ManageSchoolListComponent;
  let fixture: ComponentFixture<ManageSchoolListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageSchoolListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageSchoolListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
