import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationConfigurationViewComponent } from './application-configuration-view.component';

describe('ApplicationConfigurationViewComponent', () => {
  let component: ApplicationConfigurationViewComponent;
  let fixture: ComponentFixture<ApplicationConfigurationViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApplicationConfigurationViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationConfigurationViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
