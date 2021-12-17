import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Book } from '../models/book.model';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }
  private apiServerUrl = environment.apiBaseUrl;

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiServerUrl}/api/v1/book`);
  }

  getBook(id: string): Observable<Book> {
    return this.http.get<Book>(`${this.apiServerUrl}/api/v1/book/${id}`); 
  }

  addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(`${this.apiServerUrl}/api/v1/book`, book);
  }

  updateBook(book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.apiServerUrl}/api/v1/book/${book.id}`, book);
  }

  deleteBook(id: number): Observable<Book> {
    return this.http.delete<Book>(`${this.apiServerUrl}/api/v1/book/${id}`);
  }
}
