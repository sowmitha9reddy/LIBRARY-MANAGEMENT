package com.library.main;

import java.util.Scanner;

import com.bridge.model.Library;
import com.bridge.model.Member;
import com.library.DAO.BorrowRecordDAO;
import com.library.DAO.MemberDAO;
import com.library.dao_impl.BookDAOImpl;
import com.library.dao_impl.BorrowRecordDAOImpl;
import com.library.dao_impl.MemberDAOImpl;

public class LibraryManagementSystem {
   
	public static void main(String[] args) {
    	
    	BookDAOImpl bookDAO = new BookDAOImpl();
    	MemberDAO memberDAO=new MemberDAOImpl();
    	BorrowRecordDAO borrowRecordDAO=new BorrowRecordDAOImpl();

    	Library library = new Library(bookDAO, memberDAO, borrowRecordDAO);
//        Library library = new Library(bookDAO);
//        Library library1 = new Library(memberDAO);
//        Library library2 = new Library(borrowRecordDAO);
        
        while (true) {
   		    System.out.println("\nLibrary Management System");
   		    System.out.println("1. Add Book");
   		   
   		    System.out.println("2. Remove Book");
   		
   		    System.out.println("3. Search Book by Id");
   		    System.out.println("4. Search Book by Name");
   		  System.out.println("5. Add Member");
   		  System.out.println("6.Remove Member");
   		  System.out.println("7.Search Member by Id");
   		  System.out.println("8.Borrow Book");
   		  System.out.println("9.Return Book");
   		  System.out.println("10.view Borrowing History");
   		  System.out.println("11.view Current Borrowed Books");
   		  System.out.println("12.view All Books");
   		   
   		   System.out.println();
   		    System.out.print("Enter your choice: ");
   		
           Scanner scanner=new Scanner(System.in);
		int choice = scanner.nextInt();
           switch (choice) {
               case 1:
            	   library.addBook();
                 
                   break;
             
               case 2:
            	   library.removeBook();
            	   break;
               case 3:
            	   library.searchBookById();
                   break;
               case 4:
            	   library.serachBookByName();
               	
               	break;
               case 5:
            	   library.addMember();
               	
               	break;
               case 6:
            	   library.removeMemeber();
               	
               	break;
               case 7:
            	   library.searchMemeberById();
               	
               	break;
               case 8:
            	   library.borrowBook();
               	
               	break;
               case 9:
            	   library.returnBook();
               	
               	break;
               case 10:
            	   library.viewBorrowingHistory();
               	
               	break;
               case 11:
            	   library. viewCurrentBorrowedBooks();
               	
               	break;
               case 12:
            	   library. getAllBooks();
               	
               	break;
              
            	  
               default:
                   System.out.println("Invalid choice");
           }
       }
       
      
    	
    }
}
