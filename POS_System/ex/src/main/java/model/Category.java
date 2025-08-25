package model;

public class Category {
	
	private Integer id;
	private String category_name;
	private String category_type;
	private String category_type1;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String category_name, String category_type, String category_type1) {
		super();
		this.category_name = category_name;
		this.category_type = category_type;
		this.category_type1 = category_type1;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_type() {
		return category_type;
	}
	public void setCategory_type(String category_type) {
		this.category_type = category_type;
	}
	public String getCategory_type1() {
		return category_type1;
	}
	public void setCategory_type1(String category_type1) {
		this.category_type1 = category_type1;
	}
	
	

}
