package model;

import java.io.Serializable;

public class Member implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String member_no;
	private String name;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Member(String member_no, String name, String username, String password, String email, String phone,
			String address) {
		super();
		this.member_no = member_no;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}


	public Member(String name, String username, String password, String email, String phone, String address) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	

}
