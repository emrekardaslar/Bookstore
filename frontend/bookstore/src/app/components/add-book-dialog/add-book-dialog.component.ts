import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-book-dialog',
  templateUrl: './add-book-dialog.component.html',
  styleUrls: ['./add-book-dialog.component.scss']
})
export class AddBookDialogComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,  private fb: FormBuilder, private dialogRef: MatDialogRef<AddBookDialogComponent>) { }
  isEdit: boolean = false;
  bookForm = this.fb.group ({
    name: ['', Validators.required],
    author: ['', Validators.required],
    price: ['', Validators.required],
    amount: ['', Validators.required],
    description: ['', Validators.required],
    imgPath: ['', Validators.required]
  })

  ngOnInit(): void {
    if (this.data && this.data.book) {
      this.isEdit = true;
      this.bookForm.patchValue(this.data.book);
    }
  }

  onSubmit() {
    this.dialogRef.close(this.bookForm.value);
  }

}
