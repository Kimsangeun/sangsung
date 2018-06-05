package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.UserDAO;
import DB.UserVO;
import model.Action;
import model.ActionData;

public class LoginReg implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		
		UserVO loginVO = dao.login(vo);
		
		String msg="login fail";
		String url="Login";
		if(loginVO!=null) {
			msg="login success";
			url="../center/Main";
			HttpSession session = request.getSession(true);
			if(session !=null) 
				session.setAttribute("id", loginVO.getId());
		}
		dao.close();
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "../bb/alter.jsp");
		return data;
	}
}
