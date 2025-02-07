import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NsplNumberComponent } from './nspl-number.component';

describe('NsplNumberComponent', () => {
  let component: NsplNumberComponent;
  let fixture: ComponentFixture<NsplNumberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NsplNumberComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NsplNumberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
