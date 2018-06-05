package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class VisitResDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;

	public VisitResDAO() {
		// TODO Auto-generated constructor stub
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/oooo");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int totalCount() {		
		try {
			sql = "select count(*) from visitres";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int totalCount(String id) {
		try {
			sql = "select count(*) from visitres where userid=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, id);
			rs = ptmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	
	public void update(VisitResVO vo, String day) {
		try {
			sql = "update visitres set phone2=?,visit_day=?,content=? where txtid=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getPhone2());
			ptmt.setString(2, day);
			ptmt.setString(3, vo.getContent());
			ptmt.setInt(4, vo.getId());
			ptmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try {
			sql = "Delete from visitres where txtid=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VisitResVO detail(int id) {
		VisitResVO vo = new VisitResVO();
		try {
			sql = "select * from visitres where txtid=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();

			rs.next();
			vo.setId(id);
			vo.setName(rs.getString("username"));
			vo.setDay(rs.getDate("visit_day"));
			vo.setPhone(rs.getString("phone"));
			vo.setPhone2(rs.getString("phone2"));
			vo.setContent(rs.getString("content"));

			return vo;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return vo;
	}

	public ArrayList<VisitResVO> myVisitList(String id, int start, int end) {
		ArrayList<VisitResVO> list = new ArrayList();
		try {
			
//			sql = "select * from visitres where userid=?";

			sql = "select * from"
					+ "(select rownum rnum, tt.* from"
					+ "(select * from visitres where userid=? order by txtid desc)tt)"
					+ "where rnum >=? and rnum <=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, id);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				VisitResVO vo = new VisitResVO();
				vo.setId(rs.getInt("txtid"));
				vo.setUserid(rs.getString("userid"));
				vo.setName(rs.getString("username"));
				vo.setPhone(rs.getString("phone"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setDay(rs.getDate("visit_day"));
				vo.setContent(rs.getString("content"));
				
				list.add(vo);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	public ArrayList<VisitResVO> allVisitList(int start, int end) {
		ArrayList<VisitResVO> list = new ArrayList();
		try {
//			sql = "select * from visitres order by visit_day asc, userid asc";

			sql = "select * from"
					+ "(select rownum rnum, tt.* from"
					+ "(select * from visitres order by userid desc)tt)"
					+ "where rnum >=? and rnum <=?";

			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				VisitResVO vo = new VisitResVO();
				vo.setId(rs.getInt("txtid"));
				vo.setUserid(rs.getString("userid"));
				vo.setName(rs.getString("username"));
				vo.setDay(rs.getDate("visit_day"));
				vo.setContent(rs.getString("content"));
				vo.setPhone(rs.getString("phone"));
				vo.setPhone2(rs.getString("phone2"));
				
				list.add(vo);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}
	
	public int insert(VisitResVO vo, String day) {
		try {
			sql = "insert into visitres(txtid,userid,username,phone,phone2,visit_day,content) "
					+ "values(visit_Seq.nextval,?,?,?,?,?,?)";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getUserid());
			ptmt.setString(2, vo.getName());
			ptmt.setString(3, vo.getPhone());
			ptmt.setString(4, vo.getPhone2());
			ptmt.setString(5, day);
			ptmt.setString(6, vo.getContent());

			ptmt.executeUpdate();

			sql = "select max(txtid) from visitres";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();

			rs.next();

			return rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	public void close() {
		try {
			if (con != null) {
				con.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (ptmt != null) {
				ptmt.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
