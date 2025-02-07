import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailSendToDEOComponent } from './email-send-to-deo.component';

describe('EmailSendToDEOComponent', () => {
  let component: EmailSendToDEOComponent;
  let fixture: ComponentFixture<EmailSendToDEOComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmailSendToDEOComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailSendToDEOComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
