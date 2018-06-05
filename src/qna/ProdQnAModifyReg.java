package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.flow.InsideSubRoutineFlowContext;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ProdDAO;
import qnamodel.ProdVO;

public class ProdQnAModifyReg implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
		
		ProdDAO dao = new ProdDAO();
		ProdVO vo = new ProdVO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		//System.out.println("Mseq : "+seq);
		
		vo.setId(id);
		vo.setSeq(seq);
		vo.setPname(request.getParameter("pname"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		
		if(request.getParameter("upfile")!=null) {
			
			vo.setUpfile(request.getParameter("upfile"));
			
		}else {
			
			vo.setUpfile(new ProdQnAReplyReg().fileUpload(request));
		}
		
		//System.out.println("upfile : "+vo.getUpfile());
		
		String msg = "이미지 파일만 등록할 수 있습니다.";
		String url = "ProdQnAModify?id="+vo.getId()+"&seq=0"; 
 		
		String ext = vo.getUpfile().substring(vo.getUpfile().lastIndexOf(".")+1).toLowerCase();
		
		if(vo.getUpfile().equals("") || new ProdQnAReplyReg().imgChk(ext) ) {
			dao.modify(vo);
			
			msg = "수정이 완료되었습니다.";
			url = "ProdQnADetail?id="+vo.getId()+"&seq=0";
		}
		
		request.setAttribute("data", dao.detail(vo.getId(), vo.getSeq()));
		request.setAttribute("main", "../qna/alert.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		dao.close();
		return new ActionData();
	}

}
