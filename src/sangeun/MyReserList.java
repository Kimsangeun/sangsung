package sangeun;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.DAO;

public class MyReserList implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {

		DAO dao = new DAO();
		String userid = request.getSession().getAttribute("id") + "";
		int page = 1;
		
		int limit = 10, pageLimit = 4;
		
		if(request.getParameter("page")!=null && !request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int start = (page-1)*limit+1;
		int end = page*limit;
		
		int startPage = (page-1)/pageLimit*pageLimit+1;
		int endPage = startPage+pageLimit-1;
		int total;
		
		if(userid.equals("admin")) {
			total = dao.totalCount();
		}
		else {
			total = dao.totalCount(userid);
		}
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
		
		String ii="3";
		if(request.getParameter("ii")!=null) {
			ii = request.getParameter("ii");
			System.out.println("ii수정"+ii);
		}
		java.util.Date day = new java.util.Date();
		Date date = new Date(day.getYear(), day.getMonth(), day.getDate());
		request.setAttribute("today", date);

		if (userid.equals("admin"))
			request.setAttribute("mylist", dao.allReser(start, end, ii));
		else
			request.setAttribute("mylist", dao.userReser(userid, start, end, ii));
		
		request.setAttribute("main", "../sangeun/myReserForm.jsp");

		dao.close();
		return new ActionData();
	}
}
