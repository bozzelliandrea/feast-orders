import {Component, OnInit} from '@angular/core';
import {PrinterCfgService} from "../../service/printercfg.service";
import {PrintersService} from "../../service/printers.service";
import {first} from "rxjs/operators";
import {PrinterCfg} from "../../interface/printercfg.interface";
import {PrinterReportTemplateService} from "../../service/printer-report-template.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CategoryModal} from "../../../menu/component/category-modal/category-modal.component";
import {Category} from "../../../menu/interface/category.interface";
import {ModalService} from "../../../shared/service/modal.service";
import {PrinterCfgModalComponent} from "../printer-cfg-modal/printer-cfg-modal.component";

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
              private _formBuilder: FormBuilder,
              private _modalService: ModalService
  ) {
    this.printerCfgForm = this._buildPrinterCfgForm();
  }

  ngOnInit(): void {
    this._loadPrinterCfgList();
    this._loadPrinterList();
    this._loadPrinterReportTemplateList();
  }

  addNewPrinterCfg() {
    const formValue = this.printerCfgForm.value;
    const printerCfg: PrinterCfg = PrinterComponent._getPrinterCfgFromForm(formValue);
    this._printerCfgService.create(printerCfg).pipe(first()).subscribe((res: any) => {
      this._loadPrinterCfgList();
    });

    this.printerCfgForm.reset();
  }

  editPrinterCfg(printerCfg: PrinterCfg) {
    const modalRef = this._modalService.create(PrinterCfgModalComponent, {
      title: 'Modifica Configurazione Stampa ' + printerCfg?.name?.toUpperCase(),
      printerCfg,
      printers: this.printers,
      reportTemplates: this.reportTemplates
    });

    modalRef.onResult().subscribe(
      printerCfg => {
        if (printerCfg) {
          this._printerCfgService.update(printerCfg as PrinterCfg)
            .pipe(first())
            .subscribe((res: any) => {
              this._loadPrinterCfgList();
            });
        }
      });
  }

  deletePrinterCfg(printerCfg: PrinterCfg) {
    if (printerCfg && printerCfg.id) {
      this._printerCfgService.delete(printerCfg.id)
        .pipe(first())
        .subscribe((res: any) => {
          this._loadPrinterCfgList();
        });
    }
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

  private _buildPrinterCfgForm(): FormGroup {
    return this._formBuilder.group({
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

  private static _getPrinterCfgFromForm(formValue: any): PrinterCfg {
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
