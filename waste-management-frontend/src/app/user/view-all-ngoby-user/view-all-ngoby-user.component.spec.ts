import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllNGOByUserComponent } from './view-all-ngoby-user.component';

describe('ViewAllNGOByUserComponent', () => {
  let component: ViewAllNGOByUserComponent;
  let fixture: ComponentFixture<ViewAllNGOByUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllNGOByUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllNGOByUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
