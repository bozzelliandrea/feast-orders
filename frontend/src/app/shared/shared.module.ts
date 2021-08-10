import { OrderCardComponent } from './component/order-card/order-card.component';
import { OrderPanelComponent } from './component/order-panel/order-panel.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './../app-routing.module';

@NgModule({
  declarations: [
    NavbarComponent,
    DashboardComponent,
    OrderPanelComponent,
    OrderCardComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  exports: [
    NavbarComponent,
    DashboardComponent,
    OrderPanelComponent
  ]
})
export class SharedModule { }
