import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { Cart } from 'src/app/models/cart.model';
import { BookService } from 'src/app/services/book.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.scss']
})
export class StoreComponent implements OnInit {
  books: Book[] = [];
  cart: Cart | undefined;
  customerId: number = 1;
  quantities: number[] = [];
  constructor(private bookService: BookService, 
    private cartService: CartService) { }

  ngOnInit(): void {
    this.bookService.getBooks().subscribe(books => {
      this.books = books;
      this.books.forEach(book => this.quantities.push(0));
    });
    this.cartService.getCart(this.customerId).subscribe(cart => {
      this.cart = cart;
    });
  }

  addToCart(book: Book, quantity: number) {
    this.cartService.getCart(this.customerId).subscribe(cart => {
      if (cart) {
       if (cart.books.find(b => b.id === book.id)) {
         cart.books.forEach(b => {
            if (b.id === book.id) {
              b.amount += quantity;
            }
          });
       }
        else {
          cart.books.push(book);
          cart.books.forEach(b => {
            if (b.id === book.id) {
              b.amount = quantity;
            }
          });
        }
        cart.total = cart.total + book.price * quantity;
        this.cartService.updateCart(cart).subscribe(cart => {
          this.cart = cart;
        });
      } else {
        const cart: Cart = {
          id: 0,
          total: book.price*quantity,
          books: [book],
          customerId: this.customerId
        };
        this.cartService.addToCart(cart).subscribe(cart => {
          this.cart = cart;
        });
      }
    });
  }

  emptyCart() {
    this.cartService.getCart(this.customerId).subscribe(cart => 
      this.cartService.deleteCart(cart.id).subscribe());
  }


}
