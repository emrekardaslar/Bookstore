import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddBookDialogComponent } from '../components/add-book-dialog/add-book-dialog.component';
import { AddCustomerDialogComponent } from '../components/add-customer-dialog/add-customer-dialog.component';
import { MatConfirmDialogComponent } from '../components/mat-confirm-dialog/mat-confirm-dialog.component';
import { Book } from '../models/book.model';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root'
})
export class DialogService {
  openAddCustomerDialog() {
    return this.dialog.open(AddCustomerDialogComponent, {
      disableClose: false
    })
  }

  openEditCustomerDialog(customer: Customer) {
    return this.dialog.open(AddCustomerDialogComponent, {
      disableClose: false,
      data: {
        customer: customer
      }
    })
  }

  constructor(private dialog: MatDialog) { }

  openConfirmDialog(): any {
    return this.dialog.open(MatConfirmDialogComponent, {
      width: '390px',
      disableClose: false
    })
  }

  openAddBookDialog(): any {
    return this.dialog.open(AddBookDialogComponent, {
      disableClose: false
    })
  }

  openEditBookDialog(book: Book): any {
    return this.dialog.open(AddBookDialogComponent, {
      data: {
        book: book
      },
      disableClose: false
    })
  }
}
