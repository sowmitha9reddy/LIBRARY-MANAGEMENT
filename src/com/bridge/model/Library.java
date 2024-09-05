package com.bridge.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.library.DAO.BookDAO;
import com.library.DAO.BorrowRecordDAO;
import com.library.DAO.MemberDAO;

public class Library {
	  
		private BookDAO bookDAO;
	    private MemberDAO memberDAO;
	    private BorrowRecordDAO borrowRecordDAO;
	    Book book=null;
	    Member member=null;
	    BorrowRecord borrowRecord=null;
		Scanner scan=new Scanner(System.in);
	

	    public Library(BookDAO bookDAO, MemberDAO memberDAO, BorrowRecordDAO borrowRecordDAO) {
	        this.bookDAO = bookDAO;
	        this.memberDAO = memberDAO;
	        this.borrowRecordDAO = borrowRecordDAO;
	    }
	    

	   
	    public Library(BookDAO bookDAO) {
			
			this.bookDAO = bookDAO;
		}
	    



		public Library(MemberDAO memberDAO) {
			
			this.memberDAO = memberDAO;
		}
        
		


		public Library(BorrowRecordDAO borrowRecordDAO) {
			super();
			this.borrowRecordDAO = borrowRecordDAO;
		}



		public void addBook() {
			System.out.println("Enter the book details(BookName,Author,ISBN)");
			String s=scan.next();
			
			String[] values=s.split(",");
			String BookName=values[0];
			String Author=values[1];
			String ISBN=values[2];
			book=new Book(BookName,Author,ISBN);
			int x=bookDAO.addBook(book);
			
			if( x!=0) {
	       System.out.println( x+" Book Add successfully");
			}
			else {
				System.out.println( "Book is not added");
			}
	    }

	    public void removeBook() {
	    	System.out.println("Enter the Book Id to be deleted:");
	    	int bookId=scan.nextInt();	    	
	       int y=bookDAO.deleteBook(bookId);
	    	if(y!=0) {
	       System.out.println(y +" Book Deleted sucessfully");
	    	}
	    	else {
	    		System.out.println("Book is not deleted");
	    	}
	       
	    }
        public void searchBookById() {
        	System.out.println("Enter the Book Id to be Search:");
	    	int bookId=scan.nextInt();	  
        	Book b=bookDAO.getBook(bookId);
            System.out.println(b);
        }
        public void serachBookByName() {
        	System.out.println("Enter the BookName to be search:");
	    	String bookName=scan.nextLine();	  
        	bookDAO.getBook(bookName);
        }
        
        public void addMember() {
        
			System.out.println("Enter the Memeber details(name,email)");
			String s=scan.next();
			
			
			String[] values=s.split(",");
			String name=values[0];
			String email=values[1];
			
			member=new Member(name,email);
			
			int x1=memberDAO.addMember(member);
			if(x1!=0) {
				System.out.println(x1+ "Member added successfully");
			}
			else {
				System.out.println("Memeber is unable to add");
			}
	    }
        public void removeMemeber() {
	    	System.out.println("Enter the Member Id to be deleted:");
	    	int memberId=scan.nextInt();	    	
	       int y=memberDAO.deleteMember(memberId);
	    	if(y!=0) {
	       System.out.println(y +" Member Deleted sucessfully");
	    	}
	    	else {
	    		System.out.println("Member is not deleted");
	    	}
	       
	    }
        public void searchMemeberById() {
        	System.out.println("Enter the Member  Id to be Search:");
	    	int MemeberId=scan.nextInt();	  
        	Member m=memberDAO.getMember(MemeberId);
            System.out.println(m);
        }
        
      
        public void borrowBook() {
            System.out.print("Enter Member ID: ");
            int memberId = scan.nextInt();

            System.out.print("Enter Book ID: ");
            int bookId = scan.nextInt();

            Member member = memberDAO.getMember(memberId);
            Book book = bookDAO.getBook(bookId);

            if (member != null && book != null) {
                final int MAX_BORROW_LIMIT = 5;

               
                if (book.isBookAvialable()) {
                    List<BorrowRecord> borrowedBooks = member.getBorrowedRecords();
                    System.out.println(borrowedBooks.size());

                    if (borrowedBooks.size() < MAX_BORROW_LIMIT) {
                        
                        BorrowRecord record = new BorrowRecord(book.getBookId(), member.getMemberId());

                     
                        int recordId = borrowRecordDAO.addBorrowRecord(record);

                        if (recordId != -1) {
                            record.setBorrowRecordId(recordId);
                            member.borrowBook(record);

                            
                            bookDAO.setBookAvailability(book.getBookId(), false);
                            System.out.println("Book borrowed successfully.");
                        } else {
                            System.out.println("Failed to borrow the book.");
                        }
                    } else {
                        System.out.println("Cannot borrow more than " + MAX_BORROW_LIMIT + " books.");
                    }
                } else {
                    System.out.println("The book is currently not available.");
                }
            } else {
                System.out.println("Invalid Member ID or Book ID.");
            }
        }


        
        private static final int MAX_BORROW_DAYS = 30;
	    private static final double FINE_PER_DAY = 1.0;


