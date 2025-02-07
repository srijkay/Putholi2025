import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageMenuDetailComponent } from './manage-menu-detail.component';

describe('ManageMenuDetailComponent', () => {
  let component: ManageMenuDetailComponent;
  let fixture: ComponentFixture<ManageMenuDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageMenuDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageMenuDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
