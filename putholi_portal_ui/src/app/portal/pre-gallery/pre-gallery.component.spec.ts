import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreGalleryComponent } from './pre-gallery.component';

describe('PreGalleryComponent', () => {
  let component: PreGalleryComponent;
  let fixture: ComponentFixture<PreGalleryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreGalleryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
