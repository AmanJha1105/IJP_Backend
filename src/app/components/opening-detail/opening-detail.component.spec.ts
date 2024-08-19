import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpeningDetailComponent } from './opening-detail.component';

describe('OpeningDetailComponent', () => {
  let component: OpeningDetailComponent;
  let fixture: ComponentFixture<OpeningDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OpeningDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OpeningDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
