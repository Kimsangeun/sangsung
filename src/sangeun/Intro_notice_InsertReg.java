package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.Intro_noticeDAO;
import DB.Intro_noticeVO;
import model.Action;
import model.ActionData;

public class Intro_notice_InsertReg implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionData data = new ActionData();
		Intro_noticeVO vo = new Intro_noticeVO();
		
		String page = request.getParameter("page");
		
		vo.setContent(request.getParameter("content"));
		vo.setTitle(request.getParameter("title"));
		
		int id = new Intro_noticeDAO().insert(vo);
		data.setRedirect(true);
		data.setPath("Intro_notice_detail?page=1&id="+id);
		return data;
	}
	

}