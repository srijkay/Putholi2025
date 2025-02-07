import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageSchoolViewComponent } from './manage-school-view.component';

describe('ManageSchoolViewComponent', () => {
  let component: ManageSchoolViewComponent;
  let fixture: ComponentFixture<ManageSchoolViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageSchoolViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageSchoolViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
