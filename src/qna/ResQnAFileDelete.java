package qna;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ResDAO;
import qnamodel.ResVO;

public class ResQnAFileDelete implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
		
		ResVO vo = new ResVO();
		ResDAO dao = new ResDAO();
		
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo.setPname(request.getParameter("pname"));
		vo.setUser_id(user_id);
		vo.setContent(request.getParameter("content"));
		
		vo.setUpfile("");
		
		String fileName=request.getParameter("upfile");
		
		System.out.println("fileName : "+fileName);
		
		String path= request.getRealPath("up")+"\\";
		path="C:\\Users\\tkddm\\sangsung2\\SemiProj\\WebContent\\up\\";
		
		new File(path+fileName).delete();
		
		dao.resFileDelete(vo.getId(),vo.getSeq());
		
		request.setAttribute("main", "../qna/resQnAModify.jsp");
		request.setAttribute("data", vo);
		dao.close();
		
		return new ActionData();
	}

}
