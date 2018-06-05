package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ProdDAO;

public class ProdQnADelete implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
		
		ProdDAO dao = new ProdDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		
		System.out.println("user : "+user_id);
		
		String msg = "���� �����Ǿ����ϴ�.";
		String url ="";
		
		if(seq==0) {
			String pw=request.getParameter("pw");
			
			if(dao.check(id, pw)) {
				dao.delete(id, seq);
				
				url = "ProdQnAList";
				
			}else {
				msg="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
				url="ProdQnACheck?id="+id+"&seq=0&user_id="+user_id+"&reg=dele";
				
			}
		}else {
			dao.delete(id, seq);
		
			url="ProdQnADetail?id="+id+"&seq=0&user_id="+user_id;
			
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "../qna/alert.jsp");
			
		dao.close();
		return new ActionData();
	}

}
