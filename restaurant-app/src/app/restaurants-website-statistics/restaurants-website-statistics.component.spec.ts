import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantsWebsiteStatisticsComponent } from './restaurants-website-statistics.component';

describe('RestaurantsWebsiteStatisticsComponent', () => {
  let component: RestaurantsWebsiteStatisticsComponent;
  let fixture: ComponentFixture<RestaurantsWebsiteStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestaurantsWebsiteStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantsWebsiteStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
