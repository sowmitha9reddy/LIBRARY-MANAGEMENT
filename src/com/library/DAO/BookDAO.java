package com.library.DAO;

import java.util.List;

import com.bridge.model.Book;

public interface BookDAO {
	int addBook(Book book);
    Book getBook(int bookId);
    Book getBook(String bookName);
    void updateBook(Book book);
   int deleteBook(int bookId);
    List<Book> getAllBooks();
    void setBookAvailability(int bookId,boolean available);
    
}
