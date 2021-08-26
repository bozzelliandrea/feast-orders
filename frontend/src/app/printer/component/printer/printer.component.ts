import { Component, OnInit } from '@angular/core';
import {PrinterCfgService} from "../../service/printercfg.service";
import {PrintersService} from "../../service/printers.service";
import {first} from "rxjs/operators";
import {PrinterCfg} from "../../interface/printercfg.interface";
import {PrinterReportTemplateService} from "../../service/printer-report-template.service";

@Component({
  selector: 'printer',
  templateUrl: './printer.component.html',
  styleUrls: ['./printer.component.scss']
})
export class PrinterComponent implements OnInit {

  printerCfgList: Array<PrinterCfg> = new Array<PrinterCfg>();
  printers: Array<string> = new Array<string>();
  reportTemplates: Array<string> = new Array<string>();

  constructor(private _printerCfgService: PrinterCfgService,
              private _printersService: PrintersService,
              private _printerReportTemplateService: PrinterReportTemplateService) { }

  ngOnInit(): void {
    this._loadPrinterCfgList();
    this._loadPrinterList();
    this._loadPrinterReportTemplateList();
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

}
