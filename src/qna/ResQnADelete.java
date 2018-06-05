package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ResDAO;

public class ResQnADelete implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);

		ResDAO dao = new ResDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		System.out.println("user : "+user_id);
		dao.resDelete(id, seq);
		
		String msg = "delete fail.";
		String url ="";
		
		if(seq==0 && request.getParameter("pp").equals("res")) {
			url = "ResQnAList";
		}else if(seq!=0){
			url="ResQnADetail?id="+id+"&seq=0";
		}else if(request.getParameter("pp").equals("my")){
			url="MyResQnAList" ;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "../qna/alert.jsp");
		
		dao.close();
		return new ActionData();
	}

}
