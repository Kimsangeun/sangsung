package sangeun;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.DAO;
import model.ReserVO;

public class Reser implements Action {
	
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		if(request.getSession().getAttribute("id") == null) {
			System.out.println("no id");
			request.setAttribute("msg", "need login");
			request.setAttribute("url", "../userJoin/Login");
			request.setAttribute("main", "../../view/sangeun/alert.jsp");
			return new ActionData();
		}
		
		System.out.println("id : "+request.getSession().getAttribute("id"));
		
		DAO dao = new DAO();
		
		request.setAttribute("user", dao.findUser(request.getSession().getAttribute("id")+""));
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		request.setAttribute("main", "../sangeun/reserForm.jsp");
		request.setAttribute("cate", dao.categori());
		request.setAttribute("reservo", new ReserVO());

		
		dao.close();
		
		return new ActionData();
		
	}
}