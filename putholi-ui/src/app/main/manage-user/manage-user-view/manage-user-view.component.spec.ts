import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageUserViewComponent } from './manage-user-view.component';

describe('ManageUserViewComponent', () => {
  let component: ManageUserViewComponent;
  let fixture: ComponentFixture<ManageUserViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageUserViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageUserViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
