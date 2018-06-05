<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style type="text/css">

a{
color: black;
}

.S_tt{
border-bottom: 2px solid #ddd;
width: 100%
}

.S_sec{
position : relative ;
float: left;
margin: 5px 0;
text-align: center;
}

</style>
<br>
<div style="height: 730px;">
<div><h2>내 예약문의내역</h2></div>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >


<div class="S_tt">
		<div class="S_sec" style="width: 10%;" >번호</div>
		<div class="S_sec" style="width: 40%;" >제목</div>
		<div class="S_sec" style="width: 30%;" >작성일</div>	
		<div class="S_sec" style="width: 20%;">답변</div>
		<div style="clear: both ;"></div>
</div>
<c:choose>
	<c:when test="${data.size()==0 }">
		<div>문의내역이 없습니다.</div>
	</c:when>
	<c:otherwise>
	<c:forEach items="${data }" var="ee" varStatus="no">
<div class="S_tt">
		<div class="S_sec" style="width: 10%" >${no.index+start }</div>
		<div class="S_sec" style="width: 40%" >
			<a href="ResQnADetail?id=${ee.id }&seq=${ee.seq}&page=${page }&user_id=${user_id}">${ee.title }</a> 
		</div>
		<div class="S_sec" style="width: 30%" >${ee.reg_date }</div>	
		<div class="S_sec" style="width: 20%">
			<c:if test="${ee.count==1 }">미완료</c:if>
			<c:if test="${ee.count!=1 }">완료</c:if>
		</div>
		<div style="clear: both ;"></div>
</div>
	</c:forEach>
	
<div style="text-align: center;" >
	<c:if test="${startPage >1 }">
		<a href="MyResQnAList?page=1">[처음]</a>
		<a href="MyResQnAList?page=${startPage-1 }"><</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==page }">
				[${i }]
			</c:when>
			<c:otherwise>
				<a href="MyResQnAList?page=${i }">${i }</a>
			</c:otherwise>
		</c:choose>
		<c:if test="${endPage<totalPage }">
			<a href="MyResQnAList?page=${endPage+1 }">></a>
			<a href="MyResQnAList?page=${totalPage }">[마지막]</a>
		</c:if>
	</c:forEach>
	</c:otherwise>
	</c:choose>
</div>


	<hr style="margin-top: 50px;">
	<div align="center">
	<a href="ResQnAInsert"><input type="button" value="예약문의"></a>
	</div>
</div>