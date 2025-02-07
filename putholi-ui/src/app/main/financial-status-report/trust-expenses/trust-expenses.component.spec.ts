import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrustExpensesComponent } from './trust-expenses.component';

describe('TrustExpensesComponent', () => {
  let component: TrustExpensesComponent;
  let fixture: ComponentFixture<TrustExpensesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrustExpensesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrustExpensesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
