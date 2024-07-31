import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookRandomComponent } from "./libro-random/libro-random.component";
import { BookListComponent } from "./libro-list/libro-list.component";
import {BookDeleteComponent} from "./libro-delete/libro-delete.component";
import {BookDetailComponent} from "./libro-detail/libro-detail.component";
import {BookFormComponent} from "./libro-form/libro-form.component";


const routes: Routes = [
  { path: '', component:BookRandomComponent},
  { path: 'libro-delete/:id', component:BookDeleteComponent},
  { path: 'libro-detail/:id', component:BookDetailComponent},
  { path: 'libro-form', component:BookFormComponent},
  { path: 'libro-list', component:BookListComponent},
  { path: 'libro-random', component:BookRandomComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
