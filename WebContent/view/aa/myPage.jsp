<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
a:hover{
text-decoration: none;
}
.links {
  *zoom: 1;
  padding: 50px;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  text-align: center;
  background:#FFCD12;
}
.links:before, .links:after {
  content: "";
  display: table;
}
.links:after {
  clear: both;
}


.link-effect-12 a {
  color: #fff;
  margin: 0 20px;
  padding: 10px;
  position: relative;
  overflow: hidden;
  display: inline-block;
  -moz-transition: ease-out 0.3s 0.1s;
  -o-transition: ease-out 0.3s 0.1s;
  -webkit-transition: ease-out 0.3s;
  -webkit-transition-delay: 0.1s;
  transition: ease-out 0.3s 0.1s;
  font-size: 20px;

}
.link-effect-12 a span::before {
  width: 100%;
  height: 2px;
  background: white;
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  -moz-transition: 0.1s;
  -o-transition: 0.1s;
  -webkit-transition: 0.1s;
  transition: 0.1s;
  -moz-transform: translateX(-100%);
  -ms-transform: translateX(-100%);
  -webkit-transform: translateX(-100%);
  transform: translateX(-100%);
}
.link-effect-12 a span::after {
  width: 100%;
  height: 2px;
  background: white;
  content: "";
  position: absolute;
  right: 0;
  bottom: 0;
  -moz-transition: 0.1s 0.2s;
  -o-transition: 0.1s 0.2s;
  -webkit-transition: 0.1s;
  -webkit-transition-delay: 0.2s;
  transition: 0.1s 0.2s;
  -moz-transform: translateX(100%);
  -ms-transform: translateX(100%);
  -webkit-transform: translateX(100%);
  transform: translateX(100%);
}
.link-effect-12 a::before {
  width: 2px;
  height: 100%;
  background: white;
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  -moz-transform: translateY(100%);
  -ms-transform: translateY(100%);
  -webkit-transform: translateY(100%);
  transform: translateY(100%);
  -moz-transition: 0.1s 0.3s;
  -o-transition: 0.1s 0.3s;
  -webkit-transition: 0.1s;
  -webkit-transition-delay: 0.3s;
  transition: 0.1s 0.3s;
}
.link-effect-12 a::after {
  width: 2px;
  height: 100%;
  background: white;
  content: "";
  position: absolute;
  right: 0px;
  bottom: 0;
  -moz-transform: translateY(-100%);
  -ms-transform: translateY(-100%);
  -webkit-transform: translateY(-100%);
  transform: translateY(-100%);
  -moz-transition: 0.1s 0.1s;
  -o-transition: 0.1s 0.1s;
  -webkit-transition: 0.1s;
  -webkit-transition-delay: 0.1s;
  transition: 0.1s 0.1s; 
}
.link-effect-12 a:hover {
  color: white;
}
.link-effect-12 a:hover::before, .link-effect-12 a:hover::after {
  -moz-transform: translateX(0);
  -ms-transform: translateX(0);
  -webkit-transform: translateX(0);
  transform: translateX(0);
}
.link-effect-12 a:hover span::before, .link-effect-12 a:hover span::after {
  -moz-transform: translateX(0);
  -ms-transform: translateX(0);
  -webkit-transform: translateX(0);
  transform: translateX(0);
}
</style>
<div style="height:730px">
<br>
<h2>마이페이지</h2>
<hr size="5px" color="#ddd">
<div style="top: 35%; left:21%; position: absolute;" align="center">

<%-- 		<c:choose>
			<c:when test="${sessionScope.id!='admin' }">
				<a href="../userJoin/Chk_Pw"><img width="200px" height="200px" alt="개인정보 수정" src="http://g90179.com/data/file/GICOM/1982194151_3HYU8Enz_a096f52fe99bc20ed72500ef45db607dc1747804.png" /></a>
			</c:when>
			<c:otherwise>
				<a href="KickMember"><img width="200px" height="200px" alt="모든 회원 정보" src="http://g90179.com/data/file/GICOM/1982194151_3HYU8Enz_a096f52fe99bc20ed72500ef45db607dc1747804.png" /></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sessionScope.id!='admin' }">
					<a href="../qna/MyResQnAList"><img  width="200px" height="200px" alt="내예약문의" src="data:image/png;base64,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" /></a>
				</c:when>
				<c:otherwise>
					<a href="../qna/ResQnAList"><img  width="200px" height="200px" alt="내예약문의" src="data:image/png;base64,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" /></a>
				</c:otherwise>
			</c:choose>
		
		 --%>
		 <section class="links">
		 <nav class="link-effect-12" id="link-effect-12">
			<c:choose>
				<c:when test="${sessionScope.id!='admin' }">
					<a href="../userJoin/Chk_Pw"><span>개인 정보</span></a>
				</c:when>
				<c:otherwise>
					<a href="KickMember"><span>모든 회원 정보</span></a>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${sessionScope.id!='admin' }">
					<a href="../qna/MyResQnAList"><span>내 예약 문의보기</span></a>
				</c:when>
				<c:otherwise>
					<a href="../qna/ResQnAList"><span>예약문의보기</span></a>
				</c:otherwise>
			</c:choose>
		
		
