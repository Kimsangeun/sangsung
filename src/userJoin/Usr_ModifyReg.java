package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserVO;
import model.Action;
import model.ActionData;
import model.DAO;

public class Usr_ModifyReg implements Action {
	
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		String userid = (String)session.getAttribute("id");
		UserVO vo = new UserVO();
		DAO dao = new DAO();
		String msg="";
		String url="";

		
		String password = request.getParameter("pw");
		System.out.println("입력된 비밀번호dd : "+password);
		if(password.isEmpty()){
			msg = "비밀번호를 입력하세요.";
			url = "Usr_ModifyForm";
		}else {
			vo.setUserId(userid);
			vo.setPw(request.getParameter("pw"));
			vo.setPhone(request.getParameter("phone"));
			vo.setAddress(request.getParameter("address"));
			dao.modify(vo);
			System.out.println("1병수5");
			msg = "modify success.";
			url = "Usr_ListForm";
		
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		dao.close();
		request.setAttribute("main", "alter.jsp");
		return new ActionData();

	}

}
