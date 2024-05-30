import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PsDashboardComponent } from './ps-dashboard.component';

describe('PsDashboardComponent', () => {
  let component: PsDashboardComponent;
  let fixture: ComponentFixture<PsDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PsDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PsDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
