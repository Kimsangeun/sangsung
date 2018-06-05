package fuc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class WriteForm implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		String cate = request.getParameter("cate");
		String rang = request.getParameter("rang");
		String id = request.getParameter("id");
		
		request.setAttribute("main", "writerform.jsp");
		request.setAttribute("cate", cate);
		request.setAttribute("rang", rang);
		request.setAttribute("id", id);
		
		return data;
	}
}
