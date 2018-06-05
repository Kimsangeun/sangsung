package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.Intro_noticeDAO;
import model.Action;
import model.ActionData;

public class Intro_notice_detail implements Action{

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionData data = new ActionData();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Intro_noticeDAO dao = new Intro_noticeDAO();
		
		dao.addCount(id);
		
		request.setAttribute("data", dao.detail(id));
		dao.close();
		request.setAttribute("main", "intro_notice_detail.jsp");
		
		return data;
	}

}   
