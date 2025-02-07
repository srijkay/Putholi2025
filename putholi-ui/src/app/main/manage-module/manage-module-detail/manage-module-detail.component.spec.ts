import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageModuleDetailComponent } from './manage-module-detail.component';

describe('ManageModuleDetailComponent', () => {
  let component: ManageModuleDetailComponent;
  let fixture: ComponentFixture<ManageModuleDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageModuleDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageModuleDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
