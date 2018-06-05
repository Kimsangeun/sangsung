<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">

*{
margin: 0;
padding: 0;
}
a{
color: black;
text-decoration: none;
text-align: left;
}
.S_sec{
float: left;
padding : 15px 0;
margin-left: 10px;
}
.S_sec1{
float: left;
padding : 15px 0;
background: #ddd;
width: 100px;
text-align: center;
}

.S_tt{

}
</style>

<script>
 function fileDelete(){
	 alert('함수가 실행된다.!');
	 if(confirm('파일을 삭제하시겠습니까?\n삭제된 파일은 복구할 수 없습니다.')){
		 var frm = document.frm;
		 frm.action="ResQnAFileDelete";
		 frm.submit();
	 }
	 
 }
 
</script>
<div style="height: 750px;">
<br>
<h2>예약문의수정</h2>
<hr  style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >

<form name="frm" action="ResQnAModifyReg" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${data.id }"/>
<input type="hidden" name="seq" value="${data.seq }"/>
<input type="hidden" name="pname" value="${data.pname }"/>
<input type="hidden" name="user_id" value="${user_id }"/>
<input type="hidden" name="pp" value=${param.pp }/>
<div>

<hr>
<c:choose>
<c:when test="${data.seq==0 }">
	<div class="S_tt">
		<div class="S_sec1">제목</div>
		<div class="S_sec">
		<input type="text" name="title" value="${data.title }"  style="width : 200%;"/>
		</div>
		<div style="clear: both;"></div>
	</div>
</c:when>
<c:otherwise>
	<div class="S_tt">
		<div class="S_sec1">사진파일</div>
		<div class="S_sec">
		<c:choose>
		<c:when test="${data.upfile!='' }">
			${data.upfile }
			<input type="hidden" name="upfile" value="${data.upfile }"/>
			<input type="button" value="파일삭제" onclick="fileDelete()" >
		</c:when>
		<c:otherwise>
		<input type="file" name="upfile"/>
		</c:otherwise>
		</c:choose>
		</div>
	</div>
</c:otherwise>
</c:choose>
<hr>
<div style="clear: both;"></div>
	<div class="S_tt">
		<div style="padding : 15px 0;">
		<textarea name="content" rows="5" cols="30" style="margin-left:5%; resize: none; width: 90%; height: 300PX;">${data.content }</textarea>
		</div>
		<div style="clear: both;"></div>
	</div>
 <hr style="margin-bottom: 10px;">
	<div class="S_tt" >
		<a href="ResQnAList">목록</a> |
		<a href="ResQnADetail?id=${data.id }&seq=${param.seq}">취소</a>
		<input type="submit" value="등록" style="margin-left: 85%;"/> 
	</div>
</div>
</form>
</div>