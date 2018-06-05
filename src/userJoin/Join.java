package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class Join implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		//¡÷ºÆ
		request.setAttribute("flag", false);
		request.setAttribute("main", "../aa/join.jsp");
		return data;
	}
}
