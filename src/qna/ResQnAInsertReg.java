package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ResDAO;
import qnamodel.ResVO;

public class ResQnAInsertReg implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
	
		ResDAO dao = new ResDAO();
		
		ResVO vo = new ResVO();
		
		String url = "ResQnAInsert";
		
		String msg = "insert fail.";
		
		if(request.getParameter("title").equals("") || request.getParameter("title")==null) {
			msg = "insert title.";
		}else if(request.getParameter("content").equals("") || request.getParameter("content")==null) {
			msg = "insert content.";
		}else {
			
			vo.setPname(request.getParameter("pname"));
			vo.setUser_id(request.getParameter("user_id"));
			vo.setTitle(request.getParameter("title"));
			vo.setContent(request.getParameter("content"));
			
			int id = dao.resInsert(vo);
		
			msg =  "insert success.";
		
			url = "ResQnADetail?id="+id+"&seq=0";		
			
			
		}
		
		
		//System.out.println(vo.getUser_id());
		
		//System.out.println("id : "+id);
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "../qna/alert.jsp");
		
		dao.close();
		return new ActionData();
	}

}
