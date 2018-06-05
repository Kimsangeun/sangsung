package userJoin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.UserDAO;
import DB.UserVO;
import model.Action;
import model.ActionData;

public class KickMember implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		int livepage=1;
		if(request.getParameter("livepage")!=null) {
			livepage = Integer.parseInt(request.getParameter("livepage"));
		}
		int deathpage=1;
		if(request.getParameter("deathpage")!=null) {
			deathpage = Integer.parseInt(request.getParameter("deathpage"));
		}
		int limitpage=5;
		int limitview=8;
		
		int lstartpage = (livepage-limitpage)/limitpage*limitpage+1;
		int llastpage = lstartpage+limitpage-1;
		
		int dstartpage = (deathpage-limitpage)/limitpage*limitpage+1;
		int dlastpage = dstartpage+limitpage-1;
		
		int lsview = (livepage-1)*limitview+1;
		int llview = lsview+limitview-1;
		
		int dsview = (deathpage-1)*limitview+1;
		int dlview = dsview+limitview-1;
		
		
		List<UserVO> liveres = new ArrayList<>();
		List<UserVO> deathres = new ArrayList<>();
		UserDAO dao = new UserDAO();
		liveres = dao.choicedata("0",lsview,llview);
		int livetot = dao.totsize("0");
		int lastlive = livetot/limitview;
		if(livetot%limitview!=0) {
			lastlive++;
		}
		if(llastpage>lastlive) {
			llastpage= lastlive;
		}
		
		deathres = dao.choicedata("1",dsview,dlview);
		System.out.println(deathres);
		int deathtot = dao.totsize("1");
		int lastdeath = deathtot/limitview;
		if(lastdeath%limitview!=0) {
			lastdeath++;
		}
		if(dlastpage>lastdeath) {
			dlastpage = lastdeath;
		}
		
		dao.close();
		request.setAttribute("lstart", lstartpage);
		request.setAttribute("lend", llastpage);
		request.setAttribute("lpage", livepage);
		request.setAttribute("llast", lastlive);
		
		request.setAttribute("dstart", dstartpage);
		request.setAttribute("dend", dlastpage);
		request.setAttribute("dpage", deathpage);
		request.setAttribute("dlast", lastdeath);
		
		request.setAttribute("livedata", liveres);
		request.setAttribute("deathdata", deathres);
		request.setAttribute("main", "../aa/alluser.jsp");
		return data;
	}
}
