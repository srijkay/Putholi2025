import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeMaintenanceListComponent } from './code-maintenance-list.component';

describe('CodeMaintenanceListComponent', () => {
  let component: CodeMaintenanceListComponent;
  let fixture: ComponentFixture<CodeMaintenanceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CodeMaintenanceListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CodeMaintenanceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
