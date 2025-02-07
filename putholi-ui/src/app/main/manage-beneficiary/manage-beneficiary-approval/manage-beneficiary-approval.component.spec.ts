import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageBeneficiaryApprovalComponent } from './manage-beneficiary-approval.component';

describe('ManageBeneficiaryApprovalComponent', () => {
  let component: ManageBeneficiaryApprovalComponent;
  let fixture: ComponentFixture<ManageBeneficiaryApprovalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageBeneficiaryApprovalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageBeneficiaryApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
