import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NsplFileBrowseComponent } from './nspl-file-browse.component';

describe('NsplFileBrowseComponent', () => {
  let component: NsplFileBrowseComponent;
  let fixture: ComponentFixture<NsplFileBrowseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NsplFileBrowseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NsplFileBrowseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
