<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var currIndex =0;

function btn_click(){
	if(confirm("왠일인지 삭제해도 될까요?")){
		alert('삭제를 시작합니다.');
		location.href= "Delete?id=${id}&textid=${data.id}";
	}
}


function roll(pos){
	var loc=-currIndex*100;

	if(pos == currIndex){
        return;
    }
	var imgs = document.getElementById("imgs");
	var btn = document.getElementsByClassName("H_btn");
	for(k in btn){
		btn.item(k).style.background='#fff';
	}
	btn.item(pos).style.background='#0f0';
	
	var e = setInterval(function(){
        if(pos>currIndex){
            loc-=1;
        }
        else{
            loc+=1;
        }
        imgs.style.left=loc+"%";
        if(loc == -pos*100){
                currIndex=pos;
                clearInterval(e);
         } 
    },10)
    
}
</script>
<style>
	.H_photos{
		width: 20%;
		height: 100%;
		float: left;
		display: block;
	}
	.H_btn{
		position:relative;
		margin-top:10px;
		margin-left:20px;
		width:20px;
		height:20px;
		border-radius: 10px;
		border: 1px solid white;
		background-color:white;
		float: left;
	}
</style>
<div style="width: 100%; height: 98%; border:5px outset #224;">
	<div style="width: 50%; height: 5%; line-height: 50px; position: relative; float: left;"><strong>　[TITLE]&nbsp;${data.title }</strong></div>
	<div style="width: 50%; height: 5%; line-height: 50px; position: relative; float: left;">[DATE]&nbsp; ${data.time }┃
	<c:choose>
		<c:when test="${param.page!=null }">
			<a href='ListView?id=${id }&rang=${data.rang }&cate=${data.cate}&page=${param.page}'><input type="button" value="목록"></a>┃
		</c:when>
		<c:otherwise>
			<a href='ListView?id=${id }&rang=${data.rang }&cate=${data.cate}'><input type="button" value="목록"></a>┃
		</c:otherwise>
	</c:choose>
		<c:if test="${id=='admin' }">
			<input type="button" value="삭제" onclick="btn_click()">┃
			<a href="AdjustForm?id=${id }&textid=${data.id}&page=${param.page}"><input type="button" value="수정"></a>
		</c:if>
	</div>
	<hr size=10 color="#224"/>
	<br>
	<hr size=10 color="#224"/>
	<div style="width: 100%; height: 3%;"><strong>　[파일 드라이버]&nbsp; <a href="FileDown?file=${data.driver }">${data.driver }</a>┃</strong></div>
	<div style="width: 100%; height: 35%; background-color: #224;">
		<div style="margin-left:25%; width: 50%; height: 33%; overflow: hidden; position:absolute;">
			<div id="imgs" style="width:500%; height:98%; float: left; margin-top: 5px; position:absolute;">
				<c:forEach var="pp" items="${data.photo.split(',')}">
					<img class="H_photos" alt="" src="../photo/${pp }">
				</c:forEach>
			</div>
		</div>
		<div style="margin-left:25%; width: 50%; height: 12%;">
			<div style="margin-left: 30%;">
			<c:forEach var="pp" items="${data.photo.split(',')}" varStatus="no">
				<c:choose>
					<c:when test="${no.index==0 }">
						<div class="H_btn" style="background-color: #0f0;" onclick="roll(${no.index})"></div>
					</c:when>
					<c:otherwise>
						<div class="H_btn" onclick="roll(${no.index})"></div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</div>
		</div>
	</div>
	<div align="center" style="width: 70%; height: 52%; margin-left:15%; border-right:3px outset #224; border-left:3px outset #224;" >${data.content }</div>
</div>