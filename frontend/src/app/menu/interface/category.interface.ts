import { MenuItem } from './menu-item.interface';
import { AbstractDTO } from './../../shared/interface/abstract-dto.interface';

export interface Category extends AbstractDTO {
    name: string;
    description: string;
    color: string;
    menuItemList: Array<MenuItem>;
}