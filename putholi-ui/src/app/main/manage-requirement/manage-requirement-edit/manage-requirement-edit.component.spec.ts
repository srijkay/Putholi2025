import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageRequirementEditComponent } from './manage-requirement-edit.component';

describe('ManageRequirementEditComponent', () => {
  let component: ManageRequirementEditComponent;
  let fixture: ComponentFixture<ManageRequirementEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageRequirementEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageRequirementEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
