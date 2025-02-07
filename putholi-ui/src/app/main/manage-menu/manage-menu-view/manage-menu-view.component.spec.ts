import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageMenuViewComponent } from './manage-menu-view.component';

describe('ManageMenuViewComponent', () => {
  let component: ManageMenuViewComponent;
  let fixture: ComponentFixture<ManageMenuViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageMenuViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageMenuViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
