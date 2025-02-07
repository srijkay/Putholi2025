import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CsplTextComponent } from './nspl-text.component';

describe('CsplTextComponent', () => {
  let component: CsplTextComponent;
  let fixture: ComponentFixture<CsplTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CsplTextComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CsplTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
