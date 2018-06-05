package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;

	public DAO() {

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/oooo");

			con = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// dao �븯�굹�뿉 vo �뿬�윭媛�?
	
	// �삁�빟�젙蹂� 紐뉕컻? //�뙆�씪誘명꽣 �엳�뒗嫄� �쑀��爰쇰쭔 �뾾�뒗嫄� 紐⑤몢
	public int totalCount() {		
		try {
			sql = "select count(*) from reser";
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
			sql = "select count(*) from reser where userid=?";
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

	// rid濡� �삁�빟vo 諛쏆븘�샂
	public ReserVO findReser(int rid) {
		try {
			System.out.println("findReser吏꾩엯");
			sql = "select * from reser where rid = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, rid);

			rs = ptmt.executeQuery();
			if (rs.next()) {
				ReserVO vo = new ReserVO();
				vo.setRid(rid);
				vo.setUserId(rs.getString("userid"));
				vo.setGid(rs.getInt("gid"));
				vo.setResDate(rs.getDate("resdate"));
				vo.setPart(rs.getInt("part"));
				vo.setContent(rs.getString("content"));
				System.out.println("findReser醫낅즺");
				return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	// uid濡� �쑀��vo 諛쏆븘�샂
	public UserVO findUser(String userId) {
		try {
			System.out.println("findUser吏꾩엯");

			sql = "select * from uuser where userid = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, userId);

			rs = ptmt.executeQuery();
			if (rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserId(userId);
				vo.setPw(rs.getString("pw"));
				vo.setPoint(rs.getInt("point"));
				vo.setAddress(rs.getString("address"));
				vo.setPhone(rs.getString("phone"));
				vo.setName(rs.getString("name"));
				System.out.println("findUser醫낅즺");
				return vo;
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

		}
		return null;
	}

	// 湲곗궗 id濡� 湲곗궗vo 諛쏆븘�샂
	public EngineerVO findEngi(int gid) {
		try {
			System.out.println("findEngi吏꾩엯");

			sql = "select * from engineer where gid = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, gid);

			rs = ptmt.executeQuery();
			if (rs.next()) {
				EngineerVO vo = new EngineerVO();
				vo.setGid(gid);
				vo.setPhone(rs.getString("phone"));
				vo.setPicture(rs.getString("picture"));
				vo.setName(rs.getString("name"));
				System.out.println("reserEngi醫낅즺");
				return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return null;

	}

	// uid濡� �쑀���쓽 �삁�빟由ъ뒪�듃 諛쏆븘�샂
	public ArrayList<ReserVO> userReser(String userId,int start, int end, String mode) {
		ArrayList<ReserVO> list = new ArrayList<>();

		try {
			//System.out.println("userReser吏꾩엯");

			if(mode.equals("1")) {
				sql = "select * from"
						+ "(select rownum rnum, tt.* from"
						+ "(select * from reser where userid = ? and resdate< (select trunc(sysdate) from dual)"
						+ " order by resdate asc, part asc, gid)tt)"
						+ "where rnum >=? and rnum <=?";
			}
			else if(mode.equals("2")) {
				sql = "select * from"
						+ "(select rownum rnum, tt.* from"
						+ "(select * from reser where userid = ? and resdate= (select trunc(sysdate) from dual)"
						+ " order by resdate asc, part asc, gid)tt)"
						+ "where rnum >=? and rnum <=?";
			}else {
				sql = "select * from"
						+ "(select rownum rnum, tt.* from"
						+ "(select * from reser where userid = ? order by resdate asc, part asc, gid)tt)"
						+ "where rnum >=? and rnum <=?";
			}
			
//			sql = "select * from reser where userid = ? order by resdate asc, part asc, gid";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, userId);
			ptmt.setInt(2, start);
			ptmt.setInt(3, end);

			rs = ptmt.executeQuery();
			while (rs.next()) {
				ReserVO vo = new ReserVO();
				vo.setRid(rs.getInt("rid"));
				vo.setGid(rs.getInt("gid"));
				vo.setPart(rs.getInt("part"));
				vo.setContent(rs.getString("content"));
				vo.setUserId(userId);
				vo.setResDate(rs.getDate("resDate"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

		}

		return list;
	}

	// 紐⑤뱺 �쑀���쓽 �삁�빟由ъ뒪�듃 諛쏆븘�샂
	public ArrayList<ReserVO> allReser(int start, int end, String mode) {
		ArrayList<ReserVO> list = new ArrayList<>();

		try {
			//System.out.println("allReser吏꾩엯");

			if(mode.equals("1")) {
				sql = "select * from"
						+ "(select rownum rnum, tt.* from"
						+ "(select * from reser where resdate< (select trunc(sysdate) from dual)"
						+ " order by resdate asc, part asc, gid)tt)"
						+ "where rnum >=? and rnum <=?";
			}

				
			else if(mode.equals("2")) {
				sql = "select * from"
						+ "(select rownum rnum, tt.* from"
						+ "(select * from reser where resdate= (select trunc(sysdate) from dual)"
						+ " order by resdate asc, part asc, gid)tt)"
						+ "where rnum >=? and rnum <=?";
				
			}else {
				sql = "select * from"
						+ "(select rownum rnum, tt.* from"
						+ "(select * from reser order by resdate asc, part asc, gid)tt)"
						+ "where rnum >=? and rnum <=?";				
			}

//			sql = "select * from reser order by resdate asc, part asc, gid";
			
			ptmt = con.prepareStatement(sql);
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, start);
			ptmt.setInt(2, end);
			
			
			rs = ptmt.executeQuery();
			while (rs.next()) {
				ReserVO vo = new ReserVO();
				vo.setRid(rs.getInt("rid"));
				vo.setGid(rs.getInt("gid"));
				vo.setPart(rs.getInt("part"));
				vo.setUserId(rs.getString("userid"));
				vo.setContent(rs.getString("content"));
				vo.setResDate(rs.getDate("resDate"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

		}

		return list;
	}
	
	/* �궇吏쒕꽔怨� �삁�빟�젙蹂대━�뒪�듃 遺덈윭�삤湲� */
	public ArrayList<ScheduleVO> dayPart(java.util.Date resdate) {
		ArrayList<ScheduleVO> list = new ArrayList<>();

		try {
			
			String dd = new SimpleDateFormat("yyyy-MM-dd").format(resdate);

			
			sql="select * from schedule where resdate='"+dd+"'";
			//System.out.println(sql);
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			if(!rs.next()) {
				//System.out.println("create schedule");
				for(int i=1;i<=3;i++) {
					sql = "insert into schedule (gid, resdate, part1, part2, part3) values(?, '"+dd+"', 0, 0, 0)";
					//System.out.println(sql);
					ptmt = con.prepareStatement(sql);

					//System.out.println("gid:" + i);
					ptmt.setInt(1, i);
					//System.out.println("resdate:" + dd);

					ptmt.executeUpdate();

				}
			}
			
			//System.out.println("dayPart 吏꾩엯");
			//System.out.println("::" + resdate);
			sql = "select * from schedule where resdate = '" + dd + "' order by gid asc";
			ptmt = con.prepareStatement(sql);

			//System.out.println(sql);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				System.out.println("DAO_dayPart rs�엳�쓬");
				ScheduleVO vo = new ScheduleVO();

				vo.setGid(rs.getInt("gid"));
				vo.setResdate(rs.getDate("resdate"));
				vo.setPart1(rs.getInt("part1"));
				vo.setPart2(rs.getInt("part2"));
				vo.setPart3(rs.getInt("part3"));

				// vo.setPart(rs.getInt("part"));

				System.out.println(vo + "daypart醫낅즺");
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	// �뵒鍮꾩뿉 �삁�빟�젙蹂� 異붽�
	public boolean reserInsert(ReserVO vo) {
		try {
			/* �삁�빟�젙蹂� 異붽� */
			sql = "insert into reser (rid, userid, gid, resdate, part, content) values(reser_rid.nextval, ?, ?, ?, ?, ?)";
			System.out.println(sql);
			ptmt = con.prepareStatement(sql);

			ptmt.setString(1, vo.getUserId());
			ptmt.setInt(2, vo.getGid());
			ptmt.setDate(3, vo.getResDate());
			ptmt.setInt(4, vo.getPart());
			System.out.println("\ncontent\n"+vo.getContent());
			ptmt.setString(5, vo.getContent());

			ptmt.executeUpdate();

			/* 湲곗궗 �뒪耳�以� �닔�젙 / �븘臾닿쾬�룄 �뾾�쓣�븣 part123 0�쑝濡� 吏��젙. 珥덇린�솕< */

			sql = " select * from schedule where gid=? and resdate=? ";
			System.out.println(sql);
			ptmt = con.prepareStatement(sql);
			System.out.println("gid:" + vo.getGid());
			ptmt.setInt(1, vo.getGid());
			System.out.println("resdate:" + vo.getResDate());
			ptmt.setDate(2, vo.getResDate());

			rs = ptmt.executeQuery();
			rs.next();
			 if (rs.getInt("part" + vo.getPart()) == 1) {
				System.out.println("reserInsert : Already exist ");
				return false;
			}
			// �빐�떦 �뙆�듃�뿉 �뒪耳�以� �깮�꽦
			sql = "update schedule set part" + vo.getPart() + "=1 where gid=? and resdate=?";
			ptmt = con.prepareStatement(sql);
			System.out.println(sql);
			System.out.println("gid:" + vo.getGid());
			ptmt.setInt(1, vo.getGid());
			System.out.println("resdate:" + vo.getResDate());
			ptmt.setDate(2, vo.getResDate());

			ptmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("insert_error");
			e.printStackTrace();
		} finally {

		}
		return false;
	}

	/* 移댄뀒怨좊━ 由ъ뒪�듃 遺덈윭�삤湲� */
	public Map<String, List<String>> categori() {
		Map<String, List<String>> map = new HashMap<>();

		try {
			sql = "select * from categori";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();

			while (rs.next()) {
				String key = rs.getString("rang");
				if (map.containsKey(key)) {
					(map.get(key)).add(rs.getString("cate"));
				} else {
					List<String> ll = new ArrayList<>();
					ll.add(rs.getString("cate"));
					map.put(key, ll);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	// �삁�빟踰덊샇濡� �삁�빟 痍⑥냼
	public boolean reserDelete(int rid) { // �삁�빟 痍⑥냼 �떦�씪�씠硫� �븞�맂��
		ReserVO vo = new ReserVO();

		try {
			// �삁�빟�젙蹂댁뿉�꽌 �궘�젣�븷 湲곗궗�븘�씠�뵒, �궇吏� 媛��졇�삤湲�
			sql = "select * from reser where rid=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, rid);
			rs = ptmt.executeQuery();
			System.out.println(sql);
			if (rs.next()) { // �꽔湲�
				vo.setGid(rs.getInt("gid"));
				vo.setResDate(rs.getDate("resdate"));
				vo.setPart(rs.getInt("part"));
			}
			int part = vo.getPart();
			// 鍮� �뒪耳�以꾩뿉�꽌 �궘�젣�븯�뒗寃쎌슦?
			

			sql="select part"+part+" from schedule where gid=? and resdate=?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, vo.getGid());
			ptmt.setDate(2, vo.getResDate());
			ptmt.executeQuery();
			System.out.println(sql);
			
			if (rs.getInt(1) == 0) {
				System.out.println("鍮꾩뿀�뒗�뜲?");
				return false;
			}

			// �빐�떦 �뙆�듃�뿉 �뒪耳�以� �궘�젣 // 媛��졇�삩 湲곗궗id �궇吏쒕줈 �뒪耳�以� �궘�젣
			sql = "update schedule set part" + vo.getPart() + "=0 where gid=? and resdate=?";
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, vo.getGid());
			ptmt.setDate(2, vo.getResDate());

			ptmt.executeUpdate();
			System.out.println(sql);

			/* �삁�빟�젙蹂� �궘�젣 / �븘�씠�뵒�뿉 �빐�떦�븯�뒗 �삁�빟�젙蹂� �뾾�쓣 寃쎌슦媛� �엳�굹? */
			sql = "delete from reser where rid=?";
			ptmt = con.prepareStatement(sql);

			ptmt.setInt(1, rid);
			ptmt.executeUpdate();

			System.out.println(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return false;
	}

	// 留μ뒪 �삁�빟踰덊샇 諛쏆븘�샂
	public int getRid() {

		try {
			sql = "select max(rid) from reser";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			System.out.println("max(rid):"+rs.getInt(1));
			return rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return 0;
	}

	/*
	  try { sql=""; ptmt = con.prepareStatement(sql); ptmt.setInt(1, 1);	  ptmt.setString(1, ""); ptmt.executeQuery(); }catch (Exception e) { }finally {	  }
	 */
	public void modify(UserVO vo) {
		try {
			
			sql = "update uuser set pw=?, address=?, phone=? where userid = ?";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1,vo.getPw());
			ptmt.setString(2,vo.getAddress());
			ptmt.setString(3,vo.getPhone());
			ptmt.setString(4,vo.getUserId());
			
			ptmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void delete(String userId) {

		try {

			sql = "delete from uuser where userid = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, userId);
			ptmt.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
