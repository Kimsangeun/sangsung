<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="Intro_notice_ModifyReg" method="post">
    <input type="hidden" name="id" value="${data.id }">
    <input type="hidden" name="page" value="${param.page }">
    
    <script>
        link_detail = function(page,id) { 
            location.href= "Intro_notice_detail?=page"+page+"&id="+id
        }
        link_modify = function(page, id){
            location.href= "Intro_notice_ModifyForm?=page"+page+"&id="+id
        }

    </script>
    
    <div>
        <div class="S_tt">
            <div class="S_sec" style="width: 100px;">제목</div>
                <div class="S_sec">
                    <input type="text" name="title" value="${data.title }"/>
                </div>
            <div style="clear: both;"></div>
        </div>
    </div>
    <div style="clear: both;"></div>
    <div class="S_tt">
        <div class="S_sec">
            <textarea name="content" rows="20" cols="70" style="resize: none;">${data.content }</textarea>
        </div>
        <div style="clear: both;"></div>
    </div>
    <div>
        <input type="submit" value="수정" />
        <input type="button" value="초기화" onclick="location.href='Intro_notice_ModifyForm?page=${param.page }&id=${data.id}'" >
        <input type="button" value="뒤로 가기" onclick= "location.href= 'Intro_notice_detail?page=${param.page}&id=${data.id }'">
    <%--     "link_detail(${param.page },${data.id})" --%>
    </div>
    
    
    
<%--     
    <div>제목</div>
    <div><input type="text" name="title" value="${data.title }" /></div>
    <div>내용</div>
    <div><textarea name="content" rows="5" cols="30">${data.content }</textarea></div>
    <div>
        <input type="submit" value="수정" />
        <input type="button" value="초기화" onclick="location.href='Intro_notice_ModifyForm?page=${param.page }&id=${data.id}'" >
        <input type="button" value="뒤로 가기" onclick= "location.href= 'Intro_notice_detail?page=${param.page}&id=${data.id }'">
    "link_detail(${param.page },${data.id})"
    </div> --%>

</form>