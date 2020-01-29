import { TestBed } from '@angular/core/testing';

import { SocialLoginService } from './social-login.service';

describe('SocialLoginService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SocialLoginService = TestBed.get(SocialLoginService);
    expect(service).toBeTruthy();
  });
});
