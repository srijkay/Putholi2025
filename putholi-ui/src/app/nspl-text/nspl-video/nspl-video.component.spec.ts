import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NsplVideoComponent } from './nspl-video.component';

describe('CsplVideoComponent', () => {
  let component: NsplVideoComponent;
  let fixture: ComponentFixture<NsplVideoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NsplVideoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NsplVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
