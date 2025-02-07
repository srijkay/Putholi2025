import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovalWorkflowViewComponent } from './approval-workflow-view.component';

describe('ApprovalWorkflowViewComponent', () => {
  let component: ApprovalWorkflowViewComponent;
  let fixture: ComponentFixture<ApprovalWorkflowViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovalWorkflowViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprovalWorkflowViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
