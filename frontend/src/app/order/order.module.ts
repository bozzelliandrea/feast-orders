import { OrderFormComponent } from './component/order-form/order-form.component';
import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { OrderComponent } from './component/order/order.component';
import { OrderRoutingModule } from './order-routing.module';

@NgModule({
  declarations: [
    OrderComponent,
    OrderFormComponent
  ],
  imports: [
    OrderRoutingModule,
    SharedModule
  ]
})
export class OrderModule { }
