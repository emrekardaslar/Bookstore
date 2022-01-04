import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';
import { DialogService } from 'src/app/services/dialog.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {
  books: Book[] = [];
  displayedColumns: string[] = ['id', 'name', 'author', 'price', 'amount', 'action'];
  dataSource: any = new MatTableDataSource<Book>(this.books);
  checkedRows: any[] = [];
  selectAllButton = 'check_box_outline_blank';

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  @ViewChild(MatSort) sort: MatSort | undefined;
  
  constructor(private bookService: BookService, 
    private _liveAnnouncer: LiveAnnouncer,
    private dialogService: DialogService) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  ngAfterViewInit() {
    this.dataSource.paginator =  this.paginator;
    this.dataSource.sort = this.sort;
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  changeChkState(id: number) {
    if (this.checkedRows.includes(id)) {
      this.checkedRows = this.checkedRows.filter(item => item !== id);
    } else {
      this.checkedRows.push(id);
    }
  }

  deleteSelectedBooks() { 
    this.dialogService.openConfirmDialog()
      .afterClosed().subscribe((res:any) => {
        if (res) {
         this.checkedRows.forEach((id:any) => {
            this.bookService.deleteBook(id).subscribe(() => {
              this.books = this.books.filter(book => book.id !== id);
              this.dataSource = new MatTableDataSource<Book>(this.books);
              this.dataSource.paginator =  this.paginator;
              this.dataSource.sort = this.sort;
              this.checkedRows = [];
              this.selectAllButton = 'check_box_outline_blank';
            });
          }
        );
        }
      });
  }

  selectAllWithToggle() {
    if (this.selectAllButton == 'check_box') {
      this.selectAllButton = 'check_box_outline_blank';
      this.checkedRows = [];
    }
    else {
      this.selectAllButton = 'check_box';
      this.checkedRows = this.books.map(book => book.id);
    }
  }

  deleteBook(id: number) {
    this.dialogService.openConfirmDialog().afterClosed().subscribe((res: boolean) => {
    if (res == true) 
      this.bookService.deleteBook(id).subscribe(() => {
        this.books = this.books.filter(book => book.id !== id);
        this.selectAllButton = 'check_box_outline_blank';
        this.dataSource = new MatTableDataSource<Book>(this.books);
        this.dataSource.paginator =  this.paginator;
        this.dataSource.sort = this.sort;
      }); 
    });
  }

  addBook() {
    this.dialogService.openAddBookDialog().afterClosed().subscribe((res: Book) => {
      if (res) {
        this.bookService.addBook(res).subscribe(
          () => {
            this.loadBooks();
          }
        );
      }
    });
  }

  editBook(book: Book) {
    const id: number = book.id;
    this.dialogService.openEditBookDialog(book).afterClosed().subscribe((res: Book) => {
      if (res) {
        res.id = id;
        this.bookService.updateBook(res).subscribe(
          () => {
            this.loadBooks();
          }
        );
      }
    });
  }

  loadBooks() {
    this.bookService.getBooks().subscribe(books => {
      this.books = books;
      this.dataSource = new MatTableDataSource<Book>(this.books);
      this.dataSource.paginator =  this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

}

//TODO: create a new generic component for table