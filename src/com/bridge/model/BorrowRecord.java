package com.bridge.model;
import java.sql.Date;
import java.time.LocalDate;
public class BorrowRecord {
	    
	    private int borrowRecordId;
	    private int bookId;
	    private int memberId;
	    private LocalDate borrowDate;
	    private LocalDate returnDate;
		public BorrowRecord() {
			
			
		}
		public BorrowRecord(int borrowRecordId, int bookId, int memberId) {
			
			this.borrowRecordId = borrowRecordId;
			this.bookId = bookId;
			this.memberId = memberId;
			
			
		}
		
		
		public BorrowRecord(int bookId, int memberId) {
			super();
			this.bookId = bookId;
			this.memberId = memberId;
		}
		public BorrowRecord(int borrowRecordId, int bookId, int memberId, LocalDate borrowDate2) {
		
			this.borrowRecordId = borrowRecordId;
			this.bookId = bookId;
			this.memberId = memberId;
			this.borrowDate = borrowDate2;
		}
	
		public BorrowRecord(int borrowRecordId, int bookId, int memberId, LocalDate borrowDate, LocalDate returnDate) {
			
			this.borrowRecordId = borrowRecordId;
			this.bookId = bookId;
			this.memberId = memberId;
			this.borrowDate = borrowDate;
			this.returnDate = returnDate;
		}
		public BorrowRecord(int bookId, int memberId, LocalDate borrowDate) {
			super();
			this.bookId = bookId;
			this.memberId = memberId;
			this.borrowDate = borrowDate;
		}
		public int getBorrowRecordId() {
			return borrowRecordId;
		}
		public void setBorrowRecordId(int borrowRecordId) {
			this.borrowRecordId = borrowRecordId;
		}
		public int getBookId() {
			return bookId;
		}
		public void setBookId(int bookId) {
			this.bookId = bookId;
		}
		public int getMemberId() {
			return memberId;
		}
		public void setMemberId(int memberId) {
			this.memberId = memberId;
		}
		public LocalDate getBorrowDate() {
			return  borrowDate;
		}
		public void setBorrowDate(LocalDate borrowDate) {
			this.borrowDate = borrowDate;
		}
		public LocalDate getReturnDate() {
			return returnDate;
		}
		public void setReturnDate(LocalDate returnDate2) {
			this.returnDate = returnDate2;
		}
		
	   
}
