import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-book-dialog',
  templateUrl: './add-book-dialog.component.html',
  styleUrls: ['./add-book-dialog.component.scss']
})
export class AddBookDialogComponent implements OnInit {

  constructor(private fb: FormBuilder, private dialogRef: MatDialogRef<AddBookDialogComponent>) { }
  bookForm = this.fb.group ({
    name: ['', Validators.required],
    author: ['', Validators.required],
    price: ['', Validators.required],
    amount: ['', Validators.required]
  })

  ngOnInit(): void {
  }

  addBook() {
    
  }

  onSubmit() {
    this.dialogRef.close(this.bookForm.value);
  }

}
