<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <style>
    .detail{
        margin: 10px;            
    }
</style>
-->

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
</style>

<script>

link_modify = function(page,id){
    location.href="Intro_notice_ModifyForm?page="+page+"&id="+id
}

link_delete = function(page,id){
     if (confirm("정말 삭제하시겠습니까??") == true){    //확인
            location.href="Intro_notice_DeleteReg?page="+page+"&id="+id
     }else{   //취소
         return false;
     }
}

</script>
<div>
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
    <fmt:formatDate value="${data.reg_Date }" pattern="yy-MM-dd HH:mm:ss"/>
    </div>
    </div>
    <div style="clear: both;"></div>
</div>
<hr>
<div class="list" style="height: 300px;padding: 10px;">
    <div><ct:conBr>${data.content }</ct:conBr></div>
    <div style="clear: both;"></div>
</div>
<hr>
<%-- 
<div>디테일 페이지 입니다.
    <div class="detail" style="background-color: pink">text_id 입니다 : ${data.id}</div>
    <div class="detail" style="background-color: yellow">글 제목 입니다 : ${data.title }</div>
    <div style="background-color: white;">조회수 입니다 : ${data.cnt}</div>
    <div>날짜 : <fmt:formatDate value="${data.reg_Date }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
    
    <div style="background-color: green;">내용내용내용 ${data.content }</div>
     --%>

    
    <div>
        <input type="button" value="목록" onclick="location.href='Intro_notice_listForm?page=${param.page}'">

<c:choose>
    <c:when test="${sessionScope.id=='admin' }">
            <input type="button" value="수정" onclick="location.href='Intro_notice_ModifyForm?page=${param.page}&id=${data.id}'" >
        <input type="button" value="삭제" onclick="link_delete(${param.page},${data.id})" >
    </c:when>
    <c:otherwise>
            관리자 아니면 수정 삭제 안됨
    </c:otherwise>
</c:choose>
    </div>
    <%-- ?page=${param.page } --%>
</div>