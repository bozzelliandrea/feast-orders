import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { Category } from 'src/app/menu/interface/category.interface';
import { MenuItem } from 'src/app/menu/interface/menu-item.interface';
import { OrderItem } from '../../interface/order-item.interface';
import { Order } from '../../interface/order.interface';
import { CategoryService } from './../../../menu/service/category.service';
import { MenuItemService } from './../../../menu/service/menu-item.service';
import { ModeType } from './../../../shared/enums/mode.enum';
import { OrderService } from './../../service/order.service';

@Component({
  selector: 'order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit, OnChanges {

  @Input() id?: number;

  public mode: ModeType | undefined;
  public orderId: number | undefined;
  public orderForm !: FormGroup;
  public categoryList: Array<Category> = new Array;
  public orderItemList: Array<OrderItem> = new Array;

  constructor(private _route: ActivatedRoute,
    private _router: Router,
    private _orderService: OrderService,
    private _formBuilder: FormBuilder,
    private _categoryService: CategoryService,
    private _menuItemService: MenuItemService) {

    if (_route.snapshot.paramMap.get('id'))
      this.orderId = parseInt(_route.snapshot.paramMap.get('id') as string);
    this.mode = _route.snapshot.data?.mode as ModeType;
  }

  ngOnInit(): void {

    this.orderForm = this._buildOrderForm();

    this._categoryService.getAll()
      .pipe(first())
      .subscribe((categoryList: Array<Category>) => {
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

  ngOnChanges(changes: SimpleChanges): void {

    if (this.id) {
      console.log("passed param" + this.id);
    }
  }

  public goBack(): void {
    this._router.navigate(['../'], { relativeTo: this._route, queryParamsHandling: 'preserve' });
  }

  public save(): void {

    const dto: Order = this.orderForm.value;

    dto.menuItemList = this.orderItemList;

    this._orderService.create(dto).pipe(first()).subscribe((order: Order) => {
      this.goBack();
    });
  }

  public saveAndPrint(): void {
    console.info("To Be Implemented");
  }

  public addItemToOrder(category: Category, menuItem: MenuItem): void {

    if (this.orderItemList.length) {
      for (let orderItem of this.orderItemList) {
        if (orderItem.menuItemId === menuItem.id) {
          orderItem.quantity = orderItem.quantity + 1;
          orderItem.totalPrice = orderItem.quantity * orderItem.menuItem.price;
        } else {

          const orderItem: OrderItem = {
            menuItem: menuItem,
            quantity: 1,
            totalPrice: menuItem.price,
            menuItemId: menuItem.id
          };

          this.orderItemList.push(orderItem);
        }
      }
    } else {

      const orderItem: OrderItem = {
        menuItem: menuItem,
        quantity: 1,
        totalPrice: menuItem.price,
        menuItemId: menuItem.id
      };

      this.orderItemList.push(orderItem);
    }
  }

  private _buildOrderForm(): FormGroup {
    return this._formBuilder.group({
      id: [null],
      version: [null],
      creationTimestamp: [null],
      updateTimestamp: [null],
      creationUser: [null],
      updateUser: [null],
      client: [null, Validators.required],
      tableNumber: [null, Validators.required],
      placeSettingNumber: [null, Validators.required],
      progressNumber: [null, Validators.required],
      discount: [null],
      total: [null]
    })
  }

  private _setOrderForm(dto: Order): void {
    this.orderForm?.setValue({
      id: dto?.id,
      version: dto?.version,
      creationTimestamp: dto?.creationTimestamp,
      updateTimestamp: dto?.updateTimestamp,
      creationUser: dto?.creationUser,
      updateUser: dto?.updateUser,
      client: dto?.client,
      tableNumber: dto?.tableNumber,
      placeSettingNumber: dto?.placeSettingNumber,
      progressNumber: dto?.progressNumber,
      discount: dto?.discount,
      total: dto?.total
    })
  }
}
