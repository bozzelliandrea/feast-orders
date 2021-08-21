import { MenuItem } from './../../interface/menu-item.interface';
import { MenuItemModalComponent } from './../menu-item-modal/menu-item-modal.component';
import { MenuItemService } from './../../service/menu-item.service';
import { CategoryModal } from '../category-modal/category-modal.component';
import { ModalService } from './../../../shared/service/modal.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';
import { Category } from '../../interface/category.interface';
import { CategoryService } from './../../service/category.service';

@Component({
  selector: 'menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  public categoryList: Array<Category> = new Array();

  public categoryId: number | undefined;
  public categoryForm: FormGroup;
  public menuItemForm: FormGroup | undefined;

  constructor(private _categoryService: CategoryService,
    private _menuItemService: MenuItemService,
    private _formBuilder: FormBuilder,
    private _modalService: ModalService) {

    this.categoryForm = this._buildCategoryForm();
  }

  ngOnInit(): void {

    this._loadCategory();
  }

  public addNewCategory(): void {

    const category: Category = this.categoryForm?.value;

    this._categoryService.create(category)
      .pipe(first())
      .subscribe((res: any) => {
        this._loadCategory();
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
}
