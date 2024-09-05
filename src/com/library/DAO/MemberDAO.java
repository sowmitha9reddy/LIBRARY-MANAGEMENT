package com.library.DAO;

import java.util.ArrayList;

import com.bridge.model.Member;

public interface MemberDAO {
	
	    int addMember(Member member);
	    Member getMember(int memberId);
        void updateMember(Member member);
        int deleteMember(int memberId);
        ArrayList<Member> getAllMembers();
	

}
