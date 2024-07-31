import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {Book} from "../../shared/model/libro";

@Component({
  templateUrl: './libro-delete.component.html',
  styles: []
})
export class BookDeleteComponent implements OnInit {

  libro: Book;

  constructor(private bookEndpointService: BookEndpointService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.load(params['id']);
    });
  }

  load(id: number) {
    this.bookEndpointService.getBook(id).subscribe((libro) => {
      this.libro = libro;
    });
  }

  delete(id: number) {
    this.bookEndpointService.deleteBook(id).subscribe((response) => {
      this.router.navigate(['/libro-list']);
    });
  }
}
