package com.library.dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bridge.model.Book;
import com.bridge.model.BorrowRecord;
import com.bridge.model.Member;
import com.library.DAO.BorrowRecordDAO;
import com.library.util.MyConnect;

public class BorrowRecordDAOImpl  implements BorrowRecordDAO{
	

	    private Connection con;
        private PreparedStatement stmt;
		private ResultSet res;
		private BorrowRecordDAO borrowRecordDAO;
		private Object bookDAO;


    

	    static final String INSERT_QUERY = "INSERT INTO `borrowrecord` (`bookId`, `memberId`) VALUES (?, ?)";
	    static final String SELECT_QUERY = "SELECT * FROM `borrowrecord` WHERE `borrowRecordId`=?";
	    static final String SELECT_QUERY_By_MEMBER_ID = "SELECT * FROM `borrowrecord` WHERE `memberId`=?";
	    
	    static final String UPDATE_QUERY = "UPDATE `borrowrecord` SET `bookId`=?, `memberId`=?, `borrowDate`=?, `returnDate`=? WHERE `borrowRecordId`=?";
        
	    static final String UPDATE_QUERY_RETURN  = "UPDATE BorrowRecord SET returnDate = CURRENT_TIMESTAMP WHERE memberId = ? AND bookId = ? AND returnDate IS NULL";
	    static final String DELETE_QUERY = "DELETE FROM `borrowrecord` WHERE `borrowRecordId`=?";
	    static final String SELECT_ALL_QUERY = "SELECT * FROM `borrowrecord`";

	    public BorrowRecordDAOImpl() {
	        MyConnect.getMyConnection();
	        con = MyConnect.connect();
	    }

		@Override
		public int addBorrowRecord(BorrowRecord borrowRecord) {
			try {
				stmt = con.prepareStatement(INSERT_QUERY);
	            stmt.setInt(1, borrowRecord.getBookId());
	            stmt.setInt(2, borrowRecord.getMemberId());
	         

	            return stmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
			
		}

		@Override
		public BorrowRecord getBorrowRecord(int borrowRecordId) {
			BorrowRecord borrowRecord = null;
	        try {
	            stmt = con.prepareStatement(SELECT_QUERY);
	            stmt.setInt(1, borrowRecordId);

	            res = stmt.executeQuery();
	            if (res.next()) {
	                int bookId = res.getInt("bookId");
	                int memberId = res.getInt("memberId");
	                LocalDate borrowDate = res.getDate("borrowDate").toLocalDate();
	                java.sql.Date returnSqlDate = res.getDate("returnDate");
		            LocalDate returnDate = (returnSqlDate != null) ? returnSqlDate.toLocalDate() : null;

	                borrowRecord = new BorrowRecord(borrowRecordId, bookId, memberId, borrowDate);
	                borrowRecord.setReturnDate( returnDate);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return borrowRecord;
		
		}
		public List<BorrowRecord> getBorrowRecordsByMemberId(int memberId) {
		    List<BorrowRecord> borrowRecords = new ArrayList<>();
		    String query = "SELECT * FROM borrowrecord WHERE memberId = ?";
		    
		    try (PreparedStatement stmt = con.prepareStatement(query)) {
		        stmt.setInt(1, memberId);
		        try (ResultSet res = stmt.executeQuery()) {
		            while (res.next()) {
		                int borrowRecordId = res.getInt("borrowRecordId");
		                int bookId = res.getInt("bookId");
		                LocalDate borrowDate = res.getDate("borrowDate").toLocalDate();
		                
		               
		                java.sql.Date returnSqlDate = res.getDate("returnDate");
		                LocalDate returnDate = (returnSqlDate != null) ? returnSqlDate.toLocalDate() : null;

		                BorrowRecord record = new BorrowRecord(borrowRecordId, bookId, memberId, borrowDate);
		                record.setReturnDate(returnDate);

		                
		                borrowRecords.add(record);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return borrowRecords;
		}

		@Override
		public void updateBorrowRecord(BorrowRecord borrowRecord) {
			 try {
		            stmt = con.prepareStatement(UPDATE_QUERY);
		            stmt.setInt(1, borrowRecord.getBookId());
		            stmt.setInt(2, borrowRecord.getMemberId());
		            stmt.setDate(3, java.sql.Date.valueOf(borrowRecord.getBorrowDate()));
		            stmt.setDate(4,java.sql.Date.valueOf( borrowRecord.getReturnDate()));
		            stmt.setInt(5, borrowRecord.getBorrowRecordId());

		            stmt.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			
		}

		@Override
		public void deleteBorrowRecord(int borrowRecordId) {
			 try {
		            stmt = con.prepareStatement(DELETE_QUERY);
		            stmt.setInt(1, borrowRecordId);
		            stmt.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		}

		@Override
		public ArrayList<BorrowRecord> getAllBorrowRecords() {
		
			ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();
	        try {
	            stmt = con.prepareStatement(SELECT_ALL_QUERY);
	            res = stmt.executeQuery();

	            while (res.next()) {
	                int borrowRecordId = res.getInt("borrowRecordId");
	                int bookId = res.getInt("bookId");
	                int memberId = res.getInt("memberId");
	                LocalDate borrowDate = res.getDate("borrowDate").toLocalDate();
	                java.sql.Date returnSqlDate = res.getDate("returnDate");
		            LocalDate returnDate = (returnSqlDate != null) ? returnSqlDate.toLocalDate() : null;
	                BorrowRecord borrowRecord = new BorrowRecord(borrowRecordId, bookId, memberId, borrowDate);
	                borrowRecord.setReturnDate( returnDate);
	                borrowRecords.add(borrowRecord);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return borrowRecords;
	    }

		@Override
		public BorrowRecord getBorrowRecordForBook(int bookId, int memberId) {
		    BorrowRecord borrowRecord = null;
		    String query = "SELECT * FROM borrowrecord WHERE bookId = ? AND memberId = ? AND returnDate IS NULL";

		    try (PreparedStatement stmt = con.prepareStatement(query)) {
		        stmt.setInt(1, bookId);
		        stmt.setInt(2, memberId);

		        try (ResultSet res = stmt.executeQuery()) {
		            if (res.next()) {
		                int borrowRecordId = res.getInt("borrowRecordId");
		                LocalDate borrowDate = res.getDate("borrowDate").toLocalDate();

		               
		                java.sql.Date ReturnDate = res.getDate("returnDate");
		                LocalDate returnDate = (ReturnDate != null) ? ReturnDate.toLocalDate() : null;

		                borrowRecord = new BorrowRecord(borrowRecordId, bookId, memberId, borrowDate);
		                borrowRecord.setReturnDate(returnDate);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return borrowRecord;
		}

		public void updateBorrowRecord(int memberId, int bookId) {
		    try {


		        PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY_RETURN);
		        stmt.setInt(1, memberId);
		        stmt.setInt(2, bookId);

		        int rowsUpdated = stmt.executeUpdate();
		        if (rowsUpdated > 0) {
		           
		        } else {
		            System.out.println("No matching borrow record found for this member and book, or the book is already returned.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		   

}

	   

	   

