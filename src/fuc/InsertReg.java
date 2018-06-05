package fuc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DB.DwDAO;
import DB.DwVO;
import model.Action;
import model.ActionData;

public class InsertReg implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		boolean flag = true;
		DwVO vo = new DwVO();
		String userid= request.getParameter("id");
		vo.setTitle(request.getParameter("title"));
		vo.setCate(request.getParameter("cate"));
		System.out.println(vo.getCate());
		vo.setRang(request.getParameter("rang"));
		vo.setContent(request.getParameter("content"));
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		String filename;
		
		for(int i=0;i<=cnt;i++) {
			if(i!=0) 
				flag = imgChk(request,"imgfile"+i);		
			else 
				flag = imgChk(request,"imgfile");	
			if(!flag)
				break;
		}
		String msg = "실패";
		String url = "WriteForm?id="+userid+"&rang="+vo.getRang()+"&cate="+vo.getRang();
		if(flag) {
			for(int i=0;i<=cnt;i++) {
				if(i!=0) 
					vo.setPhoto(vo.getPhoto()+","+photoUpload(request,"imgfile"+i));	
				else 
					vo.setPhoto(photoUpload(request,"imgfile"));
			}
			vo.setDriver(driverUpload(request, "driver"));
			DwDAO dao = new DwDAO();
			int textid = dao.insert(vo);
			msg = "성공";
			url = "Detail?userid="+userid+"&textid="+textid;
			dao.close();
		}
		request.setAttribute("main", "alter.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return data;
	}
	
	public String driverUpload(HttpServletRequest request,String tagName) {
		String fileName="";
		try {
			Part pp = request.getPart(tagName);
			if(pp.getContentType()!=null) {
				for(String hh: pp.getHeader("Content-Disposition").split(";")) {
					if(hh.trim().startsWith("filename")) {
						fileName = hh.substring(hh.indexOf("=")+1)
								.trim().replace("\"", "");
					}
				}
				if(!(fileName.equals(""))) {
					return fileSave(pp, fileName, request,"driver");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "";
	}
	
	public boolean imgChk(HttpServletRequest request,String tagName) {
		String fileName="";
		try {
			Part pp = request.getPart(tagName);
			if(pp.getContentType()!=null) {
				for(String hh: pp.getHeader("Content-Disposition").split(";")) {
					if(hh.trim().startsWith("filename")) {
						fileName = hh.substring(hh.indexOf("=")+1)
								.trim().replace("\"", "");
						String exp = fileName.substring(fileName.lastIndexOf(".")+1);
						List<String> expset = new ArrayList<>();
						expset.add("png"); expset.add("jpg"); expset.add("jpeg"); expset.add("gif");
						if(!expset.contains(exp)) {
							return false; 
						}
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	
	public String photoUpload(HttpServletRequest request,String tagName) {
		String fileName="";
		try {
			Part pp = request.getPart(tagName);
			if(pp.getContentType()!=null) {
				for(String hh: pp.getHeader("Content-Disposition").split(";")) {
					if(hh.trim().startsWith("filename")) {
						fileName = hh.substring(hh.indexOf("=")+1)
								.trim().replace("\"", "");
					}
				}
				if(!(fileName.equals(""))) {
					return fileSave(pp, fileName, request,"photo");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "";
	}
	String fileSave(Part pp, String fileName, HttpServletRequest request,String folder) {
		int pos = fileName.lastIndexOf(".");
		
		String fileDo = fileName.substring(0, pos);
		String exp = fileName.substring(pos);
		//주석주석
		String path = request.getRealPath("photo")+"\\";
		path = "C:\\Users\\JHTA\\Desktop\\semi_pro\\SangSung\\WebContent\\"+folder+"\\";
		
		int cnt =0;
		
		File ff = new File(path+fileName);
		
		while(ff.exists()) {
			fileName = fileDo+"_"+(cnt++)+exp;
			ff = new File(path+fileName);
		}
		try {
			pp.write(path+fileName);
			pp.delete();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return fileName;
	}
}
