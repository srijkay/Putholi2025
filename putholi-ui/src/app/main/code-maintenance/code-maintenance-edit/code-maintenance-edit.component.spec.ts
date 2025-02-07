import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeMaintenanceEditComponent } from './code-maintenance-edit.component';

describe('CodeMaintenanceEditComponent', () => {
  let component: CodeMaintenanceEditComponent;
  let fixture: ComponentFixture<CodeMaintenanceEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CodeMaintenanceEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CodeMaintenanceEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