<!-- 			<a href="../sangeun/MyReserList"><img width="200px" height="200px" alt="내출장예약" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOYAAACWCAMAAAD9lgRIAAAAY1BMVEX///8dQod/lLvG0OFWcaVHZZ2OocPj5/Dx8/fU2+i4xNmquNKcrMorTo9yibRkfa05WpYhRons7/X4+fsvUZCls89geqtadafO1uXa4OtQbKK/yt2UpcaGmr93jbept9FBYJrgtvnJAAAE2klEQVR4nO2ciZKjIBBAgyDigZqYzLnH/P9XLg2oGJ24RkvR6Ve1W8Aq2y9yGYOnE4IgCIIgCIIgCILsi5QopM2EkOEmHUOaRCYTQLrYJMBlQM2dawYuryBwtpkrZD5MOteauclo/7JzYrK1xhhkEYKtNcZATdTcpWZBLb8hJ22mhExu0jctk5pMAemP+hy6G80myKcmFNT0h06Q75niZjMpZN5M+gLpTJgMhXQyXIOvzA8SNf0BNVerYQVQc7UaVgA1V6thBVBztRpWoBdkysbIBmu4va0Z9lR6mnzqzaUpSsiZrhn3RBbSfDsTcv5cM/BpLKP5KuHvs79f8PXiFtEYol+DxV/PZUbahnShsJZmYU1yGz9hC5bWfF0oroVZWDMU4ydswWzNm2tZXpaKa2HmaiaupXxfLK6FmakJy4J2PvF3HTRPM7q6F9PbWXOm5qV0LbPxEzZjjmYVupZe34zNCfDFtfT7KX1PsxJjVPbIP64lqwYq94ee5vgdCh848MvTZUHN05p/3aIy2iT4/+dZzc9OkdfDD9AL8haOATchnWXBHjX/i+6y4Kialy/yAzSrj/vuekjNwjUsjqrZWRZ8VAfV/OVaqmXBMTVT1/Ia7fUZygif7oQp4ydq2ISJQcbSsTRPE46oydwmmz5TwzZMClIkF+dO+tcTNWzFpCA/z45nfdY+NS/BMPB91m9yToRdAhXf1uAjvSBjMgwcBIapYGZZ8G0NPjJFs9KTSVopz6/L9zX4yBRNagfY6uUaPajBR3pBimSY9/aLhb/V+6MafGRKkK9WM+x883M0zQpWQOeX291Tr6NpvpHrn6T/dezRNN/juTVsx/wgUdMfUHO1GlYANVerYQVQc7UaVuAHaV5Hn/U9Yjea80FNP0DNI2kiCIIgCIIgCIIgCIIgg6S8t919oIjS6MR5d8NmzNjwA+3NiDiThEgW9LcDMcJsKqbRfZE606jBrhu78yZKeab1KCF+bVR1fuKc63z9WpW4deLww5FSbz1tNeGXbBIubaup3y5ImPBPU8Uj84TSJJAm1GbHFG2cclsCTq2m2bQQOZoBNIqSkND+Cox9+7+ujgrWdiJR6pdBUg7kjqYKOYxPVBIpHM1YlQa6BdSaqqQQ+nPKvNNULa/eiBgS0hRTRzM3hyT6cjaaAVxJ00qtZmZfjVmSAs7PqEeDkOqaYSLg93mF7ZwaV7M20w2z0dQJ6mpy+zHBv/jWN+sLUo8davgEso5mCQdGXU0JjxEEvLa21kyNmpDq41KFeX/q2ZI4188lw9xs4u8OQXCpMjP45LoXN5pmwKoV4U8kSUhPUQGvA/Ctb2rcJtbXVJeHFJyRTgse0GzmptwMW8ybp0amhUITzUwqbnoYwNTlUesCu+NEv0vlkeYpgZcfSEj51Td724rZnaZxElnB8rRTpMblQs8qJXNWQepzM8PrLjUdmqIcBqbUHWldIs793kquNFXjTTlnzJkkm37WFCnDPJGk5LzV7LyYz6NZc+BWwrnAd5OkphUO6wVgq9lpHB412oFOZEKVjAV8SLNFD0ywoGg1zUKxXSz6w8Ox4qGmGpi4nmqH+qZfQ9AszZqdaLrjbBdH80Gn+yGajKXeawrqcD8HNMNq7B419AYZUHXx77sgBEEQBEEQBEEQBOnyDxvWKoteX89AAAAAAElFTkSuQmCC" /></a>
			<a href="../sangeun/MyVisitList"><img width="200px" height="200px" alt="내방문예약" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWOX0AWpLoLSozbG0cFv8K3tySQpyp8R3GARmsGc7a5UUyCmsG" /></a>
		 -->
		 </nav>
		 </section>
	<section class="links">	
	<nav class="link-effect-12" id="link-effect-12">
			<a href="../sangeun/MyReserList"><span>내 출장예약 정보보기</span></a>
			<a href="../sangeun/MyVisitList"><span>내 방문 예약 정보 보기</span></a>
		</nav>
</section>
</div>

</div>