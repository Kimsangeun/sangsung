package fuc;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DwDAO;
import DB.DwVO;
import model.Action;
import model.ActionData;

public class DeleteDriver implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		String id = request.getParameter("id");
		int textid = Integer.parseInt(request.getParameter("textid"));
		String dfile = request.getParameter("dfile");
		
		String path = request.getRealPath("driver");
		path = "C:\\Users\\JHTA\\Desktop\\semi_pro\\SangSung\\WebContent\\driver\\";

		File ff = new File(path+dfile);
		ff.delete();
		
		DwDAO dao = new DwDAO();
		
		dao.defile(textid);
		DwVO vo = dao.detail(textid);
		
		dao.close();
		request.setAttribute("id", id);
		request.setAttribute("size", 1);
		request.setAttribute("data", vo);
		request.setAttribute("main", "adjustform.jsp");
		return data;
	}
}
