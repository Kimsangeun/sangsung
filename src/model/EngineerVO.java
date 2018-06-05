package model;
/*기사 vo*/
public class EngineerVO {
	int gid;
	String phone, picture, name;
	
	public EngineerVO() {
		super();
	}
	public EngineerVO(int gid, String phone, String picture, String name) {
		super();
		this.gid = gid;
		this.phone = phone;
		this.picture = picture;
		this.name = name;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
