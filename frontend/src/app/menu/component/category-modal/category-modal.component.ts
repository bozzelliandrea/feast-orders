import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AbstractModal } from 'src/app/shared/class/abstract-modal.class';
import { Category } from '../../interface/category.interface';

@Component({
  selector: 'category-modal',
  templateUrl: './category-modal.component.html',
  styleUrls: ['./category-modal.component.scss']
})
export class CategoryModal extends AbstractModal {

  public title: string | undefined;
  public category!: Category;
  public categoryForm: FormGroup;

  constructor(private _formBuilder: FormBuilder) {
    super();
    this.categoryForm = this._buildCategoryForm();
  }

  inject(inputs: any): void {
    this.title = inputs.title;
    this.category = inputs.category;
    this._setCategoryFormValues(this.category);
  }

  save(): void {
    this.close(this.categoryForm.value);
  }

  cancel(): void {
    this.dismiss();
  }

  private _buildCategoryForm(): FormGroup {
    return this._formBuilder.group({
      id: [null],
      version: [null],
      creationTimestamp: [null],
      updateTimestamp: [null],
      creationUser: [null],
      updateUser: [null],
      color: [null, Validators.required],
      name: [null, Validators.required],
      description: [null, Validators.required],
      menuItemList: []
    })
  }

  private _setCategoryFormValues(dto: Category): void {
    this.categoryForm?.setValue({
      id: dto?.id,
      version: dto?.version,
      creationTimestamp: dto?.creationTimestamp,
      updateTimestamp: dto?.updateTimestamp,
      creationUser: dto?.creationUser,
      updateUser: dto?.updateUser,
      color: dto?.color,
      name: dto?.name,
      description: dto?.description,
      menuItemList: dto?.menuItemList
    })
  }
}