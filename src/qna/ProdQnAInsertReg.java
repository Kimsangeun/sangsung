package qna;

import java.io.File;
import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ProdDAO;
import qnamodel.ProdVO;

public class ProdQnAInsertReg implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
	
		ProdDAO dao = new ProdDAO();
		
		ProdVO vo = new ProdVO();
	
		
		String url = "ProdQnAInsert?user_id="+request.getParameter("user_id");
		
		String msg = "등록실패";
		
		if(request.getParameter("pname").equals("") || request.getParameter("pname")==null){
			msg = "이름을 입력해주세요.";
		}else if(request.getParameter("title").equals("") || request.getParameter("title")==null) {
			msg = "제목을 입력해주세요.";
		}else if(request.getParameter("content").equals("") || request.getParameter("content")==null) {
			msg = "내용을 입력해주세요.";
		}else if(request.getParameter("pw").equals("") || request.getParameter("pw")==null) {
			msg = "비밀번호를 입력해주세요.";
		}else {
			
			vo.setPw(request.getParameter("pw"));
			vo.setPname(request.getParameter("pname"));
			vo.setTitle(request.getParameter("title"));
			vo.setContent(request.getParameter("content"));
			vo.setUpfile(fileUpload(request));
			
			int id = dao.insert(vo);
			
			msg =  "글이 등록되었습니다.";
		
			url = "ProdQnADetail?id="+id+"&seq=0&user_id="+request.getParameter("user_id");		
			
		}
		
	
//		System.out.println("vo.getUpfile() : "+vo.getUpfile());
//		System.out.println("upfile : "+request.getParameter("upfile"));
		
		//System.out.println("id : "+id);
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "../qna/alert.jsp");
		
		dao.close();
		return new ActionData();
	}
	
	public String fileUpload(HttpServletRequest request) {
		
		try {
			Part pp = request.getPart("upfile");
			
			System.out.println("pp : "+pp);
			
			if(pp.getContentType()!=null) {
				String fileName="";
				
				for(String hh : pp.getHeader("Content-Disposition").split(";")) {
					
					if(hh.trim().startsWith("filename")) {
						fileName=hh.substring(hh.indexOf("=")+1).trim().replaceAll("\"", "");
					}
					
					if(!fileName.equals("")) {
						return fileSave(pp, fileName, request);
					}
					
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String fileSave(Part pp, String fileName, HttpServletRequest request) {
		
		int pos = fileName.lastIndexOf(".");
		
		String fileDo = fileName.substring(0, pos);
		String ext = fileName.substring(pos);
		
		int cnt=0;
		
		String path = "C:\\Users\\tkddm\\sangsung2\\SemiProj\\WebContent\\up2\\";
	
		File ff = new File(path+fileName);
		
		while(ff.exists()) {
			
			fileName = fileDo+"_"+(cnt++)+ext;
			
			ff = new File(path+fileName);
		}
		
		try {
			pp.write(path+fileName);
			pp.delete();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileName;
	}

}
