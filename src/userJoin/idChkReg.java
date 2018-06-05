package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.UserDAO;
import model.Action;
import model.ActionData;

public class idChkReg implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		UserDAO dao = new UserDAO();
		String id = request.getParameter("id");
		System.out.println(id);
		boolean chk = dao.chk(id);
		dao.close();
		
		request.setAttribute("flag", chk);
		
		request.setAttribute("id", id);
		request.setAttribute("main","../aa/join.jsp" );
		return data;
	}
}
