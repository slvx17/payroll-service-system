import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleChangeApprovalComponent } from './schedule-change-approval.component';

describe('ScheduleChangeApprovalComponent', () => {
  let component: ScheduleChangeApprovalComponent;
  let fixture: ComponentFixture<ScheduleChangeApprovalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ScheduleChangeApprovalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ScheduleChangeApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
