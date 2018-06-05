package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.UserDAO;
import model.Action;
import model.ActionData;

public class DeleteUser implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		int livepage=1;
		if(request.getParameter("livepage")!=null) {
			livepage = Integer.parseInt(request.getParameter("livepage"));
		}
		int deathpage=1;
		if(request.getParameter("deathpage")!=null) {
			deathpage = Integer.parseInt(request.getParameter("deathpage"));
		}
		
		UserDAO dao = new UserDAO();
		
		String[] ids = request.getParameterValues("box");
		
		for (String str : ids) {
			dao.change(str,"1");
		}
		dao.close();
		data.setPath("KickMember?livepage="+livepage+"&deathpage="+deathpage);
		data.setRedirect(true);
		return data;
	}
}
