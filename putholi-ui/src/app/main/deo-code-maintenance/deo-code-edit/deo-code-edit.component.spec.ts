import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeoCodeEditComponent } from './deo-code-edit.component';

describe('DeoCodeEditComponent', () => {
  let component: DeoCodeEditComponent;
  let fixture: ComponentFixture<DeoCodeEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeoCodeEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeoCodeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
