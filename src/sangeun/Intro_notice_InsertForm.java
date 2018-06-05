package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class Intro_notice_InsertForm implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		
		ActionData res = new ActionData();
		
		request.setAttribute("main", "intro_notice_insertForm.jsp");
		return res;
	}

}
