package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Action;
import model.ActionData;

public class Logout implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		HttpSession session = request.getSession(true);

		session.removeAttribute("id"); 
		
		data.setPath("../center/Main");
		data.setRedirect(true);
		
		return data;
	}
}
