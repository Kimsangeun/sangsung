package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/oooo");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void change(String id,String flag) {
		try {
			sql="update uuser set flag=? where userid=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, flag);
			ptmt.setString(2, id);
			ptmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public int totsize(String flag) {
		try {
			sql="select count(*) from uuser where flag=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, flag);
			rs = ptmt.executeQuery();
			
			rs.next();
			
			return rs.getInt(1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<UserVO> choicedata(String flag,int start,int end){
		
		List<UserVO> res = new ArrayList<>();
		try {
			sql = "select * from (select ROWNUM rnum, tt.* from (select * from uuser where userid!='admin' and flag = ?)tt) where rnum>=? and rnum<=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, flag);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setId(rs.getString("userid"));
				vo.setName(rs.getString("name"));
				vo.setPw(rs.getString("pw"));
				vo.setAddress(rs.getString("address"));
				vo.setFlag(rs.getString("flag"));
				res.add(vo);
			}
			
			return res;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public VisitResVO getNamePhone(String id) {
		VisitResVO vo = new VisitResVO();
		try {
			sql="select name,phone from uuser where userid=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, id);
			rs = ptmt.executeQuery();
			
			rs.next();
			
			vo.setPhone(rs.getString(2));
			vo.setName(rs.getString(1));
			return vo;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	public UserVO login(UserVO vo) {
		UserVO user;
		
		try {
			sql = "select * from uuser where userid=? and pw=? and flag=0";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getId());
			ptmt.setString(2, vo.getPw());
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				user = new UserVO();
				user.setId(vo.getId());
				user.setPw(vo.getPw());
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setPoint(rs.getInt("point"));
				return user;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void insert(UserVO vo) {
		try {
			sql = "insert into uuser(userid,name,pw,address,point,phone,flag) values(?,?,?,?,0,?,0)";
			ptmt =con.prepareStatement(sql);
			ptmt.setString(1, vo.getId());
			ptmt.setString(2, vo.getName());
			ptmt.setString(3, vo.getPw());
			ptmt.setString(4, vo.getAddress());
			ptmt.setString(5, vo.getPhone());
			
			ptmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public boolean chk(String id) {
		try {
			sql = "select * from uuser where userid=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, id);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}	
	
	public void close() {
		try {
			if(con != null) { con.close();}
			if(rs != null) { rs.close();}
			if(ptmt != null) { ptmt.close();}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
}
