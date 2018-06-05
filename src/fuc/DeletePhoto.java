package fuc;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DwDAO;
import DB.DwVO;
import model.Action;
import model.ActionData;

public class DeletePhoto implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		String id = request.getParameter("id");
		int textid = Integer.parseInt(request.getParameter("textid"));
		String photo = request.getParameter("photo");
		String path = request.getRealPath("photo");
		path = "C:\\Users\\JHTA\\Desktop\\semi_pro\\SangSung\\WebContent\\photo\\";
		
		for (String photofile : photo.split(",")) {
			System.out.println(photofile);
			File ff = new File(path+photofile);
			ff.delete();
		}
		DwDAO dao = new DwDAO();
		dao.dephoto(textid);
		DwVO vo = dao.detail(textid);
		dao.close();
		
		int size;
		try {
			size = vo.getPhoto().split(",").length;
		}catch (Exception e) {
			// TODO: handle exception
			size = 0;
		}
		
		request.setAttribute("id", id);
		request.setAttribute("size", size);
		request.setAttribute("data", vo);
		request.setAttribute("main", "adjustform.jsp");
		return data;
	}
}
