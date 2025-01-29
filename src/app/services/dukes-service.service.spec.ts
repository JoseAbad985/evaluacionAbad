import { TestBed } from '@angular/core/testing';

import { DukesServiceService } from './dukes-service.service';

describe('DukesServiceService', () => {
  let service: DukesServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DukesServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
