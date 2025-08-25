package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDao;
import model.Member;
import util.DbConnection;
import util.auto_numbering;

public class MemberDaoImpl implements MemberDao{

	public static void main(String[] args) {
		
		//System.out.println(new MemberDaoImpl().selectByMemberNo("M0001"));
		
	}
	
	private static Connection conn = DbConnection.getDb();
	
	@Override
	public void addMember(Member member) {
		String sql = "insert into member(member_no, name, username, password, email, phone, address) "
				+ "values(?,?, ?, ?, ?, ?, ?)";
		try {
			String memberno = auto_numbering.MemberNo();
			member.setMember_no(memberno);
			PreparedStatement ps  = conn.prepareStatement(sql);
			ps.setString(1, member.getMember_no());
			ps.setString(2, member.getName());
			ps.setString(3, member.getUsername());
			ps.setString(4, member.getPassword());
			ps.setString(5, member.getEmail());
			ps.setString(6, member.getPhone());
			ps.setString(7, member.getAddress());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
	}

	@Override
	public Member selectUsernamePassword(String username, String password) {
		String sql = "select * from member where username = ? and password = ?";
		Member member = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				member=new Member();
				member.setId(rs.getInt("id"));
				member.setMember_no(rs.getString("member_no"));
				member.setName(rs.getString("name"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("Password"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				System.out.println("會員帳號密碼正確");
			}
			else
			{
				System.out.println("會員帳號密碼錯誤");
			}
			
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return member;
	}
	
	@Override
	public Member selectUsername(String username) {
		String sql = "select * from member where username = ?";
		Member member = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				member = new Member();
				member.setId(rs.getInt("id"));
				member.setMember_no(rs.getString("member_no"));
				member.setName(rs.getString("name"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("Password"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				System.out.println("查詢到此帳號");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return member;
	}



	@Override
	public Member selectById(int id) {
		String sql = "select * from member where id = ?";
		Member member = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				member=new Member();
				member.setId(rs.getInt("id"));
				member.setMember_no(rs.getString("member_no"));
				member.setName(rs.getString("name"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("Password"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				System.out.println("此ID已存在");
			}
			else
			{
				System.out.println("查無此ID");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	@Override
	public Member selectByMemberNo(String member_no) {
		String sql = "select * from member where member_no = ?";
		Member member = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, member_no);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				member=new Member();
				member.setId(rs.getInt("id"));
				member.setMember_no(rs.getString("member_no"));
				member.setName(rs.getString("name"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("Password"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				System.out.println("此會員已存在");
			}
			else
			{
				System.out.println("查無此會員");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	
	
	@Override
	public List<Member> selectAll() {
		String sql = "select * from member ";
		List<Member> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setMember_no(rs.getString("member_no"));
				m.setUsername(rs.getString("username"));
				m.setPassword(rs.getString("password"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	@Override
	public List<Member> keyword(String keyword) {
		String sql = "select * from member where member_no like ?  or name like ? or username like ? or "
					+ " password like ? or email like ? or phone like ? or address like ?";
		List<Member> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			String searchKeyword = "%"+keyword+"%";
			ps.setString(1, searchKeyword);
			ps.setString(2, searchKeyword);
			ps.setString(3, searchKeyword);
			ps.setString(4, searchKeyword);
			ps.setString(5, searchKeyword);
			ps.setString(6, searchKeyword);
			ps.setString(7, searchKeyword);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Member m = new Member();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setMember_no(rs.getString("member_no"));
				m.setUsername(rs.getString("username"));
				m.setPassword(rs.getString("password"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}



	@Override
	public void update(Member member) {
		String sql = "update member set name = ?, username = ?, password = ?, email = ?, phone = ?, address = ? where member_no = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getUsername());
			ps.setString(3, member.getPassword());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setString(6, member.getAddress());
			ps.setString(7, member.getMember_no());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Member member) {
		String sql = "delete from member where member_no = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, member.getMember_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

}	
