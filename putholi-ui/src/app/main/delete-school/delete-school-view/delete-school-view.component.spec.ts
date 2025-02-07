import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteSchoolViewComponent } from './delete-school-view.component';

describe('DeleteSchoolViewComponent', () => {
  let component: DeleteSchoolViewComponent;
  let fixture: ComponentFixture<DeleteSchoolViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteSchoolViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteSchoolViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
