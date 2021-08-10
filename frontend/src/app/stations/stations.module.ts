import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StationsComponent } from './component/stations/stations.component';
import { StationsRoutingModule } from './stations-routing.module';


@NgModule({
  declarations: [
    StationsComponent
  ],
  imports: [
    CommonModule,
    StationsRoutingModule
  ]
})
export class StationsModule { }
