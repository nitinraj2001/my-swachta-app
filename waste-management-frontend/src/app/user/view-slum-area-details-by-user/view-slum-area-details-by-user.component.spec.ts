import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSlumAreaDetailsByUserComponent } from './view-slum-area-details-by-user.component';

describe('ViewSlumAreaDetailsByUserComponent', () => {
  let component: ViewSlumAreaDetailsByUserComponent;
  let fixture: ComponentFixture<ViewSlumAreaDetailsByUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewSlumAreaDetailsByUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewSlumAreaDetailsByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