	    public void returnBook() {
	    	  System.out.print("Enter Member ID: ");
	            int memberId = scan.nextInt();

	            System.out.print("Enter Book ID: ");
	            int bookId = scan.nextInt();

	           
	            Member member = memberDAO.getMember(memberId); 
	            Book book = bookDAO.getBook(bookId);        

	           
	            if (member != null && book != null) {
	        BorrowRecord record = borrowRecordDAO.getBorrowRecordForBook(book.getBookId(), member.getMemberId());

	        if (record != null) {
	           
	            LocalDate returnDate = LocalDate.now();
	            record.setReturnDate(returnDate);
	            borrowRecordDAO. updateBorrowRecord( memberId, bookId);

	            Period period = Period.between(record.getBorrowDate(), returnDate);
	            long daysBorrowed = period.getDays();
	            if (daysBorrowed > MAX_BORROW_DAYS) {
	                double fine = (daysBorrowed - MAX_BORROW_DAYS) * FINE_PER_DAY;
	                System.out.println("The book is returned late. Fine: " + fine);
	            } else {
	                System.out.println("Book returned successfully.");
	            }

	         
	            bookDAO.setBookAvailability(book.getBookId(), true);
                member.returnBook(record);
	        } else {
	            System.out.println("No record found for the borrowed book.");
	        }
	      }
	    }
	
	    public void viewBorrowingHistory() {
	        System.out.print("Enter Member ID: ");
	        int memberId = scan.nextInt();

	        Member member = memberDAO.getMember(memberId); 
	        if (member == null) {
	            System.out.println("Member not found.");
	            return;
	        }

	        
	        List<BorrowRecord> borrowHistory = borrowRecordDAO.getBorrowRecordsByMemberId(memberId);

	        Optional<List<BorrowRecord>> optionalBorrowHistory = Optional.ofNullable(borrowHistory);

	        optionalBorrowHistory.ifPresentOrElse(records -> {
	            if (records.isEmpty()) {
	                System.out.println("No borrowing history found for Member ID: " + member.getMemberId());
	            } else {
	                System.out.println("Borrowing History for Member ID: " + member.getMemberId());
	                records.stream()
	                       .forEach(record -> {
	                           System.out.println("Book ID: " + record.getBookId() +
	                                              ", Borrow Date: " + record.getBorrowDate() +
	                                              ", Return Date: " + (record.getReturnDate() != null ? record.getReturnDate() : "Not Returned"));
	                       });
	            }
	        }, () -> System.out.println("No borrowing history available."));
	    }
	    
	    
	    
	    public void viewCurrentBorrowedBooks() {
	        System.out.print("Enter Member ID: ");
	        int memberId = scan.nextInt();

	        Member member = memberDAO.getMember(memberId); 
	        if (member == null) {
	            System.out.println("Member not found.");
	            return;
	        }

	      
	        List<BorrowRecord> borrowRecords = borrowRecordDAO.getBorrowRecordsByMemberId(memberId);

	        Optional<List<BorrowRecord>> optionalBorrowRecords = Optional.ofNullable(borrowRecords);

	        optionalBorrowRecords.ifPresentOrElse(records -> {
	            if (records.isEmpty()) {
	                System.out.println("No current borrowed books found for Member ID: " + member.getMemberId());
	            } else {
	                System.out.println("Current Borrowed Books for Member ID: " + member.getMemberId());
	                records.stream()
	                       .filter(record -> record.getReturnDate() == null) // Filter for books that haven't been returned
	                       .forEach(record -> {
	                           System.out.println("Book ID: " + record.getBookId() +
	                                              ", Borrow Date: " + record.getBorrowDate());
	                       });
	            }
	        }, () -> System.out.println("No current borrowed books available."));
	    }

	  
	    
      public void getAllBooks() {
    	  List<Book> books=bookDAO.getAllBooks(); 
    	  System.out.println("Books: ");
    	  for(Book b:books) {
    		  System.out.println(b);
    	  }
      }
        
	
}
