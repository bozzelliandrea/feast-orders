import { ModalService } from './service/modal.service';
import { ModalContainerComponent } from './component/modal-container/modal-container.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
@NgModule({
  declarations: [
    NavbarComponent,
    PageNotFoundComponent,
    ModalContainerComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    NavbarComponent,
    PageNotFoundComponent,
    CommonModule,
    ModalContainerComponent
  ],
  providers: [ModalService]
})
export class SharedModule { }
