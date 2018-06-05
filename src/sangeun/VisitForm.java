package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Action;
import model.ActionData;

public class VisitForm implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		HttpSession session = request.getSession(true);
		
		String id = (String)session.getAttribute("id");
		
		String msg ="need login";
		String url ="../userJoin/Login";
		
		if(id!=null) {
			msg ="visit reser.";
			url ="VisitReser";
		}
		System.out.println(msg);
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "../sangeun/alert.jsp");
		return data;
	}

}
