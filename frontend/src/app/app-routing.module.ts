import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'order', loadChildren: () => import('./order/order.module').then(m => m.OrderModule)},
  {path: 'menu', loadChildren: () => import('./menu/menu.module').then(m => m.MenuModule)},
  {path: 'stations', loadChildren: () => import('./stations/stations.module').then(m => m.StationsModule)},
  {path: 'stats', loadChildren: () => import('./stats/stats.module').then(m => m.StatsModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
