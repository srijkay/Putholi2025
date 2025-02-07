import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NsplMultilineTextComponent } from './nspl-multiline-text.component';

describe('NsplMultilineTextComponent', () => {
  let component: NsplMultilineTextComponent;
  let fixture: ComponentFixture<NsplMultilineTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NsplMultilineTextComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NsplMultilineTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
