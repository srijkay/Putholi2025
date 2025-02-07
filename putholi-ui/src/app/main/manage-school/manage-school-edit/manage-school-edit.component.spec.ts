import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageSchoolEditComponent } from './manage-school-edit.component';

describe('ManageSchoolEditComponent', () => {
  let component: ManageSchoolEditComponent;
  let fixture: ComponentFixture<ManageSchoolEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageSchoolEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageSchoolEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
