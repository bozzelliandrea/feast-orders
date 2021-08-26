import { MenuItem } from './menu-item.interface';
import { AbstractDTO } from './../../shared/interface/abstract-dto.interface';
import { PrinterCfg } from 'src/app/printer/interface/printercfg.interface';

export interface Category extends AbstractDTO {
    name: string;
    description: string;
    color: string;
    menuItemList: Array<MenuItem>;
    printerCfgList: Array<PrinterCfg>;
}