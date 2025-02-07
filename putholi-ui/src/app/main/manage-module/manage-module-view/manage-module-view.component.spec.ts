import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageModuleViewComponent } from './manage-module-view.component';

describe('ManageModuleViewComponent', () => {
  let component: ManageModuleViewComponent;
  let fixture: ComponentFixture<ManageModuleViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageModuleViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageModuleViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
