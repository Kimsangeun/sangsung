package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.Intro_noticeDAO;
import DB.Intro_noticeVO;
import model.Action;
import model.ActionData;

public class Intro_notice_ModifyReg implements Action{

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		

		Intro_noticeVO vo = new Intro_noticeVO();
		Intro_noticeDAO dao = new Intro_noticeDAO();
		
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setContent(request.getParameter("content"));
		vo.setTitle(request.getParameter("title"));
		
		String page = request.getParameter("page");

		/*String url = "ModifyForm?id=" + vo.getId()+"&page="+page;*/
		
		dao.modify(vo);
		String msg = "modify success.";
		String url = "Intro_notice_detail?page="+page+"&id=" + vo.getId();
		dao.close();
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "alter.jsp");
		return new ActionData();

	}

}
