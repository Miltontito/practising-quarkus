import { Component, OnInit } from '@angular/core';
import {Book} from "../../shared/model/libro";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";

@Component({
  templateUrl: './libro-random.component.html',
  styles: []
})
export class BookRandomComponent implements OnInit {

  libro: Book;

  constructor(private bookEndpointService: BookEndpointService) { }

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.bookEndpointService.getRandomBook().subscribe((libro) => {
      this.libro = libro;
    });
  }
}
