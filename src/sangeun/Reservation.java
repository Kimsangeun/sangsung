package sangeun;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.DAO;
import model.ReserVO;
import model.ScheduleVO;

public class Reservation implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {

		String msg = "res fail";
		String url = "Reser";
		DAO dao = new DAO();
		String userid = request.getSession().getAttribute("id")+"";
		String resdate = request.getParameter("resdate");
		String content = request.getParameter("cont");
		int plus = Integer.parseInt(request.getParameter("dplus"));
		
		System.out.println("userid:" + userid);
		System.out.println("resdate:" + resdate);
		System.out.println("yyyy:" + Integer.parseInt(resdate.substring(0, resdate.indexOf('-'))));
		System.out.println("mm:" + Integer.parseInt(resdate.substring(resdate.indexOf('-') + 1, resdate.lastIndexOf('-'))));
		System.out.println("dd:" + Integer.parseInt(resdate.substring(resdate.lastIndexOf('-') + 1)));
//		Date date = new Date(2018-1900,5-1,24);

		Date date = new Date(Integer.parseInt(resdate.substring(0, resdate.indexOf('-'))),
				Integer.parseInt(resdate.substring(resdate.indexOf('-') + 1, resdate.lastIndexOf('-'))),
				Integer.parseInt(resdate.substring(resdate.lastIndexOf('-') + 1)));
//		int part = Integer.parseInt(request.getParameter("part"));
//		int gid = Integer.parseInt(request.getParameter("gid"));
		int pg= Integer.parseInt(request.getParameter("part"));
		int part=pg%10;
		int gid= pg/10;

//		date.setDate(date.getDate()+plus);
		
		System.out.println("real date:"+date);
		
		System.out.println("ggid:"+gid);
		System.out.println("part:"+part);
		
		ReserVO rvo = new ReserVO(gid, part, date, userid, content);

		if (dao.reserInsert(rvo)) {
			msg = "res success";
			url = "ReserDetailForm?rid=" + dao.getRid();
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "../sangeun/alert.jsp");

		dao.close();
	
		return new ActionData();
	}

}
