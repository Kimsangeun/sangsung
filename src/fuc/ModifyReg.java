package fuc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DwDAO;
import DB.DwVO;
import model.Action;
import model.ActionData;

public class ModifyReg implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InsertReg inse = new InsertReg();
		ActionData data = new ActionData();
		String id = request.getParameter("id");
		DwVO vo = new DwVO();
		vo.setId(Integer.parseInt(request.getParameter("textid")));
		boolean flag = true;
		int cnt = Integer.parseInt(request.getParameter("cnt"));		
		String page = request.getParameter("page");
		if(request.getParameter("photo")!=null) {
			vo.setPhoto(request.getParameter("photo"));
			/*
			 * 추가적인 파일을 받는 경우
			 * */
			for(int i=1;i<=cnt;i++) {
				flag = inse.imgChk(request,"imgfile"+i);
				if(!flag)
					break;
			}
		}else {
			/*
			 * 파일을 완전히 삭제한후 다시 업로드하는 경우
			 * */
			for(int i=0;i<=cnt;i++) {
				if(i!=0) 
					flag = inse.imgChk(request,"imgfile"+i);		
				else 
					flag = inse.imgChk(request,"imgfile");	
				if(!flag)
					break;
			}
		}
		
		if(request.getParameter("dfile")!=null) {
			vo.setDriver(request.getParameter("dfile"));
		}
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));

		String url ="AdjustForm?id="+id+"&textid="+vo.getId()+"&page="+page;
		String msg ="실패";
		if(flag) {
			if(request.getParameter("photo")==null) {
				vo.setPhoto(inse.photoUpload(request, "imgfile"));
			}
			if(request.getParameter("dfile")==null) {
				vo.setDriver(inse.driverUpload(request, "driver"));
			}
			System.out.println(cnt);
			for(int i=1;i<=cnt;i++) {
				vo.setPhoto(vo.getPhoto()+","+inse.photoUpload(request, "imgfile"+i));
			}
			
			DwDAO dao = new DwDAO();
			dao.update(vo);
			url = "Detail?userid="+id+"&textid="+vo.getId()+"&page="+page;
			msg = "성공";
			dao.close();
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main","alter.jsp");
		
		return data;
	}
}
