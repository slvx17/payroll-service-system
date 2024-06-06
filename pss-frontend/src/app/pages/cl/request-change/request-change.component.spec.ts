import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestChangeComponent } from './request-change.component';

describe('RequestChangeComponent', () => {
  let component: RequestChangeComponent;
  let fixture: ComponentFixture<RequestChangeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RequestChangeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RequestChangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
