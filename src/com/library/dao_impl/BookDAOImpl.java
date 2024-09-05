package com.library.dao_impl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bridge.model.Book;

import com.library.DAO.BookDAO;
import com.library.util.MyConnect;


public class BookDAOImpl implements BookDAO{

	
	
	  private Connection con;
	  private PreparedStatement stmt;
	   private ResultSet res;
	


	public BookDAOImpl() {
		MyConnect.getMyConnection();
		con=MyConnect.connect();
	}
	
	    static final String INSERT_QUERY = "INSERT INTO `book` (`bookName`, `author`, `isBorrowed`,`ISBN`,`isBookAvialable`) VALUES(?, ?,?, ?,?)";
	    static final String SELECT_QUERY = "SELECT * FROM `book` WHERE `bookId`=?";
	    static final String SELECT_QUERY_BY_NAME = "SELECT * FROM `book` WHERE `bookName`=?";
	    
	    static final String UPDATE_QUERY = "UPDATE `book` SET `bookName`=?, `author`=?, `isBorrowed`=? WHERE `bookId`=?";
	    static final String DELETE_QUERY = "DELETE FROM `book` WHERE `bookId`=?";
	    static final String SELECT_ALL_QUERY = "SELECT * FROM `book`";
	    private static final String UPDATE_AVAILABILITY_QUERY = "UPDATE book SET isBookAvialable = ? WHERE bookId = ?";

	   
	@Override
	public int addBook(Book book) {
		try {
            stmt = con.prepareStatement(INSERT_QUERY);
            stmt.setString(1, book.getBookName());
            stmt.setString(2, book.getAuthor());
            stmt.setBoolean(3, book.isBorrowed()); 
            stmt.setString(4, book.getISBN());
            stmt.setBoolean(5, book.isBookAvialable());
            
//            bookId int AI PK 
//            bookName varchar(255) 
//            author varchar(255) 
//            isBorrowed

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		
	}

	@Override
	public Book getBook(int bookId) {
		 Book book = null;
	        try {
	            stmt = con.prepareStatement(SELECT_QUERY);
	            stmt.setInt(1, bookId);

	            res = stmt.executeQuery();
	            if (res.next()) {
	                String bookName = res.getString("bookName");
	                String author = res.getString("author");
	                boolean isBorrowed = res.getBoolean("isBorrowed");
	                boolean isBookAvialable=res.getBoolean("isBookAvialable");
	                String ISBN=res.getString("ISBN");

	                book = new Book(bookId, bookName, author, isBorrowed,ISBN,isBookAvialable);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return book;
		
	}
	

	@Override
	public void updateBook(Book book) {
		 try {
	            stmt = con.prepareStatement(UPDATE_QUERY);
	            stmt.setString(1, book.getBookName());
	            stmt.setString(2, book.getAuthor());
	            stmt.setBoolean(3, book.isBorrowed());
	            stmt.setInt(4, book.getBookId());

	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public int deleteBook(int bookId) {
		 try {
	            stmt = con.prepareStatement(DELETE_QUERY);
	            stmt.setInt(1, bookId);
	           return stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return 0;
	}

	@Override
	public List<Book> getAllBooks() {
	    List<Book> books = new ArrayList<>();
	    String SELECT_ALL_QUERY = "SELECT * FROM book"; // Ensure this query matches your table and column names
	    
	    try (Statement st = con.createStatement(); ResultSet res = st.executeQuery(SELECT_ALL_QUERY)) {
	        while (res.next()) {
	            int bookId = res.getInt("bookId");
	            String bookName = res.getString("bookName");
	            String author = res.getString("author");
	            boolean isBorrowed = res.getBoolean("isBorrowed");
	            boolean isBookAvailable = res.getBoolean("isBookAvialable"); // Note: Check column name
	            String ISBN = res.getString("ISBN");

	            Book book = new Book(bookId, bookName, author, isBorrowed, ISBN, isBookAvailable);
	            books.add(book);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return books;
	}



    
		
	

	@Override
	public Book getBook(String bookName) {
		Book book = null;
        try {
            stmt = con.prepareStatement(SELECT_QUERY);
            stmt.setString(1, bookName);

            res = stmt.executeQuery();
            if (res.next()) {
            	int bookId=res.getInt("bookId");
                String bookName1= res.getString("bookName");
                String author = res.getString("author");
                boolean isBorrowed = res.getBoolean("isBorrowed");
                boolean isBookAvialable=res.getBoolean("isBookAvialable");
                String ISBN=res.getString("ISBN");

                book = new Book(bookId, bookName, author, isBorrowed,ISBN,isBookAvialable);
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
	}
	
	  public void setBookAvailability(int bookId, boolean available) {
          try {
              PreparedStatement stmt = con.prepareStatement(UPDATE_AVAILABILITY_QUERY);
              stmt.setBoolean(1, available);
              stmt.setInt(2, bookId);
              int rowsUpdated = stmt.executeUpdate();
              System.out.println("Rows updated: " + rowsUpdated);
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
	
	
	   
}
