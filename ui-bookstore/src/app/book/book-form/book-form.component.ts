import { Component, OnInit } from '@angular/core';
import {Book} from "../../shared/model/libro";
import {BookEndpointService} from "../../shared/api/bookEndpoint.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  templateUrl: './libro-form.component.html',
  styles: []
})
export class BookFormComponent implements OnInit {

  libro:Book;

  constructor(private bookEndpointService: BookEndpointService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  save() {
    this.bookEndpointService.createBook(this.libro).subscribe((response: string) => {
      this.router.navigate(['/libro-list']);
    });
  }

}
