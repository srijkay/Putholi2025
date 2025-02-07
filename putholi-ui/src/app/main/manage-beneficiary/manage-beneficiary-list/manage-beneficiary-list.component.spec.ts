import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageBeneficiaryListComponent } from './manage-beneficiary-list.component';

describe('ManageBeneficiaryListComponent', () => {
  let component: ManageBeneficiaryListComponent;
  let fixture: ComponentFixture<ManageBeneficiaryListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageBeneficiaryListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageBeneficiaryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
