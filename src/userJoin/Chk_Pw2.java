package userJoin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Action;
import model.ActionData;
import model.DAO;
import model.UserVO;

public class Chk_Pw2 implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String userid = (String)session.getAttribute("id");
		String msg="";
		String url="";
		DAO dao = new DAO();
		UserVO user = dao.findUser(userid);
		dao.close();

		System.out.println("유저아이디 : "+user);
		String password = request.getParameter("pw");
		System.out.println("입력된 비밀번호 : "+password);
		System.out.println("디비의비밀번호:  "+user.getPw());
		System.out.println("아이 왜 비밀번호 확인합니다.");
		if(password.equals(user.getPw())) {
			System.out.println("비번이 같네요");
			 msg = "확인성공";
			 url = "Usr_ListForm";
		}else {
			System.out.println("비번이 다르넹");
			 msg = "비밀번호가 다릅니다.";
			 url = "Chk_Pw";
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "alter.jsp");
		return new ActionData();
	}

}
