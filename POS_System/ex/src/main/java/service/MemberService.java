package service;

import java.util.List;

import model.Member;

public interface MemberService {
	//create
		boolean addMember(Member meber); //新增member+判斷
		
		//read
		Member login(String username,String password);//登入
		Member checkusername(String username); //註冊確認
		List<Member> selectAllMember();
		Member checkMemberNo(String member_no);
		List<Member> keyword(String keyword);
		
		//update
		boolean updateMember(Member member);
		
		//delete
		void deleteMember(Member member);
		//boolean deleteAllMember(Member member);

}
