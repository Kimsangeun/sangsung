package sangeun;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.UserDAO;
import DB.VisitResDAO;
import DB.VisitResVO;
import fuc.Detail;
import model.Action;
import model.ActionData;

public class VisitReg implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		ActionData data = new ActionData();
		
		HttpSession session = request.getSession(true);
		
		String day = request.getParameter("day");
		
		String userid = (String)session.getAttribute("id");
		
		VisitResDAO dao = new VisitResDAO();
		VisitResVO vo = new UserDAO().getNamePhone(userid);

		vo.setUserid(userid);
		vo.setPhone2(request.getParameter("phone"));
		vo.setContent(request.getParameter("content"));
		
		int txtid = dao.insert(vo,day);
		
		dao.close();
		
		data.setPath("VisitReserDetail?txtid="+txtid);
		data.setRedirect(true);
		
		return data;
	}
}
