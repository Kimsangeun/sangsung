package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ProdDAO;

public class ProdQnADetail implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
		
		ProdDAO dao = new ProdDAO();
		
		int id  = Integer.parseInt(request.getParameter("id"));
		int seq  = Integer.parseInt(request.getParameter("seq"));
		
		System.out.println("seq : "+seq);
		int replyCount = dao.replyCount(id);
			
		System.out.println("replyCount : "+replyCount);
		request.setAttribute("replyCount", replyCount);
		request.setAttribute("replydata", dao.replyList(id));
		request.setAttribute("data", dao.detail(id, seq));
		request.setAttribute("main", "../qna/prodQnADetail.jsp");
		
		dao.addCount(id);
		
		dao.close();
		return new ActionData();
	}

}
