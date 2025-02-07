import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageMenuListComponent } from './manage-menu-list.component';

describe('ManageMenuListComponent', () => {
  let component: ManageMenuListComponent;
  let fixture: ComponentFixture<ManageMenuListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageMenuListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageMenuListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
