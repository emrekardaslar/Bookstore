import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddBookDialogComponent } from '../components/add-book-dialog/add-book-dialog.component';
import { MatConfirmDialogComponent } from '../components/mat-confirm-dialog/mat-confirm-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(private dialog: MatDialog) { }

  openConfirmDialog(): any {
    return this.dialog.open(MatConfirmDialogComponent, {
      width: '390px',
      disableClose: false
    })
  }

  openAddBookDialog(): any {
    return this.dialog.open(AddBookDialogComponent, {
      width: '390px',
      disableClose: false
    })
  }
}
