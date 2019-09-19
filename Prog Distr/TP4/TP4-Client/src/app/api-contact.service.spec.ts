import { TestBed } from '@angular/core/testing';

import { ApiContactService } from './api-contact.service';

describe('ApiContactService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ApiContactService = TestBed.get(ApiContactService);
    expect(service).toBeTruthy();
  });
});
