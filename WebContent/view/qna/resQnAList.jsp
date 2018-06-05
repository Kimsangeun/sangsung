<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
a{
color: black;
}

.S_tt{
border-bottom: 2px solid #ddd;
width: 100%;
}

.S_sec{
position : relative ;
float: left;
margin: 5px 0;
text-align: center;
}

</style>
<div style="height: 730px;">
<br>
<div><h2>예약문의내역</h2></div>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >
<form action="ResQnAList">
<div class="S_tt" >
	<select name="listselect">
		<option>전체보기</option>
		<option value="완료">완료된답변보기</option>
		<option value="미완료">미완료답변보기</option>
	</select>
	<input type="submit" value="검색">
</div>
</form>

<div class="S_tt">
		<div class="S_sec" style="width: 10%" >번호</div>
		<div class="S_sec" style="width: 40%" >제목</div>
		<div class="S_sec" style="width: 20%" >작성자</div>
		<div class="S_sec" style="width: 20%" >작성일</div>
		<div class="S_sec" style="width: 10%" >답변</div>
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
		<div class="S_sec" style="width: 40%" >
		<c:choose>
		<c:when test="${param.listselect!=null }">
			<a href="ResQnADetail?id=${ee.id }&seq=${ee.seq}&page=${page }&listselect=${listselect}">${ee.title }</a> 	
		</c:when>
		<c:otherwise>
			<a href="ResQnADetail?id=${ee.id }&seq=${ee.seq}&page=${page }">${ee.title }</a> 
		</c:otherwise>
		</c:choose>
		</div>
		<div class="S_sec" style="width: 20%" >${ee.pname }</div>
		<div class="S_sec" style="width: 20%" >${ee.reg_date }</div>
		<div class="S_sec" style="width: 10%">
			<c:if test="${ee.count<=1 }">미완료</c:if>
			<c:if test="${ee.count>1 }">완료</c:if>
		</div>
		<div style="clear: both ;"></div>
</div>
	</c:forEach>
	
<div style="text-align: center;" >
	<c:if test="${startPage >1 }">
		<c:choose>
		<c:when test="${param.listselect != null }">
			<a href="ResQnAList?page=1&listselect=${param.listselect }">[처음]</a>
			<a href="ResQnAList?page=${startPage-1 }&listselect=${param.listselect }"><</a>
		</c:when>
		<c:otherwise>
			<a href="ResQnAList?page=1">[처음]</a>
			<a href="ResQnAList?page=${startPage-1 }"><</a>
		</c:otherwise>
		</c:choose>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==page }">
				[${i }]
			</c:when>
			<c:otherwise>
				<c:choose>
				<c:when test="${param.listselect != null }">
					<a href="ResQnAList?page=${i }&listselect=${param.listselect}">${i }</a>
				</c:when>
				<c:otherwise>
					<a href="ResQnAList?page=${i }">${i }</a>
				</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:forEach>
		<c:if test="${endPage<totalPage }">
			<c:choose>
			<c:when test="${param.listselect != null }">
				<a href="ResQnAList?page=${endPage+1 }&listselect=${param.listselect}">></a>
				<a href="ResQnAList?page=${totalPage }&listselect=${param.listselect}">[마지막]</a>
			</c:when>
			<c:otherwise>
				<a href="ResQnAList?page=${endPage+1 }">></a>
				<a href="ResQnAList?page=${totalPage }">[마지막]</a>
			</c:otherwise>
			</c:choose>

		</c:if>
	</c:otherwise>
	</c:choose>
</div>
</div>