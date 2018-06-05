package model;

import java.sql.Date;
//import java.sql.Date;
public class ReserVO {
	int rid,  gid, part;
	Date resDate;
	String userId, content;
	
	public ReserVO() {
	}
	
	public ReserVO(int gid, int part, Date resDate, String userId, String content) {
		this.gid = gid;
		this.part = part;
		this.resDate = resDate;
		this.userId = userId;
		this.content = content;
	}
//
//	public void setVO(ReserVO vo) {
//		this.rid = vo.rid;
//		this.gid = vo.gid;
//		this.part = vo.part;
//		this.resDate = vo.resDate;
//		this.userId = vo.userId;
//		this.content = content;
//
//	}
//	
	/*
	
	public UserVO getUvo() {
		DAO dao = new DAO();
		UserVO uvo = dao.findUser(userId);
		dao.close();
		return uvo;
	}

	public EngineerVO getEvo() {
		DAO dao = new DAO();
		EngineerVO evo = dao.findEngi(gid);
		dao.close();
		return evo;
	}*/

	public boolean after(Date date) {
		if(resDate.after(date))
			return true;
		return false;
	}
	
	public int getPart() {
		return part;
	}
	public void setPart(int part) {
		this.part = part;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ReserVO [rid=" + rid + ", gid=" + gid + ", part=" + part + ", resDate=" + resDate + ", userId=" + userId
				+ "]";
	}


	
	
}
