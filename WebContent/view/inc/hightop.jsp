<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	.H_icon{
		width: 1%;
		height: 80%;
	}	
</style>
<div id="k" align="right" style="line-height:100%; margin-top:1%; width:95%; height:100%; position: relative; right: 2%">
	<c:choose>
		<c:when test="${sessionScope.id!=null }">
			<a href="../userJoin/Mypage"><img class="H_icon" src="http://m.thecoco.co.kr/SkinImg/img/footer_icon02.png"> 마이페이지</a>
			<a href="../userJoin/Logout"><img class="H_icon" src="http://download.seaicons.com/icons/icons8/windows-8/512/User-Interface-Logout-icon.png">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="../userJoin/Login"><img class="H_icon" src="http://download.seaicons.com/icons/icons8/windows-8/512/User-Interface-Login-icon.png"> 로그인</a>
			<a href="../userJoin/Join"><img class="H_icon" src="http://m.ariaskin.co.kr/m-um/icon_join.png">회원가입</a>
		</c:otherwise>
	</c:choose>	
</div>