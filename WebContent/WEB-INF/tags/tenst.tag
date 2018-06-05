<%@ tag language="java" pageEncoding="UTF-8"%>
<jsp:doBody var="con" scope="page"/>

<%
	String con = (String)jspContext.getAttribute("con");

	String res = con.length()<=10?con:(con.substring(0, 7) + "...");
%>

<%=res%>
