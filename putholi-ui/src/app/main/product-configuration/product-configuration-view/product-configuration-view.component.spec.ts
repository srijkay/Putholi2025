import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductConfigurationViewComponent } from './product-configuration-view.component';

describe('ProductConfigurationViewComponent', () => {
  let component: ProductConfigurationViewComponent;
  let fixture: ComponentFixture<ProductConfigurationViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductConfigurationViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductConfigurationViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
