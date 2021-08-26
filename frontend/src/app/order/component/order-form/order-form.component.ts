import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
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
export class OrderFormComponent implements OnInit, OnDestroy {

  public mode: ModeType | undefined;
  public orderId: number | undefined;
  public orderForm !: FormGroup;
  public categoryList: Array<Category> = new Array;
  public orderItemList: Array<OrderItem> = new Array;

  private paidSub: Subscription = Subscription.EMPTY;

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

    if (this.mode === ModeType.EDIT || this.mode === ModeType.NEW) {

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

      if (this.mode === ModeType.EDIT && this.orderId) {
        this._orderService.getById(this.orderId)
          .pipe(first())
          .subscribe((order: Order) => {
            this._setOrderForm(order);

            this._orderService.getOrderItemDetails(this.orderId as number)
              .pipe(first())
              .subscribe((orderItemList: OrderItem[]) => {
                this.orderItemList = orderItemList;
              });
          });
      }
    }

    if (this.mode === ModeType.VIEW && this.orderId) {
      this.orderForm.disable();

      this._orderService.getById(this.orderId)
        .pipe(first())
        .subscribe((order: Order) => {
          this._setOrderForm(order);

          this._orderService.getOrderItemDetails(this.orderId as number)
            .pipe(first())
            .subscribe((orderItemList: OrderItem[]) => {
              this.orderItemList = orderItemList;
            });
        });
    }

    this.paidSub = this.orderForm.controls['totalPaid'].valueChanges.subscribe((totalPaid: number) => {

      const rest: number = totalPaid - this.orderForm.controls['total'].value;

      this.orderForm.controls['rest'].setValue(rest);
    });
  }

  ngOnDestroy(): void {
    this.paidSub.unsubscribe();
  }

  public goBack(): void {
    this._router.navigate(['order']);
  }

  public save(): void {

    const dto: Order = this.orderForm.value;

    dto.menuItemList = this.orderItemList;

    if (this.mode === ModeType.NEW) {

      this._orderService.create(dto).pipe(first()).subscribe((order: Order) => {
        this.goBack();
      });
    } else if (this.mode === ModeType.EDIT) {

      this._orderService.update(dto).pipe(first()).subscribe((order: Order) => {
        this.goBack();
      });
    }
  }

  public saveAndPrint(): void {
    const dto: Order = this.orderForm.value;
    dto.menuItemList = this.orderItemList;
    dto.printOrder = true;

    if (this.mode === ModeType.NEW) {
      this._orderService.create(dto).pipe(first()).subscribe((order: Order) => {
        this.goBack();
      });
    } else if (this.mode === ModeType.EDIT) {
      this._orderService.update(dto).pipe(first()).subscribe((order: Order) => {
        this.goBack();
      });
    }
  }

  public print(): void {
    console.info("To Be Implemented");
  }

  public addItemToOrder(category: Category, menuItem: MenuItem): void {

    const orderItem = this.orderItemList?.find(orderItem => orderItem.menuItemId === menuItem.id);
    if (orderItem) {
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

    const totalPrice: number = this.orderItemList
      .map(orderItem => orderItem.totalPrice)
      .reduce(function (a, b) {
        return a + b;
      }, 0);

    this.orderForm.controls['total'].setValue(totalPrice);
    const rest: number = this.orderForm.controls['totalPaid'].value - this.orderForm.controls['total'].value;
    this.orderForm.controls['rest'].setValue(rest);
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
      note: [null, Validators.required],
      cashier: [null],
      takeAway: [false],
      total: [null],
      totalPaid: [null],
      rest: [null],
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
      note: dto?.note,
      cashier: dto?.cashier,
      takeAway: dto?.takeAway,
      total: dto?.total,
      totalPaid: null,
      rest: null,
    })
  }
}
