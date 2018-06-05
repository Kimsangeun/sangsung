package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class about_sangsung  implements Action{

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData res = new ActionData();
		request.setAttribute("main", "about_sangsung.jsp");
		
		return res;
	}

}
