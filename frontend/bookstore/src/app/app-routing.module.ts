import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomersComponent } from './components/customers/customers.component';
import { BookComponent } from './components/home/book.component';
import { StoreComponent } from './components/store/store.component';

const routes: Routes = [
  { path: '', component: BookComponent },
  { path: 'books', component: BookComponent },
  { path: 'customers', component: CustomersComponent},
  { path: 'books', component: BookComponent},
  { path: 'store', component: StoreComponent}  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
