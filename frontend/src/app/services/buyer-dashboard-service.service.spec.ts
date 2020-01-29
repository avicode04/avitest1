import { TestBed } from '@angular/core/testing';

import { BuyerDashboardServiceService } from './buyer-dashboard-service.service';

describe('BuyerDashboardServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuyerDashboardServiceService = TestBed.get(BuyerDashboardServiceService);
    expect(service).toBeTruthy();
  });
});
