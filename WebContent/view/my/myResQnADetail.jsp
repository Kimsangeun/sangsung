<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>

<style type="text/css">

.S_sec{
float: left;
margin-left: 10px;
}

.S_sec1{
float: left;
width: 100px;
text-align: center;
background : #d0d0d0;
}

.list{
}
a{
color: black;
}
</style>
<br>
<div><h2>내 예약문의내역</h2></div>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >

<div>
<hr>
<div class="list">
	<div class="S_sec1">글번호</div>
	<div class="S_sec">${param.id }</div>
	<div style="clear: both;"></div>
</div>
<hr>
<div class="list">
	<div class="S_sec1">제목</div>
	<div class="S_sec">${data.title }</div>
	<div style="margin-left:65%;">
	<div class="S_sec1" style="width: 100px;" >작성일</div>
	<div class="S_sec">
	<fmt:formatDate value="${data.reg_date }" pattern="yy-MM-dd HH:mm:ss"/>
	</div>
	</div>
	<div style="clear: both;"></div>
</div>
<hr>
<div class="list" style="height: 300px;padding: 10px;">
	<div><ct:conBr>${data.content }</ct:conBr></div>
	<div style="clear: both;"></div>
	
	${data.user_id }, ${user_id }
	
</div>
<c:if test="${param.user_id eq 'admin'}">
<div class="list">
	<div style="float : right;">
	<a href="ResQnADelete?id=${data.id }&seq=${data.seq}&user_id=${user_id}&pp=my">삭제</a>
	</div>
	<div style="clear: both;"></div>
</div>
</c:if>
</div>
<c:if test="${replyCount==1 }">

<c:if test="${data.user_id eq user_id }">
<div class="list">
	<div style="float : right;">
	<a href="ResQnAModify?id=${data.id }&seq=${data.seq}&user_id=${user_id}&pp=my">수정 | </a>
	
	<a href="ResQnADelete?id=${data.id }&seq=${data.seq}&user_id=${user_id}&pp=my">삭제</a>
	</div>
	<div style="clear: both;"></div>
</div>
</c:if>
</c:if>



<!-- 관리자일때 댓글달기 -->
<c:if test="${user_id eq 'admin' }">
<hr style="margin-bottom: 50px;">

<form action="ResQnAReplyReg" method="post" enctype="multipart/form-data">
<input type="hidden" name="pname" value="관리자"/>
<input type="hidden" name="user_id" value="${user_id }"/>
<input type="hidden" name="id" value="${param.id }"/>

<div>
	<div class="list">
		<div align="center"" >
		<textarea name="content" rows="10" cols="90" style="resize: none; width: 90%;">내용을 입력하세요.</textarea>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100px;">사진첨부</div>
		<div class="S_sec">
		
		<input type="file" name="upfile"/>
		</div>
		<div style="text-align: right;">
			<input type="submit" value="등록" style="size: 10px;"/>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>
</form>
</c:if>

<hr style="margin-bottom: 50px;">

<c:if test="${replydata.size()!=0}">
<div class="list" style="margin-left:3%; padding: 5px 0;margin-top: 30px;width: 95%;"><h3>문의에 대한 답변</h3></div>
<table border="0" cellspacing=1  style="width: 95%; margin-left:3%;">
<tr bgcolor="#F6F6F6">
<td><c:forEach items="${replydata }" var="rr" varStatus="no">
<hr>
<div class="list">
	<div class="S_sec" style="width: 100px;">작성자 </div>
	<div class="S_sec" >${rr.pname }</div>
	<div style="text-align: right">
	<fmt:formatDate value="${rr.reg_date }" pattern="yy-MM-dd HH:mm:ss"/></div>
	<div style="clear: both;"></div>

</div>
	<div class="list" style="padding: 10px;">
		<div class="S_sec" style="padding: 10px 50px">
		<img alt="" src="../up/${rr.upfile }" width="500px"> 
		</div>
		<div style="clear: both;"></div>
	</div> 

	<div class="list" style="padding: 10px;">
	<div class="S_sec"><ct:conBr> ${rr.content }</ct:conBr></div>
	<div style="clear: both;"></div>
	</div>
<c:if test="${user_id eq 'admin' }">
<div class="list">
	<div style="float : right;">
	<a href="ResQnAModify?id=${rr.id }&seq=${rr.seq}&user_id=${user_id}&pp=my">수정</a>
	<a href="ResQnADelete?id=${rr.id }&seq=${rr.seq}&user_id=${user_id}&pp=my">삭제</a>
	</div>	
	<div style="clear: both;"></div>
</div>
</c:if>
</c:forEach>
</td>
</tr>
</table>
</c:if>
<hr style="margin-top: 50px;">
<div align="center">
	<a href="MyResQnAList">
	<input type="button" value="목록으로"></a>
</div>

