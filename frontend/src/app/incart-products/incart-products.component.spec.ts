import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncartProductsComponent } from './incart-products.component';

describe('IncartProductsComponent', () => {
  let component: IncartProductsComponent;
  let fixture: ComponentFixture<IncartProductsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncartProductsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncartProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
