package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserVO;
import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ResDAO;

public class ResQnAInsert implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		ResDAO dao = new ResDAO();
		
		UserVO vo = new UserVO();
		
		vo = dao.search(user_id);
		
		//System.out.println("getName : "+vo.getName());
		
		//System.out.println("user_id : "+user_id);
		
		request.setAttribute("pname", vo.getName());
		request.setAttribute("user_id", user_id);
		request.setAttribute("main", "../qna/resQnAInsertForm.jsp");
		
		dao.close();
		return new ActionData();
	}

}
