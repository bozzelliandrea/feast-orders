import { ModeType } from './../shared/enums/mode.enum';
import { OrderFormComponent } from './component/order-form/order-form.component';
import { OrderComponent } from './component/order/order.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    {path: '', component: OrderComponent},
    {path: 'new', component: OrderFormComponent, data: {mode: ModeType.NEW}},
    {path: ':id', component: OrderFormComponent, data: {mode: ModeType.VIEW}},
    {path: ':id/edit', component: OrderFormComponent, data: {mode: ModeType.EDIT}}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule { }
