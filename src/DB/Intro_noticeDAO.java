package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Intro_noticeDAO {

	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public Intro_noticeDAO(){
		try {
			Context init = new InitialContext();
			System.out.println(init);
			DataSource ds = (DataSource)init.lookup("java:comp/env/oooo");
			con = ds.getConnection();
			
		} catch (Exception e) {
			
			try {
				
				String driver="oracle.jdbc.driver.OracleDriver";
				 Class.forName(driver);
				 String url = "jdbc:oracle:thin:@localhost:1521:xe";
				 String id="hr";
				 String pw ="hr";
				
				 con = DriverManager.getConnection(url,id,pw);
			}catch(Exception e1) {
			}
		}
	}
	
	public ArrayList<Intro_noticeVO> list(int start, int end){
		
		ArrayList<Intro_noticeVO> res = new ArrayList<>();
		
		try {
			
			sql = 	"select * from (select rownum rnum, tt.* from (select * from notice order by id DESC) tt) where rnum >= ? and rnum <= ?";
			/*sql = "select *from notice order by id";*/
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				Intro_noticeVO vo = new Intro_noticeVO();
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getInt("id"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setReg_Date(rs.getTimestamp("reg_date"));
				vo.setContent(rs.getString("content"));
				res.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return res;
	}
	
	
	public int insert(Intro_noticeVO vo) {
		try {
			
			sql = "insert into notice(id, cnt, title, content, reg_date) values (notice_seq.nextval,-1,?,?,sysdate)";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1,vo.getTitle());
			ptmt.setString(2,vo.getContent());
			ptmt.executeUpdate();
			
			sql = "select max(id) from notice";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}
	
	
	public void modify(Intro_noticeVO vo) {
		try {
			
			sql = "update notice set title=?, content=? where id = ?";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1,vo.getTitle());
			ptmt.setString(2,vo.getContent());
			ptmt.setInt(3, vo.getId());
			ptmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addCount(int id) {
		sql = "update notice set cnt = cnt+1 where id = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Intro_noticeVO detail(int id) {
		

		try {
			
			sql = "select * from notice where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			if(rs.next()) {
				Intro_noticeVO vo = new Intro_noticeVO();
				
				vo.setId(id);
				vo.setCnt(rs.getInt("cnt"));
				vo.setReg_Date(rs.getTimestamp("reg_date"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				
				System.out.println("�빞 �븞李랁엳吏� �븡�깘");
			
				
				return vo;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("�뀋�뀋吏꾩쭨�븞李랁삍�꽕?");
			return null;
		
	}

	
	public void delete(int id) {

		try {

			sql = "delete from notice where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public  int totalCount() {
		
		try {

			sql = "select count(*) from notice";
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
	
	
	public void close() {

		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}

		if (ptmt != null)
			try {
				ptmt.close();
			} catch (SQLException e) {
			}

		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
			}

	}

}
