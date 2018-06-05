package fuc;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DwDAO;
import DB.DwVO;
import model.Action;
import model.ActionData;

public class Delete implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		File ff;
		ActionData data = new ActionData();
		
		int textid = Integer.parseInt(request.getParameter("textid"));
		
		DwDAO dao = new DwDAO();
		DwVO vo = dao.detail(textid);
		
		String path = request.getRealPath("photo");
		path = "C:\\Users\\JHTA\\Desktop\\semi_pro\\SangSung\\WebContent\\photo\\";
		
		for (String name : vo.getPhoto().split(",")) {
			ff = new File(path+name);
			ff.delete();
		}
		path = request.getRealPath("driver");
		path = "C:\\Users\\JHTA\\Desktop\\semi_pro\\SangSung\\WebContent\\driver\\";
		
		ff = new File(path+vo.getDriver());
		ff.delete();
		
		dao.delete(textid);
		dao.close();
		
		String id = request.getParameter("id");
		
		data.setPath("ListView?id="+id+"&rang="+vo.getRang()+"&cate="+vo.getCate());
		data.setRedirect(true);
		
		return data;
	}
}
