import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationConfigurationListComponent } from './application-configuration-list.component';

describe('ApplicationConfigurationListComponent', () => {
  let component: ApplicationConfigurationListComponent;
  let fixture: ComponentFixture<ApplicationConfigurationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApplicationConfigurationListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationConfigurationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
