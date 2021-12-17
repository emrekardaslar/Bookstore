import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  books: Book[] = [];
  displayedColumns: string[] = ['position', 'name', 'author', 'price', 'amount'];
  dataSource: any = new MatTableDataSource<Book>(this.books);

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  
  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.bookService.getBooks().subscribe(books => {
      this.books = books;
      this.dataSource = new MatTableDataSource<Book>(this.books);
      this.dataSource.paginator =  this.paginator;
    });

  }

  ngAfterViewInit() {
    this.dataSource.paginator =  this.paginator;
  }

}
