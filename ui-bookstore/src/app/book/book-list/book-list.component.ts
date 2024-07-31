import {Component, OnInit} from '@angular/core';

import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {Book} from "../../shared/model/libro";

@Component({
  templateUrl: './libro-list.component.html',
  styles: []
})
export class BookListComponent implements OnInit {

  libros?: Book[];

  constructor(private numberEndpointService: BookEndpointService) {
  }

  ngOnInit(): void {
    this.listBooks();
  }

  listBooks(): void {
    this.numberEndpointService.getAllBooks().subscribe(libros => this.libros = libros);
  }
}
