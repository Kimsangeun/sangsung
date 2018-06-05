package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.VisitResDAO;
import DB.VisitResVO;
import model.Action;
import model.ActionData;

public class VisitModifyReg implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		int id = Integer.parseInt(request.getParameter("txtid"));
		String day = request.getParameter("day");
		VisitResVO vo = new VisitResVO();
		vo.setId(id);
		vo.setPhone2(request.getParameter("phone"));
		vo.setContent(request.getParameter("content"));
		VisitResDAO dao = new VisitResDAO();
		dao.update(vo,day);
		
		VisitResVO res = dao.detail(vo.getId());
		
		request.setAttribute("data",res);
		request.setAttribute("main", "../sangeun/visit_detail.jsp");
		
		dao.close();
		
		return data;
	}
}
