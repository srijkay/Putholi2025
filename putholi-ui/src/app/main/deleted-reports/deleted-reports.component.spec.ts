import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedReportsComponent } from './deleted-reports.component';

describe('DeletedReportsComponent', () => {
  let component: DeletedReportsComponent;
  let fixture: ComponentFixture<DeletedReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletedReportsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletedReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
