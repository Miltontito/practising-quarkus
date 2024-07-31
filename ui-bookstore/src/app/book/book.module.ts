import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { HttpClientModule} from "@angular/common/http";

import { BookRoutingModule } from './libro-routing.module';
import { BookListComponent } from './libro-list/libro-list.component';
import { BookFormComponent } from './libro-form/libro-form.component';
import { BookDetailComponent } from './libro-detail/libro-detail.component';
import { BookDeleteComponent } from './libro-delete/libro-delete.component';
import { BookRandomComponent } from './libro-random/libro-random.component';
import { BookEndpointService} from "../shared/api/bookEndpoint.service";


@NgModule({
  declarations: [BookListComponent, BookFormComponent, BookDetailComponent, BookDeleteComponent, BookRandomComponent],
  imports: [
    CommonModule,
    FormsModule,
    BookRoutingModule,
    HttpClientModule
  ],
  providers:[BookEndpointService]
})
export class BookModule { }
