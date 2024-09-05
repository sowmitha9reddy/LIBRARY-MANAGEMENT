package com.library.dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bridge.model.Member;
import com.library.DAO.MemberDAO;
import com.library.util.MyConnect;

public class MemberDAOImpl  implements MemberDAO{
	

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet res;

	public MemberDAOImpl() {
		MyConnect.getMyConnection();
		con=MyConnect.connect();
	}
	
	static final String INSERT_QUERY = "INSERT INTO `member` (`name`, `email`) VALUES(?, ?)";
    static final String SELECT_QUERY = "SELECT * FROM `member` WHERE `memberId`=?";
    static final String UPDATE_QUERY = "UPDATE `member` SET `name`=?, `email`=? WHERE `memberId`=?";
    static final String DELETE_QUERY = "DELETE FROM `member` WHERE `memberId`=?";
    static final String SELECT_ALL_QUERY = "SELECT * FROM `member`";
	
    
    @Override
	public int addMember(Member member) {
		try {
            stmt = con.prepareStatement(INSERT_QUERY);
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		
	}

	@Override
	public Member getMember(int memberId) {
		  Member member = null;
	        try {
	            stmt = con.prepareStatement(SELECT_QUERY);
	            stmt.setInt(1, memberId);

	            res = stmt.executeQuery();
	            if (res.next()) {
	                String name = res.getString("name");
	                String email = res.getString("email");

	                member = new Member(memberId, name, email);
	              
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return member;
	}
	        
		

	@Override
	public void updateMember(Member member) {
		 try {
	            stmt = con.prepareStatement(UPDATE_QUERY);
	            stmt.setString(1, member.getName());
	            stmt.setString(2, member.getEmail());
	            stmt.setInt(3, member.getMemberId());

	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public int deleteMember(int memberId) {
		try {
            stmt = con.prepareStatement(DELETE_QUERY);
            stmt.setInt(1, memberId);
           return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
	}

	@Override
	public ArrayList<Member> getAllMembers() {
		ArrayList<Member> members = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            res = st.executeQuery(SELECT_ALL_QUERY);

            while (res.next()) {
                int memberId = res.getInt("memberId");
                String name = res.getString("name");
                String email = res.getString("email");

                Member member = new Member(memberId, name, email);
                // You may want to populate the borrowRecords list here if needed
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
		
	}

	

	

	    

	    

	   
	   

	   

	
	    


