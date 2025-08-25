package service.impl;

import java.util.List;

import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService{

	public static void main(String[] args) {
		

	}
	
	private static MemberDaoImpl mdi = new MemberDaoImpl();
	
	@Override
	public boolean addMember(Member member) {
		boolean memberisuse = false;
		Member m = mdi.selectUsername(member.getUsername());
		if(m != null)
		{
			System.out.print("已有此帳號");
		}
		else
		{
			mdi.addMember(member);
			memberisuse = true;
		}
		return memberisuse;
	}
	

	@Override
	public Member login(String username, String password) {
		return mdi.selectUsernamePassword(username, password);
		
	}
	
	@Override
	public List<Member> selectAllMember() {
		
		return mdi.selectAll();
	}
	

	@Override
	public Member checkMemberNo(String member_no) {
		// TODO Auto-generated method stub
		return mdi.selectByMemberNo(member_no);
	}


	
	
	@Override
	public Member checkusername(String username) {
		return mdi.selectUsername(username);
	}
	
	@Override
	public List<Member> keyword(String keyword) {
		return mdi.keyword(keyword);
	}



	
	

	@Override
	public boolean updateMember(Member member) {
		boolean update = false;
		Member m= mdi.selectById(member.getId());
		if(m!=null)
		{
			mdi.update(member);
			update = true;
		}

		return update;
	}



	
	@Override
	public void deleteMember(Member member) {
		
			mdi.delete(member);		
	}



}
