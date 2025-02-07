import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeoCodeMaintenanceComponent } from './deo-code-maintenance.component';

describe('DeoCodeMaintenanceComponent', () => {
  let component: DeoCodeMaintenanceComponent;
  let fixture: ComponentFixture<DeoCodeMaintenanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeoCodeMaintenanceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeoCodeMaintenanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
