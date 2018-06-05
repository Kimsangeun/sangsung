package qnamodel;

import java.util.Date;

public class ResVO {


	Date reg_date;
	String title, pname, content, upfile, user_id; 
	int id, seq, count, reply;
	

	
	public int getCount() {
		ResDAO dao = new ResDAO();
		
		count =dao.resReplyCount(id);
		dao.close();
		
		System.out.println("count : "+count);
		return count;
	}

	
	public int getReply() {
		return reply;
	}


	public void setReply(int reply) {
		this.reply = reply;
	}


	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUpfile() {
		if(upfile==null||upfile.equals(""))
			upfile="";
		return upfile;
	}
	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	

}
