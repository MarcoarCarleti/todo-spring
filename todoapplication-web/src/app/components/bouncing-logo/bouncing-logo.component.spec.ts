import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BouncingLogoComponent } from './bouncing-logo.component';

describe('BouncingLogoComponent', () => {
  let component: BouncingLogoComponent;
  let fixture: ComponentFixture<BouncingLogoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BouncingLogoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BouncingLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
