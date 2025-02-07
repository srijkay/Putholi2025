import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalWorkflowListComponent } from './approval-workflow-list.component';

describe('ApprovalWorkflowListComponent', () => {
  let component: ApprovalWorkflowListComponent;
  let fixture: ComponentFixture<ApprovalWorkflowListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalWorkflowListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalWorkflowListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
