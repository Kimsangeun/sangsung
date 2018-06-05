<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="https://fonts.googleapis.com/css?family=Poor+Story|Sunflower:300" rel="stylesheet">
 <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../js/jquery-ui.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
	font-family: 'Sunflower', sans-serif;
/* 	font-family: 'Poor Story', cursive; */
} 
html,body{
	width:100%;
	height: 100%;
	overflow: auto;
}
.favorite{
	width: 100%;
	height: 30%;
	position: relative;
	background-color: #999;
	border: 5px outset #224;
}
.imgs{
	height: 100%;
	width: 30%;
	position: relative;
	float: right;
}
.H_down_img{
	width: 50%;
	height: 90%;
}
#cate{
	margin-top: 10px;
	position: relative;
	width: 100%;
	height: 60%;
	border-top:5px outset #ddd;
	border-bottom:5px outset #ddd;
	clear: both;
}
#alter{
	
	width: 30px;
	height: 100%;
}

</style>

<c:if test="${main.length() < 16 }">
<style>

</style>
</c:if>
</head>
<body>
<a href="#k">
	<div style="position: fixed; right: 5%; bottom:17%;">
		<img style="width: 50px; height: 50px" src="../opimg/화살표.png">
	</div>
</a>
<div style="width: 100%; height: 3%; border-bottom: 1px inset gray;">
	<jsp:include page="inc/hightop.jsp"></jsp:include>
</div>
<div style="width: 100%; height: 10%; border-bottom: 1px inset gray; position: relative;">
	<jsp:include page="inc/top.jsp"></jsp:include>
</div>
<div id="S_s" style="z-index: 1; width: 50%; margin-left: 25%; position:relative;">
<jsp:include page="../view/bb/${main }"></jsp:include>
</div>
<div style="width: 100%; height: 5%; border-bottom: 1px inset gray; position: relative;">
</div>
<div style="bottom:0px; width: 100%; height: 10%; position: relative; margin-top:1%; border-bottom: 1px inset gray;">
<jsp:include page="inc/bottom.jsp"></jsp:include>
</div>
</body>
</html>