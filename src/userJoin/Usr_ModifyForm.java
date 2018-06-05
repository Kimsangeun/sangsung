package userJoin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO;
import model.Action;
import model.ActionData;

public class Usr_ModifyForm implements Action{

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		
		
		HttpSession session = request.getSession(true);
		String userid = (String)session.getAttribute("id");
		
		System.out.println("유저아이디: " +userid);
		
		DAO dao = new DAO();
		request.setAttribute("data", dao.findUser(userid));
		dao.close();
		
		request.setAttribute("main", "../aa/usr_modifyForm.jsp");
		ActionData data = new ActionData();

		return data;
	}
	

}
