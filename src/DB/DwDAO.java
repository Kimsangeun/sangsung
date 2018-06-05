package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.net.aso.e;

public class DwDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public DwDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/oooo");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int tot(String title,String content,int cho) {
		int res=0;
		try {
			if(cho==3) {
				sql = "select count(*) from downtable where title like ? or CONTENT like ?";
			}else {
				sql = "select count(*) from downtable where title like ? and CONTENT like ?";
			}
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, title);
			ptmt.setString(2, content);
			rs = ptmt.executeQuery();
			
			rs.next();
			
			res = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	
	public int tot(String rang,String cate) {
		int res=0;
		try {
			sql = "select count(*) from downtable where rang=? and cate=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, rang);
			ptmt.setString(2, cate);
			rs = ptmt.executeQuery();
			
			rs.next();
			
			res = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	
	public void update(DwVO vo) {
		try {
			sql = "update downtable set title=?,content=?,photo_file=?,driver_file=? where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getTitle());
			ptmt.setString(2, vo.getContent());
			ptmt.setString(3, vo.getPhoto());
			ptmt.setString(4, vo.getDriver());
			ptmt.setInt(5, vo.id);
			
			ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void defile(int textid) {
		try {
			sql = "update downtable set driver_file=null where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, textid);
			ptmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void dephoto(int textid) {
		try {
			sql = "update downtable set photo_file=null where id = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, textid);
			ptmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void delete(int textid) {
		try {
			sql= "Delete from downtable where id=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, textid);
			ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public DwVO detail(int textid) {
		DwVO vo = new DwVO();
		try {
			sql = "update downtable set cnt=cnt+1 where id=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, textid);
			ptmt.executeUpdate();
			
			sql = "select * from downtable where id=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, textid);
			rs = ptmt.executeQuery();

			if(rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setCate(rs.getString("cate"));
				vo.setRang(rs.getString("rang"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setTime(rs.getDate("load_time"));
				vo.setPhoto(rs.getString("photo_file"));
				vo.setDriver(rs.getString("driver_file"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public int insert(DwVO vo) {
		try {
			sql = "insert into downtable(id,cnt,title,photo_file,driver_file,load_time,content,rang,cate) values(down_seq.nextval,"
					+ "-1,?,?,?,sysdate,?,?,?)";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, vo.getTitle());
			ptmt.setString(2, vo.getPhoto());
			ptmt.setString(3, vo.getDriver());
			ptmt.setString(4, vo.getContent());
			ptmt.setString(5, vo.getRang());
			ptmt.setString(6, vo.getCate());
			
			ptmt.executeUpdate();
			
			sql = "select max(id) from downtable";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			rs.next();
			
			return rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 1;
	}
	
	public Map<String, List<String>> categori(){
		Map<String, List<String>> mm = new HashMap<>();
		
		try {
			sql = "select * from categori";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				String key =rs.getString("rang");
				if(mm.containsKey(key)) {
					(mm.get(key)).add(rs.getString("cate"));
				}else {
					List<String> ll = new ArrayList<>();
					ll.add(rs.getString("cate"));
					mm.put(key, ll);
				}
			}	
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return mm;
	}
	
	public List<DwVO> favlist(String rang,String cate){
		List<DwVO> res = new ArrayList<>();
		try {
			if(rang!=null && cate!=null) {
				sql = "select * from (select ROWNUM rnum,tt.* from (select * from downtable where rang=? and cate=? order by cnt desc)tt)where rnum<=3";
				ptmt = con.prepareStatement(sql);
				ptmt.setString(1, rang);
				ptmt.setString(2, cate);
			}
			else {
				sql = "select * from (select ROWNUM rnum,tt.* from (select * from downtable order by cnt desc)tt)where rnum<=3";
				ptmt = con.prepareStatement(sql);
			}
			rs = ptmt.executeQuery();
			while(rs.next()) {
				DwVO vo = new DwVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setPhoto(rs.getString("photo_file").split(",")[0]);
				res.add(vo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<DwVO> schfav(String title,String content,int cho){
		List<DwVO> res = new ArrayList<>();
		
		try {
			if(cho==3) {
				sql = "select * from (select ROWNUM rnum,tt.* from (select * from downtable where title like ? or CONTENT like ? order by cnt desc)tt)where rnum<=3";
			}else {
				sql = "select * from (select ROWNUM rnum,tt.* from (select * from downtable where title like ? and CONTENT like ? order by cnt desc)tt)where rnum<=3";
			}
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, title);
			ptmt.setString(2, content);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				DwVO vo = new DwVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setPhoto(rs.getString("photo_file").split(",")[0]);
				res.add(vo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<DwVO> schdata(String title,String content,int cho,int starttxt,int endtxt){
		
		List<DwVO> res = new ArrayList<>();
		try {
			if(cho==3) {
				sql = "select * from(select tt.*,ROWNUM rnum from (select * from downtable where title like ? or CONTENT like ? order by id) tt) "
						+ "where rnum>=? and rnum<=?";
			}else {
				sql = "select * from(select tt.*,ROWNUM rnum from (select * from downtable where title like ? and CONTENT like ? order by id) tt) "
						+ "where rnum>=? and rnum<=?";
			}
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, title);
			ptmt.setString(2, content);
			ptmt.setInt(3, starttxt);
			ptmt.setInt(4, endtxt);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				DwVO vo = new DwVO();
				vo.setId(rs.getInt("id"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setCate(rs.getString("cate"));
				vo.setRang(rs.getString("rang"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setTime(rs.getDate("load_time"));
				vo.setPhoto(rs.getString("photo_file"));
				vo.setDriver(rs.getString("driver_file"));
				res.add(vo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<DwVO> list(String rang,String cate,int starttxt,int endtxt){
		List<DwVO> res = new ArrayList<>();
		
		try {
			sql = "select * from(select ROWNUM rnum, tt.* from(select * from downtable where rang=? and cate=? order by id) tt)"
					+ " where rnum>=? and rnum<=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, rang);
			ptmt.setString(2, cate);
			ptmt.setInt(3, starttxt);
			ptmt.setInt(4, endtxt);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				DwVO vo = new DwVO();
				vo.setId(rs.getInt("id"));
				vo.setCnt(rs.getInt("cnt"));
				vo.setCate(rs.getString("cate"));
				vo.setRang(rs.getString("rang"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setTime(rs.getDate("load_time"));
				vo.setPhoto(rs.getString("photo_file"));
				vo.setDriver(rs.getString("driver_file"));
				res.add(vo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return res;
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
