import {AfterViewInit, Component, ElementRef} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {PrinterCfg} from 'src/app/printer/interface/printercfg.interface';
import {AbstractModal} from 'src/app/shared/class/abstract-modal.class';
import {Category} from '../../interface/category.interface';

@Component({
  selector: 'category-modal',
  templateUrl: './category-modal.component.html',
  styleUrls: ['./category-modal.component.scss']
})
export class CategoryModal extends AbstractModal implements AfterViewInit {

  public title: string | undefined;
  public category!: Category;
  public categoryForm: FormGroup;
  public printerCfgList: Array<PrinterCfg> = [];

  constructor(private _formBuilder: FormBuilder, private _elRef: ElementRef) {
    super();
    this.categoryForm = this._buildCategoryForm();
  }

  ngAfterViewInit(): void {
    this._setCategoryPrinterCfgSwitches(this.category);
  }

  inject(inputs: any): void {
    this.title = inputs.title;
    this.category = inputs.category;
    this.printerCfgList = inputs.printerCfgList;
    this._setCategoryFormValues(this.category);
  }

  save(): void {
    this.close(this.categoryForm.value);
  }

  public onPrinterCfgChange(event: any): void {
    const checked = event.target.checked;
    const id = +event.target.value;
    const formControl: FormControl = this.categoryForm.controls["printerCfgList"] as FormControl;
    const value: Array<PrinterCfg> = formControl.value || [];
    if (checked) {
      value.push({id} as PrinterCfg);
    } else {
      const index = value.findIndex(v => v.id === id);
      value.splice(index, 1);
    }
    formControl.setValue(value);
  }

  private _buildCategoryForm(): FormGroup {
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
      menuItemList: [],
      printerCfgList: []
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
      menuItemList: dto?.menuItemList,
      printerCfgList: dto?.printerCfgList
    })
  }

  private _setCategoryPrinterCfgSwitches(category: Category): void {
    const printerCfgSwitches: any[] = this._elRef.nativeElement.querySelectorAll('.printercfg-input');
    if (category.printerCfgList && category.printerCfgList.length) {
      category.printerCfgList.forEach(item => {
        printerCfgSwitches.forEach(node => {
          if(+node.value === item.id) {
            node.checked = true;
          }
        });
      });
    }
  }
}
