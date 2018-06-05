package model;


public class UserVO {
	int  point;
	String name, pw, address, phone, userId;

	public UserVO() { 
		// TODO Auto-generated constructor stub
	}

	public UserVO(String userId, int point, String name, String pw, String address, String phone) {
		this.userId = userId;
		this.point = point;
		this.name = name;
		this.pw = pw;
		this.address = address;
		this.phone = phone;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
