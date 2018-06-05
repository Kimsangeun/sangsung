package qnamodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.UserVO;



public class ResDAO {

	Connection con = null;
	PreparedStatement ptmt = null;
	ResultSet rs = null;
	
	String sql = null;
	public ResDAO() {
		super();
		Context init;
		
		try {
			
			init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/oooo");
		
			con = ds.getConnection();
			System.out.println("con:"+con); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ResVO> resList(int start, int end){
		
		ArrayList<ResVO> res = new ArrayList<>();
		
		try {
			
			//sql = "select * from resqna order by id desc";
			
			sql = "select * from"
					+ "(select rownum rnum, tt.* from"
					+ "(select * from resqna where seq=0 order by id desc)tt)"
					+ "where rnum >=? and rnum <=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);
			
			rs = ptmt.executeQuery();
		
			while(rs.next()) {
				
				ResVO vo = new ResVO();
				
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setReg_date(rs.getDate("reg_date"));
				
				res.add(vo);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;
	}
	
	public int totalCount() {
		
		try {
			sql = "select count(*) from resqna where seq=0";
		
			ptmt = con.prepareStatement(sql);
			
			rs = ptmt.executeQuery();
			
			rs.next();
			
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int totalCount(int reply) {
		
		try {
			if(reply==1) {
				sql = "select count(*) from resqna where seq=0 and reply>=1";
				
			}else {
				sql = "select count(*) from resqna where seq=0 and reply=0";
				
			}
			
			ptmt = con.prepareStatement(sql);
			
			
			rs = ptmt.executeQuery();
			
			rs.next();
			
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public ResVO resDetail(int id, int seq) {
		
		
		try {
			sql = "select * from resqna where id=? and seq=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			ptmt.setInt(2, seq);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				ResVO vo = new ResVO();
				
				vo.setId(id);
				vo.setSeq(seq);
				vo.setPname(rs.getString("pname"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setUpfile(rs.getString("upfile"));
				
				return vo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public int resInsert(ResVO vo) {
		
		try {
			sql = "insert into resqna(id, seq, pname, reg_date, title, content,upfile, user_id, reply)"
				+ " values(resqna_Seq.nextval,0 , ?, sysdate, ?, ?,null, ?,0)";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, vo.getPname());
			ptmt.setString(2, vo.getTitle());
			ptmt.setString(3, vo.getContent());
			ptmt.setString(4, vo.getUser_id());
			
			ptmt.executeUpdate();
			
			sql="select max(id) from resqna";
			
			ptmt = con.prepareStatement(sql);
			
			rs = ptmt.executeQuery();
			
			rs.next();
			
			//System.out.println(rs.getInt(1));
			
			return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void resModify(ResVO vo) {
		
		try {
			sql = "update resqna set title=?, content=?, upfile=? where id=? and seq=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, vo.getTitle());
			ptmt.setString(2, vo.getContent());
			ptmt.setString(3, vo.getUpfile());
			ptmt.setInt(4, vo.getId());
			ptmt.setInt(5, vo.getSeq());
			
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void resDelete(int id, int seq) {
		
		try {
			if(seq==0) {
				
				sql = "delete from resqna where id=?";
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, id);

			}else {
				sql = "update resqna set reply=reply-1 where id=? and seq=0";
				
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, id);
				
				ptmt.executeQuery();
				
				sql = "delete from resqna where id=? and seq=?";
			
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, id);
				ptmt.setInt(2, seq);
			}
			ptmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ResVO> resReplyList(int id){
		
		ArrayList<ResVO> res = new ArrayList<>();
		
		try {
			sql="select * from resqna where id=? and seq>0 order by seq desc";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				ResVO vo = new ResVO();
				
				vo.setId(id);
				vo.setSeq(rs.getInt("seq"));
				vo.setPname(rs.getString("pname"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setContent(rs.getString("content"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setUpfile(rs.getString("upfile"));
				
				res.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int resReplyCount(int id) {
		
		try {
			sql = "select count(*) from resqna where id=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public void resReplyInsert(ResVO vo) {
		
		ResVO ori = resDetail(vo.id, vo.seq);
	
		System.out.println("vo.id :"+vo.id);
		try {
			sql = "update resqna set seq=seq+1 where id=? and seq>?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.getId());
			ptmt.setInt(2, ori.getSeq());
			
			ptmt.executeUpdate();
			
			sql = "update resqna set reply=reply+1 where id=? and seq=0";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.getId());
			
			ptmt.executeUpdate();
			
			sql = "insert into resqna(id, seq, pname, reg_date, title, content, upfile, user_id) "
				+ "values(?, ?, ?, sysdate, null, ?, ?, ?)";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.getId());
			ptmt.setInt(2, ori.getSeq()+1);
			ptmt.setString(3, vo.getPname());
			ptmt.setString(4, vo.getContent());
			ptmt.setString(5, vo.getUpfile());
			ptmt.setString(6, vo.getUser_id());
			
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void resFileDelete(int id, int seq) {
		
		try {
			sql="update resqna set upfile=null where id=? and seq=?";
		
			ptmt = con.prepareStatement(sql);
		
			ptmt.setInt(1, id);
			ptmt.setInt(2, seq);
			
			ptmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public UserVO search(String user_id) {
		
		UserVO vo = new UserVO();
		
		try {
			sql = "select * from uuser where userid=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, user_id);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				
				vo.setUserId(user_id);
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setPhone(rs.getString("phone"));
				vo.setPoint(rs.getInt("point"));
				
				return vo;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public ArrayList<ResVO> selectList(int start, int end, int reply){
		ArrayList<ResVO> res = new ArrayList<>();
		
		try {
			if(reply==1) {
				sql = "select * from "
						+ "(select rownum rnum, tt.* from "
						+ "(select * from resqna where reply>=? order by id desc )tt)"
						+ "where rnum>=? and rnum<=?";
					
			}else{
				sql = "select * from "
						+ "(select rownum rnum, tt.* from "
						+ "(select * from resqna where reply=? order by id desc )tt)"
						+ "where rnum>=? and rnum<=?";
				
			}
			
			ptmt = con.prepareStatement(sql);
			

			ptmt.setInt(1, reply);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				ResVO vo = new ResVO();
				
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setReg_date(rs.getDate("reg_date"));
					
				res.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void close() {
		
		if(con!=null)try {con.close();}catch(Exception e) {}
		if(rs!=null)try {rs.close();}catch(Exception e) {}
		if(ptmt!=null)try {ptmt.close();}catch(Exception e) {}
		
	}
		

}
