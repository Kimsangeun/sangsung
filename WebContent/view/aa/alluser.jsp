<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="DeleteUser" method="get">
<div style="width: 100%; height: 730px;">
<div><h2>회원 관리</h2></div>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >

<div><h2>회원 탈퇴</h2></div>
<div>
<div style="width: 100%; border-bottom: 2px solid #ddd;">
	<div style="width: 40%; float: left;">아이디</div>
	<div style="width: 10%; float: left;">이름</div>
	<div style="width: 10%; float: left;">PW</div>
	<div style="width: 40%; float: left;">주소</div>
	<div style="clear: both;"></div>
</div>
<c:choose>
	<c:when test="${livedata.size()!=0 }">
		<c:forEach items="${livedata}" var="kk">
			<div style="width: 100%; border-bottom: 2px solid #ddd;">
				<div style="width: 40%; float: left;"><input type="checkbox" name="box" value="${kk.id }">${kk.id}</input></div>
				<div style="width: 10%; float: left;">${kk.name }</div>
				<div style="width: 10%; float: left;">${kk.pw }</div>
				<div style="width: 40%; float: left;">${kk.address }</div>
				<div style="clear: both;"></div>
			</div>
		</c:forEach>	
	</c:when>
	<c:otherwise>
		데이터가 없습니다.
	</c:otherwise>
</c:choose>
	<div align="right" style="width:100%; border-bottom: 2px soild #dd;">
	<c:choose>
		<c:when test="${param.deathpage==null}">
			<c:set var="death" value="1"/>
		</c:when>
		<c:otherwise>
			<c:set var="death" value="${param.deathpage }"/>
		</c:otherwise>
	</c:choose>
		<div align="center">
			<c:if test="${lstart!=1 }">
				<a href="KickMember?livepage=${i-1}&deathpage=${death}">[<]</a>
			</c:if>
			<c:forEach begin="${lstart}" end="${lend }" var="i">
				<c:choose>
					<c:when test="${i==lpage }">
						[${i }]
					</c:when>
					<c:otherwise>
						<a href="KickMember?livepage=${i}&deathpage=${death}">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${lend!=llast}">
				<a href="KickMember?livepage=${i+1}&deathpage=${death}">[>]</a>
			</c:if>
		</div>
		<input type="submit" value="회원 탈퇴">
	</div>
</form>
</div>
<div><h2>탈퇴 취소</h2></div>
<div>
	<div style="width: 100%; border-bottom: 2px solid #ddd;">
		<div style="width: 40%; float: left;">아이디</div>
		<div style="width: 10%; float: left;">이름</div>
		<div style="width: 10%; float: left;">PW</div>
		<div style="width: 40%; float: left;">주소</div>
		<div style="clear: both;"></div>
	</div>
<form action="DeleteCancle" method="get">
<c:choose>
	<c:when test="${deathdata.size()!=0 }">
		<c:forEach items="${deathdata}" var="kk">
			<div style="width: 100%; border-bottom: 2px solid #ddd;">
				<div style="width: 40%; float: left;"><input type="checkbox" name="box" value="${kk.id }">${kk.id}</input></div>
				<div style="width: 10%; float: left;">${kk.name }</div>
				<div style="width: 10%; float: left;">${kk.pw }</div>
				<div style="width: 40%; float: left;">${kk.address }</div>
				<div style="clear: both;"></div>
			</div>
		</c:forEach>	
	</c:when>
	<c:otherwise>
		데이터가 없습니다.
	</c:otherwise>
</c:choose>
	<div align="right" style="width:100%; border-bottom: 2px soild #dd;">
		<div align="center">
		<c:choose>
			<c:when test="${param.livepage==null}">
				<c:set var="live" value="1"/>
			</c:when>
			<c:otherwise>
				<c:set var="live" value="${param.livepage }"/>
			</c:otherwise>
		</c:choose>
		<c:if test="${dstart!=1 }">
				<a href="KickMember?livepage=${live}&deathpage=${i-1}">[<]</a>
		</c:if>
			<c:forEach begin="${dstart}" end="${dend }" var="i">
				<c:choose>
					<c:when test="${i==dpage }">
						[${i }]
					</c:when>
					<c:otherwise>
						<a href="KickMember?livepage=${live}&deathpage=${i}">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${dend!=dlast}">
				<a href="KickMember?livepage=${live}&deathpage=${i+1}">[>]</a>
			</c:if>
		</div>
		<input type="submit" value="탈퇴 삭제">
	</div>
</form>
</div>