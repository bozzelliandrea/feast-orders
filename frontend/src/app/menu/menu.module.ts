import { CategoryModal } from './component/category-modal/category-modal.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './component/menu/menu.component';
import { MenuRoutingModule } from './menu-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { MenuItemModalComponent } from './component/menu-item-modal/menu-item-modal.component';

@NgModule({
  declarations: [
    MenuComponent,
    CategoryModal,
    MenuItemModalComponent
  ],
  imports: [
    CommonModule,
    MenuRoutingModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class MenuModule { }
