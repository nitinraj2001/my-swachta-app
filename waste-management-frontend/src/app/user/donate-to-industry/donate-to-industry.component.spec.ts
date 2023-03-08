import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonateToIndustryComponent } from './donate-to-industry.component';

describe('DonateToIndustryComponent', () => {
  let component: DonateToIndustryComponent;
  let fixture: ComponentFixture<DonateToIndustryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonateToIndustryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonateToIndustryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
