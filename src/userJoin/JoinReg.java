package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.UserDAO;
import DB.UserVO;
import model.Action;
import model.ActionData;

public class JoinReg implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		UserVO vo = new UserVO();
		
		vo.setId(request.getParameter("userid"));
		vo.setPw(request.getParameter("pw"));
		vo.setName(request.getParameter("name"));
		vo.setAddress(request.getParameter("address"));
		vo.setPhone(request.getParameter("phone"));
		
		UserDAO dao = new UserDAO();
		
		dao.insert(vo);
		dao.close();
		
		request.setAttribute("main","../aa/login.jsp" );
		
		return data;
	}
}
