import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PrinterComponent} from './component/printer/printer.component';
import {PrinterRoutingModule} from './printer-routing.module';
import {ReactiveFormsModule} from "@angular/forms";
import {PrinterCfgModalComponent} from './component/printer-cfg-modal/printer-cfg-modal.component';
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    PrinterComponent,
    PrinterCfgModalComponent
  ],
  imports: [
    CommonModule,
    PrinterRoutingModule,
    ReactiveFormsModule,
    SharedModule
  ]
})
export class PrinterModule {
}
