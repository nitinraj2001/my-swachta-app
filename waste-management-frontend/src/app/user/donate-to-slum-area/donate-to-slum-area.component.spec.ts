import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonateToSlumAreaComponent } from './donate-to-slum-area.component';

describe('DonateToSlumAreaComponent', () => {
  let component: DonateToSlumAreaComponent;
  let fixture: ComponentFixture<DonateToSlumAreaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonateToSlumAreaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonateToSlumAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
