package fuc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DwDAO;
import DB.DwVO;
import model.Action;
import model.ActionData;

public class AdjustForm implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionData data = new ActionData();
		
		String id = request.getParameter("id");
		int textid = Integer.parseInt(request.getParameter("textid"));
		
		DwDAO dao = new DwDAO();
		
		DwVO vo = dao.detail(textid);
		int size;
		try {
			size = vo.getPhoto().split(",").length;
		}catch (Exception e) {
			// TODO: handle exception
			size = 0;
		}
		 
		dao.close();
		request.setAttribute("id", id);
		request.setAttribute("data", vo);
		request.setAttribute("size", size);
		request.setAttribute("main", "adjustform.jsp");
		
		return data;
	}
}
