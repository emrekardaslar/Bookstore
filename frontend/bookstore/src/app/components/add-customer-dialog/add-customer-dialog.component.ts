import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-customer-dialog',
  templateUrl: './add-customer-dialog.component.html',
  styleUrls: ['./add-customer-dialog.component.scss']
})
export class AddCustomerDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,  
  private fb: FormBuilder, 
  private dialogRef: MatDialogRef<AddCustomerDialogComponent>) { }

  isEdit: boolean = false;
  customerForm = this.fb.group ({
    username: ['', Validators.required],
    email: ['', Validators.required],
    dob: ['', Validators.required],
  })

  ngOnInit(): void {
    if (this.data && this.data.customer) {
      this.isEdit = true;
      this.customerForm.patchValue(this.data.customer);
    }
  }

  onSubmit() {
    this.dialogRef.close(this.customerForm.value);
  }
  //TODO: Add validation for dob
}
