import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductConfigurationEditComponent } from './product-configuration-edit.component';

describe('ProductConfigurationEditComponent', () => {
  let component: ProductConfigurationEditComponent;
  let fixture: ComponentFixture<ProductConfigurationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductConfigurationEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductConfigurationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
