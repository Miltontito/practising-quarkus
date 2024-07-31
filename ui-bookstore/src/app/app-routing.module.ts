import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: () => import('./libro/libro.module').then(m => m.BookModule) },
  { path: 'libro-random', loadChildren: () => import('./libro/libro.module').then(m => m.BookModule) },
  { path: 'inventory', loadChildren: () => import('./inventory/inventory.module').then(m => m.InventoryModule) },
  { path: 'number-generate', loadChildren: () => import('./number/number.module').then(m => m.NumberModule) },
  { path: '**', loadChildren: () => import('./libro/libro.module').then(m => m.BookModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
