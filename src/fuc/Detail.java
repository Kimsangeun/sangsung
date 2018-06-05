package fuc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DwDAO;
import DB.DwVO;
import model.Action;
import model.ActionData;

public class Detail implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		String uid = request.getParameter("userid");
		int textid = Integer.parseInt(request.getParameter("textid"));
		
		DwDAO dao = new DwDAO();
		DwVO vo = dao.detail(textid);
		dao.close();
		
		request.setAttribute("id", uid);
		request.setAttribute("data", vo);
		request.setAttribute("main", "detail.jsp");
		return data;
	}
}
