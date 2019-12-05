import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CuisineMaxReviewStatisticsComponent } from './cuisine-max-review-statistics.component';

describe('CuisineMaxReviewStatisticsComponent', () => {
  let component: CuisineMaxReviewStatisticsComponent;
  let fixture: ComponentFixture<CuisineMaxReviewStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CuisineMaxReviewStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CuisineMaxReviewStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
