import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrustMemberIncomeComponent } from './trust-member-income.component';

describe('TrustMemberIncomeComponent', () => {
  let component: TrustMemberIncomeComponent;
  let fixture: ComponentFixture<TrustMemberIncomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrustMemberIncomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrustMemberIncomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
