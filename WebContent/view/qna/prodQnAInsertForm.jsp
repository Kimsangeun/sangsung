<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style type="text/css">

*{
margin: 0;
padding: 0;
}

.S_sec{
float: left;
padding : 15px 0;
background: #ddd;
width: 100px;
text-align: center;
}
.S_sec1{
float : left;
padding : 15px 0;
margin-left: 10px;
}
.tt{

}
</style>
<div style="height: 750px;">
<br>
<h2>제품문의</h2>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >

<form action="ProdQnAInsertReg" method="post" enctype="multipart/form-data">
<input type="hidden" name="user_id" value="${user_id }"/>
<div>
	<hr>
	<div class="tt">
		<div class="S_sec">작성자</div>
		<div class="S_sec1">
		<input type="text" name="pname"/>
		</div>
		<div style="clear: both;"></div>
	</div>
	<hr>
	<div class="tt">
		<div class="S_sec">제목</div>
		<div class="S_sec1">
		<input type="text" name="title" value="제품문의" style="width : 200%;"/>
		</div>
		<div style="clear: both;"></div>
	</div>
	<hr>
	<div class="tt">
		<div class="S_sec">사진첨부</div>
		<div class="S_sec1">
		<input type="file" name="upfile">
		</div>
		<div style="clear: both;"></div>
	</div>
	<hr>
	<div class="tt">
		<div style="padding : 15px 0;">
		<textarea name="content" rows="5" cols="30" style="margin-left:5%; resize: none; width: 90%; height: 300PX;">
제품분류 :  

제품이름 :  

문의사항 : 
 
		</textarea>
		</div>
		<div style="clear: both;"></div>
	</div>
	<hr>
	<div class="tt">
		<div class="S_sec">PW 설정</div>	
		<div class="S_sec1">
		<input type="password" name="pw"/>
		</div>	
		<div style="clear: both;"></div>
	</div>
	<hr style="margin-bottom: 10px;">
	<div class="tt">
		<div align="center">
		<a href="ProdQnAList" >
			<input type="button" value="취소"></a>
			<input type="submit" value="등록" />
		</div>
	</div>
</div>
</form>
</div>