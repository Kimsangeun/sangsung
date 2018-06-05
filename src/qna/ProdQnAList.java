package qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnamodel.Action;
import qnamodel.ActionData;
import qnamodel.ProdDAO;

public class ProdQnAList implements Action {

	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse responce) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String user_id = (String)session.getAttribute("id");
		
		request.setAttribute("user_id", user_id);
	
		System.out.println("user_id1 : "+user_id);
		ProdDAO dao = new ProdDAO();
		
		String pname = request.getParameter("search");

		int page = 1;
		
		int limit = 10;
		int pageLimit = 4;
		
		if(request.getParameter("page")!=null && !request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int start = (page-1)*limit+1;
		int end = page*limit;
		
		int startPage = (page-1)/pageLimit*pageLimit+1;
		int endPage = startPage+pageLimit-1;
		
		int total = 0;
		
		//System.out.println(request.getParameter("search")+"::::"+request.getParameter("select"));
		
		//System.out.println("aaa : "+request.getParameter("listselect"));
	/*	ArrayList<Integer> ffid = new ArrayList<>();
		ArrayList<Integer> eeid = new ArrayList<>();
		*/
		
		if(request.getParameter("listselect")!=null) {
			/*int cnt = 0;
			
			ArrayList<Integer> idarr = dao.selectedCount(start, end);
			
			for (int id : idarr) {
				if(dao.replyCount(id)>1) {
					cnt++;
					ffid.add(id);
				}else {
					eeid.add(id);
				}
			}
			
			switch (request.getParameter("listselect")) {
			case "완료": //완료된 답변
				total = cnt;
				System.out.println("total3 : "+total);
				break;
			case "미완료": //미완료 답변
				total = dao.totalCount()-cnt;
				System.out.println("total4 : "+total);
				break;
			default:
				total = dao.totalCount();
				break;
			}
			*/
			switch (request.getParameter("listselect")) {
			case "완료": //완료된 답변
				total = dao.totalCount(1);
				//System.out.println("total3 : "+total);
				break;
			case "미완료": //미완료 답변
				total = dao.totalCount(0);
				//System.out.println("total4 : "+total);
				break;
			default:
				total = dao.totalCount();
				break;
			}
			
		}else {
			
			if(request.getParameter("select")!=null && request.getParameter("search")!=null) {
				total = dao.totalCount(request.getParameter("search"));
				//System.out.println("total1 : "+total);
				
			}else {
				total = dao.totalCount();
				//System.out.println("total2 : "+total);
				
			}
				
		}
		
		int totalPage = total/limit;
		
		if(total%limit !=0)
			totalPage++;
		
		if(endPage > totalPage)
			endPage = totalPage;

		request.setAttribute("page", page);
		request.setAttribute("start", start);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		if(request.getParameter("listselect")!=null) {
			
			request.setAttribute("listselect",request.getParameter("listselect"));
			
			switch (request.getParameter("listselect")) {
			case "완료":
				//System.out.println("완료인데");
				request.setAttribute("data", dao.selectList(start, end, 1));
				
				break;
			case "미완료":
				//System.out.println("미완료인데");
				request.setAttribute("data", dao.selectList(start, end, 0));
				
				break;

			default:
				//System.out.println("전체인데");
				request.setAttribute("data", dao.list(start, end));
				
				break;
			}
			
		}else {
			
			if(request.getParameter("select")!=null) {
			
				request.setAttribute("search", request.getParameter("search"));
				request.setAttribute("select", request.getParameter("select"));
				request.setAttribute("data", dao.searchList(start, end, pname));
				
			}else {
				
				request.setAttribute("data", dao.list(start, end));
					
			}
		
		}
		
		request.setAttribute("main", "../qna/prodQnAList.jsp");	
	
		dao.close();
		return new ActionData();
	}

}
