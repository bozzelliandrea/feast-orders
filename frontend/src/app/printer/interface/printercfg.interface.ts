import { AbstractDTO } from "src/app/shared/interface/abstract-dto.interface";

export interface PrinterCfg extends AbstractDTO {
    name: string;
    description: string;
    printerName: string;
    reportTemplate: string;
    attrs: { [key:string]: string; };
}