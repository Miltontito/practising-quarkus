/**
 * Book API
 * This API allows CRUD operations on libros
 *//* tslint:disable:no-unused-variable member-ordering */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../model/libro';


@Injectable()
export class BookEndpointService {

  protected basePath = 'http://localhost:8082';
  public defaultHeaders = new HttpHeaders();

  constructor(protected httpClient: HttpClient) {
  }

  /**
   * Returns all the libros from the database
   *
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public getAllBooks(observe?: 'body', reportProgress?: boolean): Observable<Array<Book>> {

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    // to determine the Content-Type header
    const consumes: string[] = [];

    return this.httpClient.request<Array<Book>>('get', `${this.basePath}/api/libros`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Deletes an existing libro
   *
   * @param id Book identifier
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public deleteBook(id: number, observe?: 'body', reportProgress?: boolean): Observable<any> {

    if (id === null || id === undefined) {
      throw new Error('Required parameter id was null or undefined when calling apiBooksIdDelete.');
    }

    let headers = this.defaultHeaders;

    return this.httpClient.request<any>('delete', `${this.basePath}/api/libros/${encodeURIComponent(String(id))}`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Returns a libro for a given identifier
   *
   * @param id Book identifier
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public getBook(id: number, observe?: 'body', reportProgress?: boolean): Observable<Book> {

    if (id === null || id === undefined) {
      throw new Error('Required parameter id was null or undefined when calling apiBooksIdGet.');
    }

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    return this.httpClient.request<Book>('get', `${this.basePath}/api/libros/${encodeURIComponent(String(id))}`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Creates a valid libro
   *
   * @param body
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public createBook(body: Book, observe?: 'body', reportProgress?: boolean): Observable<string> {

    if (body === null || body === undefined) {
      throw new Error('Required parameter body was null or undefined when calling apiBooksPost.');
    }

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    // to determine the Content-Type header
    const consumes: string[] = [
      'application/json'
    ];
    headers = headers.set('Content-Type', consumes);

    return this.httpClient.request<string>('post', `${this.basePath}/api/libros`,
      {
        body: body,
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Updates an existing  libro
   *
   * @param body
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public updateBook(body: Book, observe?: 'body', reportProgress?: boolean): Observable<Book> {

    if (body === null || body === undefined) {
      throw new Error('Required parameter body was null or undefined when calling apiBooksPut.');
    }

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    // to determine the Content-Type header
    const consumes: string[] = [
      'application/json'
    ];
    headers = headers.set('Content-Type', consumes);

    return this.httpClient.request<Book>('put', `${this.basePath}/api/libros`,
      {
        body: body,
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }

  /**
   * Returns a random libro
   *
   * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
   * @param reportProgress flag to report request and response progress.
   */
  public getRandomBook(observe?: 'body', reportProgress?: boolean): Observable<Book> {

    let headers = this.defaultHeaders;

    // to determine the Accept header
    let httpHeaderAccepts: string[] = [
      'application/json'
    ];
    headers = headers.set('Accept', httpHeaderAccepts);

    return this.httpClient.request<Book>('get', `${this.basePath}/api/libros/random`,
      {
        headers: headers,
        observe: observe,
        reportProgress: reportProgress
      }
    );
  }
}
