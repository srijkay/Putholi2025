import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeMaintenanceViewComponent } from './code-maintenance-view.component';

describe('CodeMaintenanceViewComponent', () => {
  let component: CodeMaintenanceViewComponent;
  let fixture: ComponentFixture<CodeMaintenanceViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CodeMaintenanceViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CodeMaintenanceViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
