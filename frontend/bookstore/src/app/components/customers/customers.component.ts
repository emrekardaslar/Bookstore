import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Customer } from 'src/app/models/customer.model';
import { CustomerService } from 'src/app/services/customer.service';
import { DialogService } from 'src/app/services/dialog.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  customers: Customer[] = [];
  displayedColumns: string[] = ['id', 'username', 'email', 'dob', 'action'];
  dataSource: any = new MatTableDataSource<Customer>(this.customers);

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  @ViewChild(MatSort) sort: MatSort | undefined;
  constructor(private customerService: CustomerService, 
    private _liveAnnouncer: LiveAnnouncer,
    private dialogService: DialogService) { }

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers() {
    this.customerService.getCustomers().subscribe(customers => {
      this.customers = customers;
      this.dataSource = new MatTableDataSource<Customer>(this.customers);
      this.dataSource.paginator =  this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  editCustomer(customer: Customer) {
    const id: number = customer.id;
    this.dialogService.openEditCustomerDialog(customer).afterClosed().subscribe(customer => {
      if (customer) {
        customer.id = id;
        this.customerService.updateCustomer(customer).subscribe(updatedCustomer => {
          const index = this.customers.findIndex(customer => customer.id === updatedCustomer.id);
          this.customers[index] = updatedCustomer;
          this.dataSource = new MatTableDataSource<Customer>(this.customers);
          this.dataSource.paginator =  this.paginator;
          this.dataSource.sort = this.sort;
        });
      }
    }
    );
    
  }

  deleteCustomer(id: number) {
    this.dialogService.openConfirmDialog().afterClosed().subscribe((res: boolean) => {
      if (res == true) 
        this.customerService.deleteCustomer(id).subscribe(() => {
          this.customers = this.customers.filter(customer => customer.id !== id);
          this.dataSource = new MatTableDataSource<Customer>(this.customers);
          this.dataSource.paginator =  this.paginator;
          this.dataSource.sort = this.sort;
        }); 
    });
  }

  changeChkState(id: number) {
    
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  addCustomer() {
    this.dialogService.openAddCustomerDialog().afterClosed().subscribe(customer => {
      if (customer) {
        this.customerService.addCustomer(customer).subscribe(newCustomer => {
          this.customers.push(newCustomer);
          this.dataSource = new MatTableDataSource<Customer>(this.customers);
          this.dataSource.paginator =  this.paginator;
          this.dataSource.sort = this.sort;
        });
      }
    }
    );
  }


}
