import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NsplTableExportComponent } from './nspl-table-export.component';

describe('NsplTableExportComponent', () => {
  let component: NsplTableExportComponent;
  let fixture: ComponentFixture<NsplTableExportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NsplTableExportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NsplTableExportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
