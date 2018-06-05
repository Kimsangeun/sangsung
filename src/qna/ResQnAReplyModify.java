package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ResDAO;
import qnamodel.ResVO;

public class ResQnAReplyModify implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
		
		ResVO vo = new ResVO();
		ResDAO dao = new ResDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		vo.setId(id);
		vo.setSeq(seq);
		vo.setPname(request.getParameter("pname"));
		vo.setUser_id(request.getParameter("user_id"));
		vo.setContent(request.getParameter("content"));
		
		if(request.getParameter("upfile")!=null) {
			
			vo.setUpfile(request.getParameter("upfile"));
			
		}else {
			
			vo.setUpfile(new ResQnAReplyReg().fileUpload(request));
		}
		dao.resModify(vo);
		
		request.setAttribute("main", "../qna/resQnADetail.jsp");
		
		dao.close();
		return new ActionData();
	}

}
