import { TestBed } from '@angular/core/testing';

import { BuyerRegistrationService } from './buyer-registration.service';

describe('BuyerRegistrationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuyerRegistrationService = TestBed.get(BuyerRegistrationService);
    expect(service).toBeTruthy();
  });
});
