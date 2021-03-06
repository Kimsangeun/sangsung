package fuc;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class FileDown implements Action{
	@Override
	public ActionData exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String fileName = request.getParameter("file");
		String path = request.getRealPath("driver");
		path = "C:\\Users\\JHTA\\Desktop\\semi_pro\\SangSung\\WebContent\\driver\\";
		
		ActionData data = new ActionData();
		
		try {
			
			String en = URLEncoder.encode(fileName,"utf-8");	
			response.setHeader("Content-Disposition", "attachment;filename="+en);
			ServletOutputStream sos = response.getOutputStream();
			
			FileInputStream fis = new FileInputStream(path+fileName);
			
			byte[] buf = new byte[1024];
			
			while(fis.available()>0) {
				int len = fis.read(buf);
				sos.write(buf,0,len);
			}
			
			fis.close();
			sos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
