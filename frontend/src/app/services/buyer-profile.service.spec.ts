import { TestBed } from '@angular/core/testing';

import { BuyerProfileService } from './buyer-profile.service';

describe('BuyerProfileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuyerProfileService = TestBed.get(BuyerProfileService);
    expect(service).toBeTruthy();
  });
});
