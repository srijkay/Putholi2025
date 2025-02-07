import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageRoleViewComponent } from './manage-role-view.component';

describe('ManageRoleViewComponent', () => {
  let component: ManageRoleViewComponent;
  let fixture: ComponentFixture<ManageRoleViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageRoleViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageRoleViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
