package qnamodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import qna.ProdQnAList;

public class ProdDAO {

	Connection con = null;
	PreparedStatement ptmt = null;
	ResultSet rs = null;
	
	String sql = null;
	public ProdDAO() {
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
	
	public ArrayList<ProdVO> list(int start, int end){
		
		ArrayList<ProdVO> res = new ArrayList<>();
		
		try {
			
			//sql = "select * from resqna order by id desc";
			
			sql = "select * from"
					+ "(select rownum rnum, tt.* from"
					+ "(select * from prodqna where seq=0 order by id desc)tt)"
					+ "where rnum >=? and rnum <=?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);
			
			rs = ptmt.executeQuery();
		
			while(rs.next()) {
				
				ProdVO vo = new ProdVO();
				
				vo.setId(rs.getInt("id"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
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
			sql = "select count(*) from prodqna where seq=0";
		
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

	public int totalCount(String pname) {
		
		try {
			sql = "select count(*) from prodqna where seq=0 and pname like ?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, "%"+pname+"%");
			
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
				sql = "select count(*) from prodqna where seq=0 and reply>=1";
				
			}else {
				sql = "select count(*) from prodqna where seq=0 and reply=0";
				
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

	
/*	public ArrayList<Integer> selectedCount(int start, int end) {
		
		ArrayList<Integer> idarr = new ArrayList<>();
		
		try {
			
			sql = "select distinct id from prodqna order by id desc";
			
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				
			//	System.out.println("rrrr:"+id);
			
				idarr.add(id);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idarr;
	}*/
	
	public void addCount(int id) {
		
		try {
			sql="update prodqna set cnt=cnt+1 where id=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ProdVO detail(int id, int seq) {
		
		
		try {
			sql = "select * from prodqna where id=? and seq=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			ptmt.setInt(2, seq);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				ProdVO vo = new ProdVO();
				
				vo.setId(id);
				vo.setSeq(seq);
				vo.setPname(rs.getString("pname"));
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

	public int insert(ProdVO vo) {
		
		try {
			sql = "insert into prodqna(id, seq, cnt, pname, reg_date, title, content, upfile, pw, reply)"
				+ " values(prodqna_Seq.nextval,0 ,0 , ?, sysdate, ?, ?, ?, ?,0)";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, vo.getPname());
			ptmt.setString(2, vo.getTitle());
			ptmt.setString(3, vo.getContent());
			ptmt.setString(4, vo.getUpfile());
			ptmt.setString(5, vo.getPw());
			
			ptmt.executeUpdate();
			
			sql="select max(id) from prodqna";
			
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
	
	public boolean check(int id, String pw) {
		
		try {
			sql="select * from prodqna where id=? and seq=0 and pw=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			ptmt.setString(2, pw);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public void modify(ProdVO vo) {
		
		try {
			sql = "update prodqna set title=?, content=?, upfile=?, pname=? where id=? and seq=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, vo.getTitle());
			ptmt.setString(2, vo.getContent());
			ptmt.setString(3, vo.getUpfile());
			ptmt.setString(4, vo.getPname());
			ptmt.setInt(5, vo.getId());
			ptmt.setInt(6, vo.getSeq());
			
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void delete(int id, int seq) {
		
		try {
			if(seq==0) {
				
				sql = "delete from prodqna where id=?";
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, id);

			}else {
				sql = "update prodqna set reply=reply-1 where id=? and seq=0";
				
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, id);
				
				ptmt.executeQuery();
				
				sql = "delete from prodqna where id=? and seq=?";
			
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
	
	public ArrayList<ProdVO> replyList(int id){
		
		ArrayList<ProdVO> res = new ArrayList<>();
		
		try {
			sql="select * from prodqna where id=? and seq>0 order by seq desc";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				ProdVO vo = new ProdVO();
				
				vo.setId(id);
				vo.setSeq(rs.getInt("seq"));
				vo.setPname(rs.getString("pname"));
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
	
	public int replyCount(int id) {
		
		try {
			sql = "select count(*) from prodqna where id=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, id);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				//System.out.println("rs.getInt(1):"+rs.getInt(1));
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public void replyInsert(ProdVO vo) {
		
		ProdVO ori = detail(vo.id, vo.seq);
	
		//System.out.println("vo.id :"+vo.id);
		try {
			sql = "update prodqna set seq=seq+1 where id=? and seq>?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.getId());
			ptmt.setInt(2, ori.getSeq());
			
			ptmt.executeUpdate();
			
			
			sql = "update prodqna set reply=reply+1 where id=? and seq=0";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.getId());
			
			ptmt.executeUpdate();
			
			
			sql = "insert into prodqna(id, seq, pname, reg_date, content, upfile) "
				+ "values(?, ?, ?, sysdate, ?, ?)";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.getId());
			ptmt.setInt(2, ori.getSeq()+1);
			ptmt.setString(3, vo.getPname());
			ptmt.setString(4, vo.getContent());
			ptmt.setString(5, vo.getUpfile());
			
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void fileDelete(int id, int seq) {
		
		try {
			sql="update prodqna set upfile=null where id=? and seq=?";
		
			ptmt = con.prepareStatement(sql);
		
			ptmt.setInt(1, id);
			ptmt.setInt(2, seq);
			
			ptmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ProdVO> searchList(int start, int end, String pname){
		
		ArrayList<ProdVO> res = new ArrayList<>();
		
		try {
			sql = "select * from"
					+ "(select rownum rnum, tt.* from"
					+ "(select * from prodqna where pname like ? and seq=0 order by id desc)tt)"
					+ "where rnum >=? and rnum <=?";

			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, "%"+pname+"%");
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				ProdVO vo = new ProdVO();
				
				vo.setId(rs.getInt("id"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
				vo.setReg_date(rs.getDate("reg_date"));
				
				res.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

/*	public ArrayList<ProdVO> selectedList(int start, int end,int page, ArrayList<Integer> idarr ){
		
		ArrayList<ProdVO> res = new ArrayList<>();
		if(idarr.size()-(page-1)*10<10) {
			end = idarr.size()-(page-1)*10;
		}
		for(int i=start-1; i <end ;i++ ) {
			
		//for (Integer id : idarr) {
		//System.out.println("isid : "+id);
			try {
		
				sql = "select * from prodqna where id=? and seq=0";
				
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, idarr.get(i));
			
				rs = ptmt.executeQuery();
				
				if(rs.next()) {
					ProdVO vo = new ProdVO();
					
					vo.setId(rs.getInt("id"));
					vo.setCnt(rs.getInt("cnt"));
					vo.setTitle(rs.getString("title"));
					vo.setPname(rs.getString("pname"));
					vo.setReg_date(rs.getDate("reg_date"));
					
					res.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return res;
	}
	*/
	
	public ArrayList<ProdVO> selectList(int start, int end, int reply){
		ArrayList<ProdVO> res = new ArrayList<>();
		
		try {
			if(reply==1) {
				sql = "select * from "
						+ "(select rownum rnum, tt.* from "
						+ "(select * from prodqna where reply>=? order by id desc )tt)"
						+ "where rnum>=? and rnum<=?";
					
			}else{
				sql = "select * from "
						+ "(select rownum rnum, tt.* from "
						+ "(select * from prodqna where reply=? order by id desc )tt)"
						+ "where rnum>=? and rnum<=?";
				
			}
			
			ptmt = con.prepareStatement(sql);
			

			ptmt.setInt(1, reply);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				ProdVO vo = new ProdVO();
				
				vo.setId(rs.getInt("id"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setTitle(rs.getString("title"));
				vo.setPname(rs.getString("pname"));
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
