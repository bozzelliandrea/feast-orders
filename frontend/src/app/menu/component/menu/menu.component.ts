import { MenuItem } from './../../interface/menu-item.interface';
import { MenuItemModalComponent } from './../menu-item-modal/menu-item-modal.component';
import { MenuItemService } from './../../service/menu-item.service';
import { CategoryModal } from '../category-modal/category-modal.component';
import { ModalService } from './../../../shared/service/modal.service';
import { Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Category } from '../../interface/category.interface';
import { CategoryService } from './../../service/category.service';
import { PrinterCfgService } from 'src/app/printer/service/printercfg.service';
import { PrinterCfg } from 'src/app/printer/interface/printercfg.interface';

@Component({
  selector: 'menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  public categoryList: Array<Category> = new Array();
  public printerCfgList: Array<PrinterCfg> = new Array();

  public categoryId: number | undefined;
  public categoryForm: FormGroup;
  public menuItemForm: FormGroup | undefined;

  constructor(private _categoryService: CategoryService,
    private _menuItemService: MenuItemService,
    private _printerCfgService: PrinterCfgService,
    private _formBuilder: FormBuilder,
    private _modalService: ModalService,
    private _elRef:ElementRef) {

    this.categoryForm = this._buildCategoryForm();
  }

  ngOnInit(): void {
    this._loadCategory();
    this._loadPrinterCfg();
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

  public addNewCategory(): void {

    const category: Category = this.categoryForm?.value;

    this._categoryService.create(category)
      .pipe(first())
      .subscribe((res: any) => {
        this._loadCategory();
      });

    const printerCfgSwitches: any[] = this._elRef.nativeElement.querySelectorAll('.printercfg-input');
    printerCfgSwitches.forEach(element => {
      element.checked = false;
    });
    this.categoryForm.reset();
  }

  public editCategory(category: Category): void {
    const modalRef = this._modalService.create(CategoryModal, {
      title: 'Modifica Categoria ' + category.name?.toUpperCase(),
      category
    });

    modalRef.onResult().subscribe(
      category => {
        if (category) {
          this._categoryService.update(category as Category)
            .pipe(first())
            .subscribe((res: any) => {
              this._loadCategory();
            });
        }
      }
    );
  }

  public deleteCategory(category: Category): void {

    if (category && category.id)
      this._categoryService.delete(category.id)
        .pipe(first())
        .subscribe((res: any) => {
          this._loadCategory();
        });
  }


  public addMenuItem(category: Category): void {
    const modalRef = this._modalService.create(MenuItemModalComponent, {
      title: 'Crea Nuovo Prodotto',
      category
    });

    modalRef.onResult().subscribe(
      menuItem => {
        if (menuItem && category && category.id) {
          this._menuItemService.create(menuItem as MenuItem, category.id)
            .pipe(first())
            .subscribe((menuItemList: MenuItem[]) => {
              category.menuItemList = menuItemList;
            });
        }
      }
    );
  }

  public editMenuItem(category: Category, menuItem: MenuItem): void {
    const modalRef = this._modalService.create(MenuItemModalComponent, {
      title: 'Modifica Prodotto ' + menuItem.name,
      category,
      menuItem
    });

    modalRef.onResult().subscribe(
      menuItem => {
        if (menuItem && category && category.id) {
          this._menuItemService.update(menuItem as MenuItem, category.id)
            .pipe(first())
            .subscribe((menuItemList: MenuItem[]) => {
              category.menuItemList = menuItemList;
            });
        }
      }
    );
  }

  public deleteMenyItem(category: Category, menuItem: MenuItem): void {

    if (menuItem && menuItem.id && category && category.id) {
      this._menuItemService.delete(menuItem.id, category.id)
        .pipe(first())
        .subscribe((menuItemList: MenuItem[]) => {
          category.menuItemList = menuItemList;
        });
    }
  }

  private _loadCategory(): void {
    this._categoryService.getAll()
      .pipe(first())
      .subscribe((categoryList: Category[]) => {
        this.categoryList = categoryList;

        this.categoryList.forEach((category: Category) => {

          if (category && category.id) {
            this._menuItemService.getAll(category.id)
              .pipe(first())
              .subscribe((menuItemList: MenuItem[]) => {
                category.menuItemList = menuItemList;
              });
          }
        });
      });
  }

  private _loadPrinterCfg(): void {
    this._printerCfgService.getAll()
      .pipe(first())
      .subscribe((printerCfgList: PrinterCfg[]) => {
        this.printerCfgList = printerCfgList;
      });
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
      menuItemList: [],
      printerCfgList: []
    })
  }
}
