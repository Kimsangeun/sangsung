package fuc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DwDAO;
import DB.DwVO;
import model.Action;
import model.ActionData;

public class ListView implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int limitpage = 5;
		int startpage = ((page-1)/limitpage)*5+1;
		int endpage = startpage+limitpage-1;
		
		int viewtxt = 20;
		int starttxt = ((page-1)*20)+1;
		int endtxt = page * viewtxt;
		
		String rang = request.getParameter("rang");
		String cate = request.getParameter("cate");
		
		DwDAO dao = new DwDAO();
		int total = dao.tot(rang, cate);
		int last = total/20;
		if(total%20!=0) {
			last+=1;
		}
		if(last<endpage) {
			endpage = last;
		}
		
		List<DwVO> res = dao.list(rang, cate,starttxt,endtxt);
		List<DwVO> favo = dao.favlist(rang, cate);
		dao.close();

		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("start", startpage);
		request.setAttribute("end", endpage);
		request.setAttribute("last", last);
		request.setAttribute("page", page);
		request.setAttribute("fav", favo);
		request.setAttribute("data", res);
		request.setAttribute("main", "list.jsp");
		return data;
	}
}
