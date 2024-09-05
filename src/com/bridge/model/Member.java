package com.bridge.model;
import java.util.ArrayList;
import java.util.List;
public class Member {
	

	    private int memberId;
	    private String name;
	    private String email;
	    private ArrayList<BorrowRecord> borrowRecords;

	    public Member(int memberId, String name, String email) {
	        this.memberId = memberId;
	        this.name = name;
	        this.email = email;
	        this.borrowRecords = new ArrayList<>();
	    }
	    

		public Member(String name, String email) {
			
			this.name = name;
			this.email = email;
		}


		public Member(String name, String email, ArrayList<BorrowRecord> borrowRecords) {
			super();
			this.name = name;
			this.email = email;
			this.borrowRecords = borrowRecords;
		}


		public int getMemberId() {
			return memberId;
		}

		public void setMemberId(int memberId) {
			this.memberId = memberId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public ArrayList<BorrowRecord> getBorrowedRecords() {
			return borrowRecords;
		}
        
		 public void borrowBook(BorrowRecord record) {
			 borrowRecords.add(record);
		    }

		    // Remove a borrowed book
		    public void returnBook(BorrowRecord record) {
		    	borrowRecords.remove(record);
		    }
		public void setBorrowRecords(ArrayList<BorrowRecord> borrowRecords) {
			this.borrowRecords = borrowRecords;
		}
		
        @Override
        public String toString() {
             
           return getMemberId()+" "+ getName()+" "+getEmail() ;
            }


		
}
