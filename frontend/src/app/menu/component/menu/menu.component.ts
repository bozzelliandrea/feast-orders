import { MyModalComponent } from './../my-modal.component';
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

  public category$: Observable<Array<Category>> | undefined;

  public categoryId: number | undefined;
  public categoryForm: FormGroup;
  public menuItemForm: FormGroup | undefined;

  constructor(private _categoryService: CategoryService,
    private _formBuilder: FormBuilder,
    private _modalService: ModalService) {

    this.categoryForm = this._buildCategoryForm();
  }

  ngOnInit(): void {

    this._loadCategory();
  }

  public addNewCategory(): void {

    const category: Category = this.categoryForm?.value;

    this._categoryService.create(category).pipe(first()).subscribe((res: any) => {
      this._loadCategory();
    });

    this.categoryForm.reset();
  }

  public editCategory(category: Category): void {
    const modalRef = this._modalService.create(MyModalComponent, { title: 'My dynamic title', message: 'My dynamic message' });

    modalRef.onResult().subscribe(
      closed => console.log('closed', closed),
      dismissed => console.log('dismissed', dismissed),
      () => console.log('completed')
    );
  }

  public deleteCategory(category: Category): void {
  
    if (category && category.id)
      this._categoryService.delete(category.id).pipe(first()).subscribe((res: any) => {
        this._loadCategory();
      });
  }

  private _loadCategory(): void {
    this.category$ = this._categoryService.getAll();
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
