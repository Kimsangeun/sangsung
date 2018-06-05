package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ResDAO;

public class ResQnADetail implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		System.out.println("detailuser : "+user_id);
		ResDAO dao = new ResDAO();
		
		int id  = Integer.parseInt(request.getParameter("id"));
		int seq  = Integer.parseInt(request.getParameter("seq"));
		
		int replyCount = dao.resReplyCount(id);
			
		System.out.println("replyCount : "+replyCount);
		
		request.setAttribute("replyCount", replyCount);
		request.setAttribute("replydata", dao.resReplyList(id));
		request.setAttribute("data", dao.resDetail(id, seq));
		request.setAttribute("user_id", user_id);
		
		if(user_id.equals("admin")) {
		
			request.setAttribute("main", "../qna/resQnADetail.jsp");
			
		}else {
			request.setAttribute("main", "../my/myResQnADetail.jsp");
			
		}
		
		dao.close();
		return new ActionData();
	}

}
