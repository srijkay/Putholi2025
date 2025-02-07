import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationConfigurationEditComponent } from './application-configuration-edit.component';

describe('ApplicationConfigurationEditComponent', () => {
  let component: ApplicationConfigurationEditComponent;
  let fixture: ComponentFixture<ApplicationConfigurationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApplicationConfigurationEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationConfigurationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
