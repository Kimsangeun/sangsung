package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class Login implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		ActionData data = new ActionData();
		request.setAttribute("main", "../aa/login.jsp");
		return data;
	}
}
