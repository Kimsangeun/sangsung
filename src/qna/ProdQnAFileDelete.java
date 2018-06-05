package qna;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ProdDAO;
import qnamodel.ProdVO;

public class ProdQnAFileDelete implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ProdVO vo = new ProdVO();
		ProdDAO dao = new ProdDAO();
		
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo.setTitle(request.getParameter("title"));
		vo.setPname(request.getParameter("pname"));
		vo.setContent(request.getParameter("content"));
		
		vo.setUpfile("");
		
		String fileName=request.getParameter("upfile");
		
		System.out.println("fileName : "+fileName);
		
		String path= request.getRealPath("up2")+"\\";
		path="C:\\Users\\tkddm\\git\\SeSemi\\WebContent\\up2\\";
		
		new File(path+fileName).delete();
		
		dao.fileDelete(vo.getId(),vo.getSeq());
		
		request.setAttribute("main", "../qna/prodQnAModify.jsp");
		request.setAttribute("data", vo);
		dao.close();
		
		return new ActionData();
	}

}
