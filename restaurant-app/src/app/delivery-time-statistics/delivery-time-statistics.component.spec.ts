import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryTimeStatisticsComponent } from './delivery-time-statistics.component';

describe('DeliveryTimeStatisticsComponent', () => {
  let component: DeliveryTimeStatisticsComponent;
  let fixture: ComponentFixture<DeliveryTimeStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryTimeStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryTimeStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
