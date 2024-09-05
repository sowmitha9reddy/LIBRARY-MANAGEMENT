package com.library.DAO;

import java.util.ArrayList;
import java.util.List;

import com.bridge.model.BorrowRecord;

public interface BorrowRecordDAO {
	
	    int addBorrowRecord(BorrowRecord borrowRecord);
	    BorrowRecord getBorrowRecord(int borrowRecordId);
	    void updateBorrowRecord(BorrowRecord borrowRecord);
	     void updateBorrowRecord(int memberId, int bookId);
	    void deleteBorrowRecord(int borrowRecordId);
	    ArrayList<BorrowRecord> getAllBorrowRecords();
	    BorrowRecord getBorrowRecordForBook(int bookId, int memberId);
	    List<BorrowRecord> getBorrowRecordsByMemberId(int memberId);
	    

}
