package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.Intro_noticeDAO;
import model.Action;
import model.ActionData;

public class Intro_notice_ModifyForm implements Action{

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		
		Intro_noticeDAO dao = new Intro_noticeDAO(); 
		request.setAttribute("data", dao.detail(Integer.parseInt(request.getParameter("id"))));
		dao.close();
		

		request.setAttribute("main", "intro_notice_modifyForm.jsp");
		ActionData data = new ActionData();

		return data;
	}
	

}
