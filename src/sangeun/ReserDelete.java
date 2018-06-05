package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.DAO;

public class ReserDelete implements Action{

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		
		DAO dao = new DAO();
		
//		int rid = (int)request.getAttribute("delrid");
		int rid = Integer.parseInt(request.getParameter("rid"));
//		String id= request.getSession().getAttribute("id")+"";
		
		request.setAttribute("msg", "delete fail");
		request.setAttribute("url", "MyReserList");
		request.setAttribute("main", "../sangeun/alert.jsp");
		
		if(dao.reserDelete(rid)) {
			request.setAttribute("msg", "delete success");
		}
		dao.close();
		
		return new ActionData();
	}
	

}
