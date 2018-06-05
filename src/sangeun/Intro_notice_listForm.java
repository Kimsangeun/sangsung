package sangeun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.Intro_noticeDAO;
import model.Action;
import model.ActionData;

public class Intro_notice_listForm implements Action{

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionData res = new ActionData();
		Intro_noticeDAO dao = new Intro_noticeDAO();
		
		int page = 1;
		int limit = 10; 
		int pageLimit=10;
	
		
		if(request.getParameter("page")!=null && !request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int start = (page-1)*limit+1;
		int end = page*limit;
		
		int startPage = (page-1)/pageLimit*pageLimit+1;
		int endPage = startPage+pageLimit-1;
		
		int total = dao.totalCount();
		int totalPage = total/limit;
		dao.close();

		if(total%limit!=0) {
			totalPage++;
		}
		

		if(endPage> totalPage) {
			endPage= totalPage;
		}

		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("start", start);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("data", dao.list(start, end));

		request.setAttribute("main", "intro_notice_listForm.jsp");
		

		return res;
	}
	
	

}
