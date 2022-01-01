import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {  }

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${environment.apiBaseUrl}/api/v1/customer`);
  }

  getCustomer(id: string): Observable<Customer> {
    return this.http.get<Customer>(`${environment.apiBaseUrl}/api/v1/customer/${id}`); 
  }

  addCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${environment.apiBaseUrl}/api/v1/customer`, customer);
  }

  updateCustomer(customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${environment.apiBaseUrl}/api/v1/customer/${customer.id}`, customer);
  }

  deleteCustomer(id: number): Observable<Customer> {
    return this.http.delete<Customer>(`${environment.apiBaseUrl}/api/v1/customer/${id}`);
  }
}
