package qna;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.flow.InsideSubRoutineFlowContext;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ResDAO;
import qnamodel.ResVO;

public class ResQnAModifyReg implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
		
		ResDAO dao = new ResDAO();
		ResVO vo = new ResVO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		vo.setId(id);
		vo.setSeq(seq);
		vo.setPname(request.getParameter("pname"));
		vo.setUser_id(request.getParameter("user_id"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		
		if(request.getParameter("upfile")!=null) {
			
			vo.setUpfile(request.getParameter("upfile"));
			
		}else {
			
			vo.setUpfile(new ResQnAReplyReg().fileUpload(request));
		}
		
		String msg = "이미지 파일만 등록할 수 있습니다.";
		String url = "ResQnAModify?id="+vo.getId()+"&seq="+vo.getSeq()+"&user_id="+vo.getUser_id();
		
		String ext = vo.getUpfile().substring(vo.getUpfile().lastIndexOf(".")+1).toLowerCase();

		System.out.println("ext: "+ext);
		
		if(vo.getUpfile().equals("") || imgChk(ext)) {
			
			dao.resModify(vo);
		
			msg = "수정이 완료되었습니다.";
			
			url = "ResQnADetail?id="+vo.getId()+"&seq=0";
				
			
			
		}
			
		request.setAttribute("data", dao.resDetail(vo.getId(), vo.getSeq()));
		request.setAttribute("main", "../qna/alert.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		dao.close();
		return new ActionData();
	}

	public boolean imgChk(String ext)
	{
		ArrayList<String> extArr = new ArrayList<>();
		
		extArr.add("gif");
		extArr.add("png");
		extArr.add("jpg");
		extArr.add("jpeg");

		return extArr.contains(ext);
	}
}
