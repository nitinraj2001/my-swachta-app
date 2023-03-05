import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewIndustryDetailsByUserComponent } from './view-industry-details-by-user.component';

describe('ViewIndustryDetailsByUserComponent', () => {
  let component: ViewIndustryDetailsByUserComponent;
  let fixture: ComponentFixture<ViewIndustryDetailsByUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewIndustryDetailsByUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewIndustryDetailsByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
