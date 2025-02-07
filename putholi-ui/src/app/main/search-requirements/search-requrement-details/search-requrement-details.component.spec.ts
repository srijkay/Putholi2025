import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchRequrementDetailsComponent } from './search-requrement-details.component';

describe('SearchRequrementDetailsComponent', () => {
  let component: SearchRequrementDetailsComponent;
  let fixture: ComponentFixture<SearchRequrementDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchRequrementDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchRequrementDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
