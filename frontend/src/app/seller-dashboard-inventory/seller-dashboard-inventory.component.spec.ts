import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerDashboardInventoryComponent } from './seller-dashboard-inventory.component';

describe('SellerDashboardInventoryComponent', () => {
  let component: SellerDashboardInventoryComponent;
  let fixture: ComponentFixture<SellerDashboardInventoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellerDashboardInventoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerDashboardInventoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
