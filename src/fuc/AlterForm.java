package fuc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.DwDAO;
import model.Action;
import model.ActionData;

public class AlterForm implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		DwDAO dao = new DwDAO();
		Map<String,List<String>> mm = dao.categori();
		dao.close();
		
		request.setAttribute("data", mm);
		request.setAttribute("main", "altercate.jsp");
		return data;
	}
}
