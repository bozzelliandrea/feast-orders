import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PrinterCfg} from "../../interface/printercfg.interface";
import {AbstractModal} from "../../../shared/class/abstract-modal.class";

@Component({
  selector: 'printer-cfg-modal',
  templateUrl: './printer-cfg-modal.component.html',
  styleUrls: ['./printer-cfg-modal.component.scss']
})
export class PrinterCfgModalComponent extends AbstractModal {

  title: string | undefined;
  printerCfg!: PrinterCfg;
  printerCfgForm: FormGroup;
  printers: Array<string> = new Array<string>();
  reportTemplates: Array<string> = new Array<string>();

  constructor(private _formBuilder: FormBuilder) {
    super();
    this.printerCfgForm = this._buildPrinterCfgForm();
  }

  inject(inputs: any): void {
    this.title = inputs.title;
    this.printerCfg = inputs.printerCfg;
    this.printers = inputs.printers;
    this.reportTemplates = inputs.reportTemplates;
    this._setPrinterCfgFormValues(this.printerCfg);
  }

  save() {
    const printerCfg = PrinterCfgModalComponent._getPrinterCfgFromForm(this.printerCfgForm.value)
    this.close(printerCfg);
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

  private _setPrinterCfgFormValues(dto: PrinterCfg) {
    this.printerCfgForm?.setValue({
      id: dto?.id,
      version: dto?.version,
      creationTimestamp: dto?.creationTimestamp,
      updateTimestamp: dto?.updateTimestamp,
      creationUser: dto?.creationUser,
      updateUser: dto?.updateUser,
      name: dto?.name,
      description: dto?.description,
      printerName: dto?.printerName,
      reportTemplate: dto?.reportTemplate,
      attrMediaSize: dto?.attrs?.mediaSize || "A4",
      attrOrientation: dto?.attrs?.orientation || "portrait",
      attrSide: dto?.attrs?.sides || "one-side",
      attrColor: dto?.attrs?.color || "false",
      attrCopies: dto?.attrs?.copies || "1"
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
