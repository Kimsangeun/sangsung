<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">


</script>
<style type="text/css">
a{
color: black;
}

.S_sec{
float: left;
margin-left: 10px;
padding: 8px 0;
}

.S_sec1{
float: left;
width: 100px;
text-align: center;
background : #d0d0d0;
padding: 8px 0;
}

.list{

}
</style>
<br>
<h2>제품문의</h2>
<div  style="float:right;" >
<c:choose>
<c:when test="${param.select!=null }">
	<a href="ProdQnAList?select=${param.select }&search=${param.search }" style="color: black;text-decoration: none;text-align: left;" >목록</a>
</c:when>
<c:otherwise>
	<a href="ProdQnAList" style="color: black;text-decoration: none;text-align: left;" >목록</a>
</c:otherwise>
</c:choose>
</div>
<div style="clear: both;"></div>
<hr style=" margin-bottom: 30px;" size="5px" color="#ddd" >
 

<c:if test="${replyCount==1 }">

</c:if>
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
	<div class="S_sec1" style="width: 100px;">작성일</div>
	<div class="S_sec">
	<fmt:formatDate value="${data.reg_date }" pattern="yy-MM-dd HH:mm:ss"/>
	</div>
	</div>
	<div style="clear: both;"></div>
</div>
<hr>
<div class="list">
	<div class="S_sec1">작성자</div>
	<div class="S_sec">${data.pname }</div>
	<div style="clear: both;"></div>
</div>
<hr>
<div class="list" style="padding: 10px;">
		<div class="S_sec" style="padding: 10px 50px">
		<img alt="" src="../up2/${data.upfile }" width="50%"> 
		</div>
		<div style="clear: both;"></div>
	</div> 
<div class="list" style="height: 300px;padding: 10px;">
	<div><ct:conBr>${data.content }</ct:conBr></div>
	${user_id }
	<div style="clear: both;"></div>
	
</div>

</div>
<c:if test="${replyCount==1 }">
<div class="list">
	<div style="float : right;">
	<a href="ProdQnACheck?id=${data.id }&seq=${data.seq}&reg=modi">수정</a> | 
	<a href="ProdQnACheck?id=${data.id }&seq=${data.seq}&reg=dele">삭제</a>
	</div>
	<div style="clear: both;"></div>
</div>
</c:if>




<!-- 관리자일때 댓글달기 -->
<c:if test="${user_id eq 'admin' }">
<hr style="margin-bottom: 50px;">

<form action="ProdQnAReplyReg" method="post" enctype="multipart/form-data">
<input type="hidden" name="pname" value="관리자"/>
<input type="hidden" name="user_id" value="${user_id }"/>
<input type="hidden" name="id" value="${param.id }"/>

<div>
	<div class="list">
		<div align="center">
		<textarea name="content" rows="10" cols="90" style="resize: none; width: 90%;">내용을 입력하세요.</textarea>
		</div>
		<div style="clear: both;"></div>
		<div style="float: left;">사진첨부</div>
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

<hr>

<!-- 댓글들 -->
<c:if test="${replydata.size()!=0}">
<div class="list" style="margin-left:3%; padding: 5px 0;margin-top: 30px;width: 95%;"><h3>문의에대한답변</h3></div>
<table border="0" cellspacing=1 style="width: 95%; margin-left:3%;" >
<tr bgcolor="#F6F6F6">
<td>
<c:forEach items="${replydata }" var="rr" varStatus="no">
<hr>
<div class="list" >
	<div class="S_sec" style="width: 100px;">작성자</div>
	<div class="S_sec" >${rr.pname }</div>
	<div style="text-align: right">
	<fmt:formatDate value="${rr.reg_date }" pattern="yy-MM-dd HH:mm:ss"/></div>
	<div style="clear: both;"></div>

</div>
<c:choose>
<c:when test="${false}">
<form name="frm" method="post" enctype="multipart/form-data">
	<div class="list">
		<div class="S_sec" style="width: 100px;">사진첨부</div>
		<div class="S_sec">
		<c:choose>
		<c:when test="${rr.upfile!='' }">
			<input type="hidden" value="${rr.upfile }"/>${rr.upfile }
			<input type="button" onclick="fileDelete()" value="파일삭제">
		</c:when>
		<c:otherwise>
			<input type="file" name="upfile"/>
		</c:otherwise>
		</c:choose>
		</div>
	</div>
	<div class="list">
		<div class="S_sec" style="padding-left: 20px;">
		<textarea name="content" rows="10" cols="90" style="resize: none;">${rr.content }</textarea>
		</div>
	</div>
</form>
</c:when>
<c:otherwise>
	<div class="list" >
		<div class="S_sec" style="padding: 10px 50px">
		<img alt="" src="../up/${rr.upfile }" width="500px"> 
		</div>
		<div style="clear: both;"></div>
	</div> 

	<div class="list" style="padding: 10px;">
	<div class="S_sec"><ct:conBr> ${rr.content }</ct:conBr></div>
	<div style="clear: both;"></div>
	
	</div>
</c:otherwise>
</c:choose>
<div class="list">
</div>
<c:if test="${user_id eq 'admin' }">
<div class="list" style="width: 95%; margin-left:3%;"  align="center" >
	<div style="float : right;">
	<a href="ProdQnAModify?id=${rr.id }&seq=${rr.seq}">수정</a> | 
	<a href="ProdQnADelete?id=${rr.id }&seq=${rr.seq}">삭제</a>
	</div>
	<div style="clear: both;"></div>
</div>
</c:if>
</c:forEach>
</td></tr>
</table>
</c:if>
<hr style="margin-top: 50px;" size="3px" color="#ddd">
<div class="list" style="text-align: left;">
<c:choose>
<c:when test="${param.listselect !=null }">
	<a href="ProdQnAList?listselect=${param.listselect }" style="color: black;text-decoration: none;text-align: left;" >목록</a>	
</c:when>
<c:otherwise>
	<c:choose>
	<c:when test="${param.select!=null }">
		<a href="ProdQnAList?select=${param.select }&search=${param.search }" style="color: black;text-decoration: none;text-align: left;" >목록</a>
	</c:when>
	<c:otherwise>
		<a href="ProdQnAList" style="color: black;text-decoration: none;text-align: left;" >목록</a>
	</c:otherwise>
	</c:choose>
</c:otherwise>
</c:choose>
</div>
</div>
