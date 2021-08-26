import {Component, OnInit} from '@angular/core';
import {PrinterCfgService} from "../../service/printercfg.service";
import {PrintersService} from "../../service/printers.service";
import {first} from "rxjs/operators";
import {PrinterCfg} from "../../interface/printercfg.interface";
import {PrinterReportTemplateService} from "../../service/printer-report-template.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'printer',
  templateUrl: './printer.component.html',
  styleUrls: ['./printer.component.scss']
})
export class PrinterComponent implements OnInit {

  printerCfgList: Array<PrinterCfg> = new Array<PrinterCfg>();
  printers: Array<string> = new Array<string>();
  reportTemplates: Array<string> = new Array<string>();
  printerCfgForm: FormGroup;

  constructor(private _printerCfgService: PrinterCfgService,
              private _printersService: PrintersService,
              private _printerReportTemplateService: PrinterReportTemplateService,
              private _formBuilder: FormBuilder
  ) {
    this._buildPrinterCfgForm();
  }

  ngOnInit(): void {
    this._loadPrinterCfgList();
    this._loadPrinterList();
    this._loadPrinterReportTemplateList();
  }

  addNewPrinterCfg() {
    const formValue = this.printerCfgForm.value;
    const printerCfg: PrinterCfg = this._getPrinterCfgFromForm(formValue);
    this._printerCfgService.create(printerCfg).pipe(first()).subscribe((res: any) => {
      this._loadPrinterCfgList();
    });

    this.printerCfgForm.reset();
  }

  editPrinterCfg(printerCfg: PrinterCfg) {
    // todo
  }

  deletePrinterCfg(printerCfg: PrinterCfg) {
    // todo
  }

  private _loadPrinterCfgList() {
    this._printerCfgService.getAll().pipe(first()).subscribe((printerCfgList: PrinterCfg[]) => {
      this.printerCfgList = printerCfgList;
    });
  }

  private _loadPrinterList() {
    this._printersService.getAll().pipe(first()).subscribe(printers => {
      this.printers = printers;
    });
  }

  private _loadPrinterReportTemplateList() {
    this._printerReportTemplateService.getAll().pipe(first()).subscribe(reportTemplates => {
      this.reportTemplates = reportTemplates;
    });
  }

  private _buildPrinterCfgForm() {
    this.printerCfgForm = this._formBuilder.group({
      id: [null],
      version: [null],
      creationTimestamp: [null],
      updateTimestamp: [null],
      creationUser: [null],
      updateUser: [null],
      name: [null, Validators.required],
      description: [null],
      printerName: [null, Validators.required],
      reportTemplate: [null, Validators.required],
      attrMediaSize: ['A4'],
      attrOrientation: ['portrait'],
      attrColor: [false],
      attrSide: ['one-side'],
      attrCopies: [1]
    });
  }

  private _getPrinterCfgFromForm(formValue: any): PrinterCfg {
    return  {
      id: formValue.id,
      version: formValue.version,
      creationTimestamp: formValue.creationTimestamp,
      updateTimestamp: formValue.updateTimestamp,
      creationUser: formValue.creationUser,
      updateUser: formValue.updateUser,
      name: formValue.name,
      description: formValue.description,
      printerName: formValue.printerName,
      reportTemplate: formValue.reportTemplate,
      attrs: {
        mediaSize: formValue.attrMediaSize,
        orientation: formValue.attrOrientation,
        sides: formValue.attrSide,
        color: formValue.attrColor,
        copies: formValue.attrCopies
      }
    };
  }
}
