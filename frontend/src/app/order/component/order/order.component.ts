import { Component, OnDestroy, OnInit } from '@angular/core';
import { OrderService } from '../../service/order.service';
import { Subscription } from "rxjs";
import { Order } from '../../interface/order.interface';
import { Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit, OnDestroy {

  public orders$: Observable<Order[]> | undefined;
  public selectedOrder?: Order;

  private _subscription: Subscription = new Subscription();

  constructor(private _orderService: OrderService,
    private _route: ActivatedRoute,
    private _router: Router) { }

  ngOnInit(): void {

    this.orders$ = this._orderService.getAll();
  }

  ngOnDestroy(): void {
    this._subscription.unsubscribe();
  }

  public onNewOrder(): void {
    this._router.navigate(['new'], { relativeTo: this._route });
  }

  public onEditOrder(): void {
    this._router.navigate([ this.selectedOrder?.id, 'edit'], { relativeTo: this._route });
  }

  public onViewOrder(): void {
    this._router.navigate([this.selectedOrder?.id], { relativeTo: this._route });
  }

  public onRowSelect(order: Order) {
    this.selectedOrder = order;
  }
}
