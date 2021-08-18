import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
@NgModule({
  declarations: [
    NavbarComponent,
    PageNotFoundComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    NavbarComponent,
    PageNotFoundComponent,
    CommonModule
  ]
})
export class SharedModule { }
