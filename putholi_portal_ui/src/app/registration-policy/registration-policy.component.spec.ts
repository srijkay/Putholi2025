import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationPolicyComponent } from './registration-policy.component';

describe('RegistrationPolicyComponent', () => {
  let component: RegistrationPolicyComponent;
  let fixture: ComponentFixture<RegistrationPolicyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationPolicyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationPolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
