import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrinterComponent } from './component/printer/printer.component';
import { PrinterRoutingModule } from './printer-routing.module';


@NgModule({
  declarations: [
    PrinterComponent
  ],
  imports: [
    CommonModule,
    PrinterRoutingModule
  ]
})
export class PrinterModule { }
