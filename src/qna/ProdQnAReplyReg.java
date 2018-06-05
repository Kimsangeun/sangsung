package qna;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ProdDAO;
import qnamodel.ProdVO;

public class ProdQnAReplyReg implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
		
		ProdDAO dao = new ProdDAO();
		ProdVO vo = new ProdVO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		vo.setId(id);
		vo.setPname(request.getParameter("pname"));
		vo.setContent(request.getParameter("content"));
		
		vo.setUpfile(fileUpload(request));
		
		String ext = vo.getUpfile().substring(vo.getUpfile().lastIndexOf(".")+1).toLowerCase();
		System.out.println("ext: "+ext);
		
		String msg = "이미지 파일만 등록할 수 있습니다.";
		String url = "ProdQnADetail?id="+vo.getId()+"&seq=0";
		
		if(vo.getUpfile().equals("") || imgChk(ext)) {
			dao.replyInsert(vo);
			msg = "답글이 등록되었습니다.";
		}
		
		request.setAttribute("replydata", dao.replyList(id));
		request.setAttribute("data", dao.detail(id, 0));
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "../qna/alert.jsp");
		
		dao.close();
		return new ActionData();
	}

	public String fileUpload(HttpServletRequest request) {

		try {
			Part pp = request.getPart("upfile");
			
			if(pp.getContentType()!=null) {
				String fileName="";
				System.out.println(pp.getHeader("Content-Disposition"));
				for (String hh : pp.getHeader("Content-Disposition").split(";")) {
					if(hh.trim().startsWith("filename")) {
						fileName= hh.substring(hh.indexOf("=")+1).trim().replaceAll("\"", "");
					}
				}
				
				if(!fileName.equals("")) {
					return fileSave(pp, fileName, request);
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
		
		String path = request.getRealPath("up2")+"\\";
		path = "C:\\Users\\tkddm\\sangsung2\\SemiProj\\WebContent\\up2\\";
		
		File ff = new File(path+fileName);
	
		int cnt = 0;
		
		while(ff.exists()) {
			fileName = fileDo+"_"+(cnt++)+ext;
			
			ff = new File(path+fileName);
		}
		if(imgChk(fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase())) {
			try {
				pp.write(path+fileName);
				pp.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileName;
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
