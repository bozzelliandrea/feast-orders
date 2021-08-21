import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuItem } from './../../interface/menu-item.interface';
import { Component } from '@angular/core';
import { AbstractModal } from 'src/app/shared/class/abstract-modal.class';
import { Category } from '../../interface/category.interface';

@Component({
  selector: 'menu-item-modal',
  templateUrl: './menu-item-modal.component.html',
  styleUrls: ['./menu-item-modal.component.scss']
})
export class MenuItemModalComponent extends AbstractModal {

  public category!: Category;
  public menuItem: MenuItem | undefined;
  public menuItemForm: FormGroup;
  public title: string | undefined;

  constructor(private _formBuilder: FormBuilder) {
    super();
    this.menuItemForm = this._builMenuItemForm();
  }

  inject(inputs: any): void {

    this.title = inputs?.title;
    this.category = inputs.category;
    this.menuItemForm.controls['categoryId'].setValue(inputs.category);
    
    if (inputs.menuItem) {
      this.menuItem = inputs.menuItem as MenuItem;
      this._setMenuItemFormValues(inputs.menuItem as MenuItem);
    }
  }

  save(): void {
    this.close(this.menuItemForm.value);
  }

  cancel(): void {
    this.dismiss();
  }

  private _builMenuItemForm(): FormGroup {
    return this._formBuilder.group({
      id: [null],
      version: [null],
      creationTimestamp: [null],
      updateTimestamp: [null],
      creationUser: [null],
      updateUser: [null],
      color: [null],
      name: [null, Validators.required],
      description: [null, Validators.required],
      price: [null, Validators.required],
      categoryId: [null, Validators.required]
    })
  }

  private _setMenuItemFormValues(dto: MenuItem): void {
    this.menuItemForm?.setValue({
      id: dto?.id,
      version: dto?.version,
      creationTimestamp: dto?.creationTimestamp,
      updateTimestamp: dto?.updateTimestamp,
      creationUser: dto?.creationUser,
      updateUser: dto?.updateUser,
      color: dto?.color,
      name: dto?.name,
      description: dto?.description,
      price: dto?.price,
      categoryId: this.category.id
    })
  }
}
