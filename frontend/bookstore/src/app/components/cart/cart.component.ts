import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { Cart } from 'src/app/models/cart.model';
import { BookService } from 'src/app/services/book.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  customerId: number = 1
  cart: Cart | any;   
  constructor(private cartService: CartService, 
    private bookService: BookService) { }

  ngOnInit(): void {
    this.cartService.getCart(this.customerId).subscribe(cart => {
      this.cart = cart;
    });
  }

  emptyCart() {
    this.cartService.getCart(this.customerId).subscribe(cart => 
      this.cartService.deleteCart(cart.id).subscribe());
  }

  removeFromCart(book: Book) {
    this.cartService.getCart(this.customerId).subscribe(cart => {
      cart.books = cart.books.filter(b => b.id !== book.id);
      cart.total = cart.total - book.price;
      this.cartService.updateCart(cart).subscribe(cart => {
        this.cart = cart;
      });
    });
  }

  getTotal() {
    let total = 0;
    if (this.cart) {
      this.cart.books.forEach((book: Book) => {
        total += book.price * book.amount;
      });
    }
    return total;
  }


  updateCart(book: Book, quantity: number) {
    this.cartService.getCart(this.customerId).subscribe(cart => {
      cart.books = cart.books.filter(b => b.id !== book.id);
      cart.total = cart.total - book.price;
      cart.books.push(book);
      cart.total = cart.total + book.price*quantity;
      this.cartService.updateCart(cart).subscribe(cart => {
        this.cart = cart;
      });
    });
  }

  

  checkout() {
    //TODO: implement checkout
    //TODO: subtract books from stock
    this.cartService.getCart(this.customerId).subscribe(cart => {
      cart.books = [];
      cart.total = 0;
      this.cartService.updateCart(cart).subscribe(cart => {
        this.cart = cart;
      }
    );
    });
  }

}
