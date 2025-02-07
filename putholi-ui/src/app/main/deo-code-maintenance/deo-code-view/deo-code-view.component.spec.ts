import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeoCodeViewComponent } from './deo-code-view.component';

describe('DeoCodeViewComponent', () => {
  let component: DeoCodeViewComponent;
  let fixture: ComponentFixture<DeoCodeViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeoCodeViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeoCodeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
