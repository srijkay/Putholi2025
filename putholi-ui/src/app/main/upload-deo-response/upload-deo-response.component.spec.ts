import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadDeoResponseComponent } from './upload-deo-response.component';

describe('UploadDeoResponseComponent', () => {
  let component: UploadDeoResponseComponent;
  let fixture: ComponentFixture<UploadDeoResponseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadDeoResponseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadDeoResponseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
