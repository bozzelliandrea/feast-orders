import { PageNotFoundComponent } from './shared/component/page-not-found/page-not-found.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  { path: '',   redirectTo: '/dashboard', pathMatch: 'full' },
  {path: 'dashboard', loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)},
  {path: 'order', loadChildren: () => import('./order/order.module').then(m => m.OrderModule)},
  {path: 'menu', loadChildren: () => import('./menu/menu.module').then(m => m.MenuModule)},
  {path: 'printer', loadChildren: () => import('./printer/printer.module').then(m => m.PrinterModule)},
  {path: 'stats', loadChildren: () => import('./stats/stats.module').then(m => m.StatsModule)},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
