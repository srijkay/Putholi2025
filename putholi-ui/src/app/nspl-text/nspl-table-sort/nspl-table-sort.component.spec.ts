import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NsplTableSortComponent } from './nspl-table-sort.component';

describe('NsplTableSortComponent', () => {
  let component: NsplTableSortComponent;
  let fixture: ComponentFixture<NsplTableSortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NsplTableSortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NsplTableSortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
