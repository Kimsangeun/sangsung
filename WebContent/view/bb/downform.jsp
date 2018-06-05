<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="height: 80%;">
<div><h2>제품 사용설명서 & 다운로드</h2></div>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >

<div class="favorite">
	<c:forEach var="ee" items="${fav }">
		<div class="imgs" >
				<a href="Detail?userid=${id}&textid=${ee.id }"><img class="H_down_img" src="../photo/${ee.photo }"></a>
		</div>
	</c:forEach>
</div>

<div align="center" id="cate">
	<c:forEach var="name" items="${cate.keySet() }">
		<div>
			<hr>
			<h3 align="center">${name }</h3>
			<table width=100% height=33% border="1"> 
				<tr>
				<c:forEach var="parts" items="${cate.get(name) }" varStatus="no">
					<td align="center"><a href="ListView?id=${id }&rang=${name }&cate=${parts}">${parts }</a></td>
					<c:if test="${(no.index+1)%4==0 }">
						</tr><tr>
					</c:if>
				</c:forEach>
				</tr>
			</table>
			<br>
			<hr size=5 color="#ddd"/>
		</div>
	</c:forEach>
</div>
<div style="clear: both;"></div>
	<form action="SearchReg">
		<input type="hidden" name="id" value="${id }">
		<div align="center"; style="position: relative; margin-top:10px; right:25%; width:50%; float: right;">
		
		<select style="height: 4%;" name="sch">
			<option value="1" selected="selected">제목</option>
			<option value="2">내용</option>
			<option value="3">내용/제목</option>
		</select>
		<input style="height: 4%;" type="text" name="schtxt">
		<input style="height: 5%;" type="submit" value="검색">
		</div>
	</form>
</div>