package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class Mypage implements Action{

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main", "../aa/myPage.jsp");
		return new ActionData();
	}
	

}
