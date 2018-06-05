
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">

.S_tt{
border-bottom: 2px solid black;
width: 100%
}

.S_sec{
position : relative ;
float: left;
margin: 5px 0;
}

</style>

<script>
link_write = function(page){
	
	location.href="Intro_notice_InsertForm?page="+page;
}
</script>

<div class="S_tt">
		<div class="S_sec" style="width: 10%" >번호</div>
		<div class="S_sec" style="width: 50%" >제목</div>
		<div class="S_sec" style="width: 30%" >작성일</div>
		<div class="S_sec" style="width: 10%" >조회수</div>		
		<div style="clear: both ;"></div>
</div>

<c:choose>
	<c:when test="${data.size()==0 }">
		<div>내용이 없습니다.</div>
	</c:when>

	<c:otherwise>
		<c:forEach items="${data }" var="ee" varStatus="no">
		<div class="S_tt">
			<div class="S_sec" style="width: 10%" >${no.index+start }</div>
			<div class="S_sec" style="width: 50%" >
				<a href="Intro_notice_detail?id=${ee.id}">${ee.title}</a>
			</div>
			<div class="S_sec" style="width: 30%"><fmt:formatDate value="${ee.reg_Date }" pattern="yyyy-MM-dd"/></div>
			<div class="S_sec" style="width: 10%">${ee.cnt }</div>
		</div>
		</c:forEach>
		
		
		<div class="s_bott">
			<c:if test="${startPage >1}">
					<a href="Intro_notice_listForm?page=1">[처음]</a>
					<a href="List?page=${startPage-1}"><</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:choose>
					<c:when test="${i==page }">
							[${i }]
					</c:when>
					<c:otherwise>
						<a href="Intro_notice_listForm?page=${i }">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
					
			<c:if test="${endPage<totalPage }">
				<a href="Intro_notice_listForm?page=${endPage+1}">></a>
				<a href="Intro_notice_listForm?page=${totalPage }">[마지막]</a>
			</c:if>
		</div>	
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${sessionScope.id=='admin' }">
			<div class="bott"><input type="button" value="글쓰기" onclick="link_write(${page})" ></div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

</div>