package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.MyDAO;
import qnamodel.ResDAO;

public class MyResQnAList implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		MyDAO dao = new MyDAO();
		
		int page = 1;
		
		int limit = 10, pageLimit = 4;
		
		if(request.getParameter("page")!=null && !request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int start = (page-1)*limit+1;
		int end = page*limit;
		
		int startPage = (page-1)/pageLimit*pageLimit+1;
		int endPage = startPage+pageLimit-1;
		
		int total = dao.totalCount(user_id);
		
		int totalPage = total/limit;
		
		if(total%limit !=0)
			totalPage++;
		
		if(endPage > totalPage)
			endPage = totalPage;
		
		request.setAttribute("page", page);
		request.setAttribute("start", start);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		request.setAttribute("data", dao.list(start, end, user_id));
		request.setAttribute("user_id", user_id);
		request.setAttribute("main", "../my/myResQnAList.jsp");
		
		dao.close();
		return new ActionData();
	}

}
