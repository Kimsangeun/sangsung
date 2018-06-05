package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.VisitResDAO;
import model.Action;
import model.ActionData;

public class Visit_Delete implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		int txtid = Integer.parseInt(request.getParameter("txtid"));
		
		VisitResDAO dao = new VisitResDAO();
		
		dao.delete(txtid);
		
		dao.close();
		
		data.setPath("../center/Main");
		data.setRedirect(true);
		return data;
	}

}
