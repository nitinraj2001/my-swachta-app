import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonateToNGOComponent } from './donate-to-ngo.component';

describe('DonateToNGOComponent', () => {
  let component: DonateToNGOComponent;
  let fixture: ComponentFixture<DonateToNGOComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonateToNGOComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonateToNGOComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
