import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageRoleEditComponent } from './manage-role-edit.component';

describe('ManageRoleEditComponent', () => {
  let component: ManageRoleEditComponent;
  let fixture: ComponentFixture<ManageRoleEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageRoleEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageRoleEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
