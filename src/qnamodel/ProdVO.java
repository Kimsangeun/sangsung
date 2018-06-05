package qnamodel;

import java.util.Date;

public class ProdVO {
	Date reg_date;
	String title, pname, content, upfile, pw; 
	int id, seq, cnt, reply;

	public int getCount() {
		ProdDAO dao = new ProdDAO();
		
		int count =dao.replyCount(id);
		
		//System.out.println("count : "+id+":"+count);
		
		dao.close();
		return count;
		
	}
	
	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	

}
