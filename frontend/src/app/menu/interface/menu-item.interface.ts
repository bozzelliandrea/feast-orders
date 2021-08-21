import { Category } from './category.interface';
import { AbstractDTO } from './../../shared/interface/abstract-dto.interface';

export interface MenuItem extends AbstractDTO {
    name: string;
    description: string;
    color?: string;
    price: number
    category?: Category;
    categoryId?: number;
}