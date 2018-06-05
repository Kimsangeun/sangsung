package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Action;
import model.ActionData;
import model.DAO;

public class Usr_DeleteReg implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		String userid = (String)session.getAttribute("id");
		DAO dao = new DAO();
		
		dao.delete(userid);
		String msg = "탈퇴 완료.";
		String url = "../center/Main";
		dao.close();
		session.removeAttribute("id"); 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "alter.jsp");
		return new ActionData();
	}

}
