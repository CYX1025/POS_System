package dao;

import java.util.List;

import model.Member;

public interface MemberDao {
	
	//create
	void addMember(Member member);
	
	//read
	Member selectUsernamePassword(String username,String password);
	Member selectUsername(String username);
	Member selectById(int id);
	Member selectByMemberNo(String member_no);
	List<Member> selectAll();
	List<Member> keyword(String keyword);
	
	
	//update
	void update(Member member);
	
	//delete
	void delete(Member member);
	

}
