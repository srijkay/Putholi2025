import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageModuleListComponent } from './manage-module-list.component';

describe('ManageModuleListComponent', () => {
  let component: ManageModuleListComponent;
  let fixture: ComponentFixture<ManageModuleListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageModuleListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageModuleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
