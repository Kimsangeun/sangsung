<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.S_sec{
margin-left: 7%;
} 
</style>

<div style="height: 750px;">
<c:choose>
<c:when test="${param.reg eq 'modi' }">
	<form action="ProdQnAModify">
	
</c:when>
<c:otherwise>
	<form action="ProdQnADelete">
</c:otherwise>
</c:choose>

<input type="hidden" name="id" value="${param.id }">
<input type="hidden" name="seq" value="${param.seq }">
<input type="hidden" name="user_id" value="${user_id }">
<br>
<h2>비밀글 보기</h2>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >


<div style="width: 100%; padding-top:15%; padding-bottom:20%; background: #f0f0f0" align="center" >
<table border="0" cellspacing="1"> 

<tr>
	<td>
	<div style="text-align: center;">
	※비밀번호가 필요한 기능입니다.<br>
	비밀번호를 입력해 주세용.<br><br><br>
	</div></td>
</tr>
<tr>
	<td><div class="S_sec"><font size="5px">PW</font></div></td>
</tr>
<tr height="30px">
	<td>
	<div class="S_sec">
	<input type="password" name="pw" style="width : 172px;"/>
	</div>
	<div style="clear: both;"></div>	
	</td> 
</tr>
<tr>
	<td><div class="S_sec">
	<a href="ProdQnADetail?id=${param.id }&seq=0"><input type="button" value="취소" style="width:85px; height: 40px;"></a>
	<input type="submit" value="확인" style="width:85px; height: 40px;"/>
	</div>
	</td>
</table>
</div>
<div>
<hr style="margin-top: 60%;">
</div>
</form> 
</div>