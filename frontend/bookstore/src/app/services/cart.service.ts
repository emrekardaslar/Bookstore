import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cart } from '../models/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  constructor(private http: HttpClient) { }

  addToCart(cart: Cart): Observable<Cart> {
    return this.http.post<Cart>(`${environment.apiBaseUrl}/api/v1/cart`, cart);
  }

  getCart(customerId: number): Observable<Cart> {
    return this.http.get<Cart>(`${environment.apiBaseUrl}/api/v1/cart/${customerId}`);
  }

  updateCart(cart: Cart): Observable<Cart> {
    return this.http.put<Cart>(`${environment.apiBaseUrl}/api/v1/cart`, cart);
  }

  deleteCart(id: number): Observable<Cart> {
    return this.http.delete<Cart>(`${environment.apiBaseUrl}/api/v1/cart/${id}`);
  }
    
}
