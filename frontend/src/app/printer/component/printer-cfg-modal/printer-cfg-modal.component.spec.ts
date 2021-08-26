import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrinterCfgModalComponent } from './printer-cfg-modal.component';

describe('PrintercfgModalComponent', () => {
  let component: PrinterCfgModalComponent;
  let fixture: ComponentFixture<PrinterCfgModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrinterCfgModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PrinterCfgModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
