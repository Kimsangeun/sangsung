<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
a{
	color: black;
}
</style>

<div style="height: 640px">
<div><h2>${param.rang }</h2></div>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >
<div class="favorite">
	<c:forEach var="ee" items="${fav }">
		<div class="imgs" >
				<a href="Detail?userid=${id}&textid=${ee.id }&page=${page}"><img class="H_down_img" src="../photo/${ee.photo }"></a>
		</div>
	</c:forEach>
</div>
<br>
<hr>
<div style="width:100%; height: 54%; position: relative;">
	<div style="width: 100%; text-align:center;">
			<div style="float: left; width: 20%">글번호</div>
			<div style="float: left; width: 60%">제목</div>
			<div style="float: left; width: 10%">작성일</div>
			<div style="float: left; width: 10%">조회수</div>
	</div>
	<div style="clear: both;"><hr size=10 color="#ddd"/></div>
	<c:forEach var="ee" items="${data }" varStatus="no">
		<div style="width: 100%; text-align:center;">
			<div style="float: left; width: 20%">${no.index+starttxt }</div>
			<div style="float: left; width: 60%"><a href="Detail?userid=${id }&textid=${ee.id }&page=${page}">${ee.title }</a></div>
			<div style="float: left; width: 10%">${ee.time }</div>
			<div style="float: left; width: 10%">${ee.cnt }</div>
		</div>
		<div style="clear: both;"><hr></div>
	</c:forEach>
</div>
	
<div style="width: 100%; height: 5%; position:relative; border-top :5px outset #ddd; border-bottom :5px outset #ddd;">
	<div align="center"; style="position: absolute; top:10px; left: 25%; width:50%; height: 60%;">
		<c:if test="${start!=1 }">
			<a href="ListView?id=${id }&rang=${param.rang }&cate=${param.cate}&page=${start-1 }">[<]</a>
		</c:if>
		<c:forEach begin="${start }" end="${end }" var="i">
			<c:choose>
				<c:when test="${i==page }">
					[${i }]
				</c:when>
				<c:otherwise>
					<a href="ListView?id=${id }&rang=${param.rang }&cate=${param.cate}&page=${i}">${i }</a>
				</c:otherwise>	
			</c:choose>
		</c:forEach>
		<c:if test="${end!=last }">
			<a href="ListView?id=${id }&rang=${param.rang }&cate=${param.cate}&page=${end+1 }">[>]</a>
		</c:if>
	</div>
</div>
<div style="position: relative; width: 100%; height: 7%;" align="right">
	<a href='Download?id=${id }'><input style="height: 100%; left:0px; position: absolute; " type="button" value="이전으로"></a>
	<form action="SearchReg">
		<input type="hidden" name="id" value="${id }">
		<div align="center"; style="position: relative; margin-top:10px; right:25%; width:50%; height: 100%; float: right;">
		
		<select style="height: 4%;" name="sch">
			<option value="1" selected="selected">제목</option>
			<option value="2">내용</option>
			<option value="3">내용/제목</option>
		</select>
		<input style="height: 4%;" type="text" name="schtxt">
		<input style="height: 5%;" type="submit" value="검색">
		</div>
	</form>
	<c:if test="${id=='admin' }">
		<a href="WriteForm?id=${id }&rang=${param.rang }&cate=${param.cate}">
		<img style="height: 100%; right:0px; position: absolute; " alt="수정버튼" src="../opimg/설정.png">
		</a>
	</c:if>
</div>
</div>
