import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { CustomersComponent } from './components/customers/customers.component';
import { BookComponent } from './components/home/book.component';
import { StoreComponent } from './components/store/store.component';

const routes: Routes = [
  { path: '', component: BookComponent },
  { path: 'books', component: BookComponent },
  { path: 'customers', component: CustomersComponent},
  { path: 'store', component: StoreComponent},
  { path: 'cart', component: CartComponent}  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
