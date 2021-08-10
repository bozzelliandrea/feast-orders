import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsComponent } from './component/stats/stats.component';
import { StatsRoutingModule } from './stats-routing.module'; 


@NgModule({
  declarations: [
    StatsComponent
  ],
  imports: [
    CommonModule,
    StatsRoutingModule
  ]
})
export class StatsModule { }
