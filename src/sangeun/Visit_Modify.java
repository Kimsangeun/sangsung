package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.VisitResDAO;
import model.Action;
import model.ActionData;

public class Visit_Modify implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		VisitResDAO dao = new VisitResDAO();
		int txtid = Integer.parseInt(request.getParameter("txtid"));
		
		request.setAttribute("data", dao.detail(txtid));
		request.setAttribute("main","../sangeun/visit_mod.jsp");
		dao.close();
		return data;
	}
}
