import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewNGODetailsByUserComponent } from './view-ngodetails-by-user.component';

describe('ViewNGODetailsByUserComponent', () => {
  let component: ViewNGODetailsByUserComponent;
  let fixture: ComponentFixture<ViewNGODetailsByUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewNGODetailsByUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewNGODetailsByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
