package sangeun;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;
import model.DAO;
import model.EngineerVO;
import model.ReserVO;
import model.UserVO;

public class ReserDetailForm implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		
		DAO dao = new DAO();
		int rid = Integer.parseInt(request.getParameter("rid"));
		System.out.println("rid:"+rid);

		ReserVO rvo = dao.findReser(rid);
		UserVO uvo = dao.findUser(rvo.getUserId());
		EngineerVO evo = dao.findEngi(rvo.getGid());
		java.util.Date dd = new java.util.Date();
		request.setAttribute("today", new Date(dd.getYear(), dd.getMonth(), dd.getDate()+1));

		request.setAttribute("reser", rvo);
		request.setAttribute("user", uvo);
		request.setAttribute("engi", evo);
		
		request.setAttribute("main", "../sangeun/reserDetailForm.jsp");
		
		dao.close();
		return new ActionData();
	}
}