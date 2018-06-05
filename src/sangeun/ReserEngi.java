package sangeun;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.DAO;
import model.ReserVO;
import model.ScheduleVO;

public class ReserEngi implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {

		DAO dao = new DAO();
		System.out.println("engi---\ndate");
		Date today = new Date();
	
		
		int ddd = Integer.parseInt(request.getParameter("day"));
		Date date = new Date();
		date.setDate(date.getDate()+ddd);
		System.out.println("engi day: "+date);
		String uid = request.getSession().getAttribute("id")+"";
		
		ArrayList<ScheduleVO> slist = dao.dayPart(date);

		System.out.println("slist:"+slist);
		String cont = request.getParameter("content");
		
		
		request.setAttribute("user", dao.findUser(uid));
		request.setAttribute("cate", dao.categori());
		request.setAttribute("schevo", new ScheduleVO());
		request.setAttribute("slist", slist);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		request.setAttribute("tt", date);
		String week = "일월화수목금토".charAt(date.getDay())+"";
		request.setAttribute("week", week);
		
		request.setAttribute("tday", today);
		request.setAttribute("dplus", ddd);
		request.setAttribute("cont", cont);
		
		request.setAttribute("main", "../sangeun/reserForm.jsp");

		dao.close();

		return new ActionData();
	}
//	int getYear(String date) {
//		int year = Integer.parseInt(date.substring(0, date.indexOf("-")));
//		return year;
//	}
//	int getMonth(String date) {
//		int month = Integer.parseInt(date.substring(date.indexOf("-")+1, date.lastIndexOf("-") ));
//		return month;
//	}
//	int getDate(String date) {
//		int dd = Integer.parseInt(date.substring(date.lastIndexOf("-")+1));
//		return dd;
//	}

}
