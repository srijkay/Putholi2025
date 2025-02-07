import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NsplCheckboxComponent } from './nspl-checkbox.component';

describe('NsplCheckboxComponent', () => {
  let component: NsplCheckboxComponent;
  let fixture: ComponentFixture<NsplCheckboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NsplCheckboxComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NsplCheckboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
