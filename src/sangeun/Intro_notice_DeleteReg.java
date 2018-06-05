package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.Intro_noticeDAO;
import DB.Intro_noticeVO;
import model.Action;
import model.ActionData;

public class Intro_notice_DeleteReg implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		Intro_noticeDAO dao = new Intro_noticeDAO();
		Intro_noticeVO vo = new Intro_noticeVO();
		int page = Integer.parseInt(request.getParameter("page"));
		vo.setId(Integer.parseInt(request.getParameter("id")));
		
		dao.delete(vo.getId());
		String msg = "delete success.";
		String url = "Intro_notice_listForm?page="+page;
		dao.close();
		
		ActionData data = new ActionData();
		
		request.setAttribute("main", "alter.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return data;
	}

}
