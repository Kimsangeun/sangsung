<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery-ui.js"></script>
    <script type="text/javascript">
	$(document).ready(function() {
		
		$('.H_menudiv').hover(function(){
			choice = $(this).index();
			$('.H_menudiv').each(function(){	
				$(this).stop();
				$(this).css("border-top", "0px solid #f33");
                $(this).animate({height:95+'px'},500);
            });
			$('.H_menudiv').eq(choice-1).css("border-top", "5px solid #f33");
			$('.H_menudiv').eq(choice-1).stop().animate({height:'300px',border_top: "5px inset #999"},500,'easeOutBounce');
		});
		
		$('.H_menudiv').mouseleave(function(){
			$('.H_menudiv').each(function(){	
				$(this).stop();
				$(this).css("border-top", "0px solid #f33");
                $(this).animate({height:95+'px'},500);
            });
		})
	});</script>

<style>
a{
color: black;
text-decoration: none;
}

a:hover{
text-decoration: underline;
}

.H_submenu{
	line-height:30px;
	width: 100%;
	height: 30px;
/*  	border-radius : 15px 15px 0 0;   */
	background-color: white;
/*  	border-top: 2px solid #f33; */
}
.H_submenu1{
	line-height:30px;
	width: 100%;
	height: 30px;
/* 	border-radius : 15px 15px 0 0; */
	background-color: white;
	border-bottom: 2px solid #f33;  
}

.H_menudiv{
    z-index:2;
    margin-left:20px;
    line-height:100px;
    overflow:hidden;
    position:absolute;
    width:8%;
    height: 95px;
    float: right;
}
#a1{
    right: 25%;
}
#a2{
    right: 33%;
}
#a3{
    right: 41%;
}
#a4{
    right: 49%;
}
#logo{
	
}

</style>

<div style="width: 50%; height: 100%; margin-left: 25%;">
	<div style=" height:100%; float: left;">
		<a href="../center/Main">
			<img id="logo" style="margin-top:10px;" height="90%" alt="여자친구" src="../opimg/파인애플3.png"></a>
	</div>
	<c:forEach begin="1" end="4" var="i">
		<div id="a${i }" align="center" class="H_menudiv" >
			<c:choose>
				<c:when test="${i==4 }">intro</c:when>
				<c:when test="${i==3 }">Reservation</c:when>
				<c:when test="${i==2 }">Download</c:when>
				<c:otherwise>Q&A</c:otherwise>
			</c:choose>
			<img style="width: 20px; height: 20px;" src="http://www.pkicon.com/icons/30984/Plus-Vector-256.png">
			<c:choose>
				<c:when test="${i==4 }">
					<a href="../sangeun/Intro_notice_listForm"><div class="H_submenu">공지사항</div></a>
					<a href="../sangeun/about_sangsung"><div class="H_submenu">회사소개</div></a>
					<a href="../sangeun/about_CEO"><div class="H_submenu1">경영진소개</div></a>
				</c:when>
				<c:when test="${i==3 }">
					<a href="../sangeun/VisitForm"><div class="H_submenu" >방문예약</div></a>
					<a href="../sangeun/Reser"><div class="H_submenu1" >출장예약</div></a>
				</c:when>
				<c:when test="${i==2 }">
					<a href="../center/Download"><div class="H_submenu1">제품설명</div></a>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${sessionScope.id==null }">
						<a href="../userJoin/Login"><div class="H_submenu">예약문의</div></a>
					</c:when>
					<c:when test="${sessionScope.id=='admin' }">
						<a href="../qna/ResQnAList"><div class="H_submenu">예약문의</div></a>
					</c:when>
					<c:otherwise>
						<a href="../qna/ResQnAInsert"><div class="H_submenu">예약문의</div></a>
					</c:otherwise>
					</c:choose>
					<a href="../qna/ProdQnAList"><div class="H_submenu1">제품문의</div></a>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
</div>