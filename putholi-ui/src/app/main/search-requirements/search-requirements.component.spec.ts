import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchRequirementsComponent } from './search-requirements.component';

describe('SearchRequirementsComponent', () => {
  let component: SearchRequirementsComponent;
  let fixture: ComponentFixture<SearchRequirementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchRequirementsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchRequirementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
