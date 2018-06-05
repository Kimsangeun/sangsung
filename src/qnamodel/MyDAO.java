package qnamodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class MyDAO {

	Connection con = null;
	PreparedStatement ptmt = null;
	ResultSet rs = null;
	
	String sql = null;
	public MyDAO() {
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

	
	public ArrayList<ResVO> list(int start, int end, String user_id){
		
		ArrayList<ResVO> res = new ArrayList<>();
		
		try {
			sql = "select * from"
					+ "(select rownum rnum, tt.* from"
					+ "(select * from resqna where user_id=? order by id desc)tt)"
					+ "where rnum >=? and rnum <=?";

			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, user_id);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				
				ResVO vo = new ResVO();
				
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
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
	
	public int totalCount(String user_id) {
		
		try {
			sql = "select count(*) from resqna where user_id=?";
		
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, user_id);
			
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
		
		if(rs!=null)try {rs.close();} catch(Exception e) {}
		if(con!=null)try {con.close();} catch(Exception e) {}
		if(ptmt!=null)try {ptmt.close();} catch(Exception e) {}
		
	}
	
}
