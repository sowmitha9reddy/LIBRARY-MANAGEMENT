package com.bridge.model;

public class Book {
     private int bookId;
     private String bookName;
     private String author;
     private boolean isBorrowed;
     private String ISBN;
     private boolean isBookAvialable;
	public Book() {
		
	}
	public Book(int bookId, String bookName, String author, boolean isBorrowed,String ISBN,boolean isBookAvialable) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.isBorrowed = false;
		this.ISBN=ISBN;
		this.isBookAvialable=true;
		}
	
	public Book(int bookId, boolean isBookAvialable) {
		
		this.bookId = bookId;
		this.isBookAvialable = isBookAvialable;
	}
	public boolean isBookAvialable() {
		return isBookAvialable;
	}
	public void setBookAvialable(boolean isBookAvialable) {
		this.isBookAvialable = isBookAvialable;
	}
	public Book(String bookName, String author, String iSBN) {
		
		this.bookName = bookName;
		this.author = author;
		this.ISBN = iSBN;
	}
	public Book(String bookName, String author, boolean isBorrowed, String iSBN) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.isBorrowed = isBorrowed;
		ISBN = iSBN;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isBorrowed() {
		return isBorrowed;
	}
	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	
	@Override
	public String toString() {
		
		return getBookId()+" "+getBookName()+ " "+ getAuthor()+" "+isBorrowed()+" "+getISBN() +" "+isBookAvialable();
	    
	   
	}
}
