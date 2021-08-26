import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrinterComponent } from './component/printer/printer.component';
import { PrinterRoutingModule } from './printer-routing.module';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PrinterComponent
  ],
    imports: [
        CommonModule,
        PrinterRoutingModule,
        ReactiveFormsModule
    ]
})
export class PrinterModule { }
