import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeoCodeListComponent } from './deo-code-list.component';

describe('DeoCodeListComponent', () => {
  let component: DeoCodeListComponent;
  let fixture: ComponentFixture<DeoCodeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeoCodeListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeoCodeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
