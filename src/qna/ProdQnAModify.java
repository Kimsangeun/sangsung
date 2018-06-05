package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ProdDAO;

public class ProdQnAModify implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
		
		ProdDAO dao = new ProdDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int seq = Integer.parseInt(request.getParameter("seq"));
			
		String pw = null;
		
		System.out.println("seq : "+seq);
		
		if(seq==0) {
			pw = request.getParameter("pw");
			
			if(dao.check(id, pw)) {
				request.setAttribute("data", dao.detail(id, seq));
				request.setAttribute("main", "../qna/prodQnAModify.jsp");
			}else {
				request.setAttribute("msg", "fdgd.");
				request.setAttribute("url", "ProdQnACheck?id="+id+"&seq=0&reg=modi");
				request.setAttribute("main", "../qna/alert.jsp");
			}
		}else {
			
			request.setAttribute("data", dao.detail(id, seq));
			request.setAttribute("main", "../qna/prodQnAModify.jsp");

		}
		dao.close();
		return new ActionData();
	}

}
