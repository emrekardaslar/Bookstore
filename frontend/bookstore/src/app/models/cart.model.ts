import { Book } from "./book.model";

export interface Cart {
    id: number;
    total: number;
    books: Book[];
    customerId: number;
}