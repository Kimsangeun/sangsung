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
.S_sel{
float: left;
}
</style>

<div style="height: 730px;">
<br>
<h2>제품문의</h2>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >
<form action="ProdQnAList">
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
		<div class="S_sec" style="width: 30%" >제목</div>
		<div class="S_sec" style="width: 20%" >작성자</div>
		<div class="S_sec" style="width: 20%" >작성일</div>
		<div class="S_sec" style="width: 10%" >조회수</div>
		<div class="S_sec" style="width: 10%" >답변</div>		
		<div style="clear: both ;"></div>
</div>
<c:choose>
	<c:when test="${data.size()==0 }">
		<div style="text-align: center;margin-top: 30px;">내용이 없습니다.</div>
	</c:when>
	<c:otherwise>
	<c:forEach items="${data }" var="ee" varStatus="no">
<div class="S_tt">
		<div class="S_sec" style="width: 10%" >${no.index+start }</div>
		<div class="S_sec" style="width: 30%" >
		
		
		<c:choose>
		<c:when test="${param.listselect!=null }">
			<a href="ProdQnADetail?id=${ee.id }&seq=${ee.seq}&page=${page }&listselect=${listselect}">${ee.title }</a> 	
		</c:when>
		<c:otherwise>
			<c:choose>
			<c:when test="${param.select!=null }">
				<a href="ProdQnADetail?id=${ee.id }&seq=${ee.seq}&page=${page }&select=${param.select }&search=${param.search }">${ee.title }</a> 	
			</c:when>
			<c:otherwise>
				<a href="ProdQnADetail?id=${ee.id }&seq=${ee.seq}&page=${page }">${ee.title }</a> 	
			</c:otherwise>
			</c:choose>
		</c:otherwise>
		</c:choose>
		</div>
		<div class="S_sec" style="width: 20%" >${ee.pname }</div>
		<div class="S_sec" style="width: 20%" >${ee.reg_date }</div>		
		<div class="S_sec" style="width: 10%" >${ee.cnt }</div>		
		<div class="S_sec" style="width: 10%" >
			<c:if test="${ee.count==1 }">미완료</c:if>
			<c:if test="${ee.count!=1 }">완료</c:if>
		</div>
		<div style="clear: both ;"></div>
</div>
	</c:forEach>

<div style="text-align: center;" >
	<c:if test="${startPage >1 }">
	<c:when test="${param.listselect!=null }">
			<a href="ProdQnAList?id=${ee.id }&page=${page }&listselect=${listselect}">${ee.title }</a> 	
	</c:when>
	<c:otherwise>
		<a href="ProdQnAList?page=1&select=${select }&search=${search}">[처음]</a>
		<a href="ProdQnAList?page=${startPage-1 }&select=${select }&search=${search}"><</a>
	</c:otherwise>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==page }">
				[${i }]
			</c:when>
			<c:otherwise>
				<c:choose>
				<c:when test="${param.listselect != null }">
					<a href="ProdQnAList?page=${i }&listselect=${listselect}">${i }</a>
				</c:when>
				<c:otherwise>
					<a href="ProdQnAList?page=${i }&select=${select }&search=${search}">${i }</a>
				</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:forEach>
			<c:if test="${endPage<totalPage }">
				<c:choose>
				<c:when test="${param.listselect!=null }">
					<a href="ProdQnAList?page=${endPage+1 }&listselect=${listselect}">></a>
					<a href="ProdQnAList?page=${totalPage }&listselect=${listselect}">[마지막]</a>
				</c:when>
				<c:otherwise>
					<a href="ProdQnAList?page=${endPage+1 }&select=${select }&search=${search}">></a>
					<a href="ProdQnAList?page=${totalPage }&select=${select }&search=${search}">[마지막]</a>
				</c:otherwise>
				</c:choose>
		</c:if>
	</c:otherwise>
	</c:choose>
</div>

<hr style="margin: 20px 0;" size=4px" color="#ddd">

<form action="ProdQnAList">
	<div class="S_sel" style="margin:0 30%;"> 
		<select name="select">
			<option>작성자</option>
		</select>
		<input type="text" name="search"/>
		<input type="submit" value="검색"/>
	</div>
</form>

<div > 
	<a href="ProdQnAInsert"><input type="button" value="문의하기"></a>
</div>
</div>