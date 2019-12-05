import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryDistributionStatisticsComponent } from './delivery-distribution-statistics.component';

describe('DeliveryDistributionStatisticsComponent', () => {
  let component: DeliveryDistributionStatisticsComponent;
  let fixture: ComponentFixture<DeliveryDistributionStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryDistributionStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryDistributionStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
