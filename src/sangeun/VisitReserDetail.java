package sangeun;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.VisitResDAO;
import DB.VisitResVO;
import model.Action;
import model.ActionData;
import model.DAO;

public class VisitReserDetail implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		int txtid = Integer.parseInt(request.getParameter("txtid"));
		
		ActionData data = new ActionData();
		VisitResDAO dao = new VisitResDAO();
		DAO adao=new DAO();
		VisitResVO vo = dao.detail(txtid);
		dao.close();
		
		
		request.setAttribute("user", adao.findUser(request.getSession().getAttribute("id")+""));
		request.setAttribute("data", vo);
		java.util.Date dd = new java.util.Date();
		request.setAttribute("today", new Date(dd.getYear(), dd.getMonth(), dd.getDate()+1));

		request.setAttribute("main", "../sangeun/visit_detail.jsp");
		return data;
	}
}
