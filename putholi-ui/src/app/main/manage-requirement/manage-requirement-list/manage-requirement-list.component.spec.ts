import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageRequirementListComponent } from './manage-requirement-list.component';

describe('ManageRequirementListComponent', () => {
  let component: ManageRequirementListComponent;
  let fixture: ComponentFixture<ManageRequirementListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageRequirementListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageRequirementListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
