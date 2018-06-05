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
<h2>예약문의</h2>
<hr  style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >
<form action="ResQnAInsertReg" method="post">
<input type="hidden" name="pname" value="${pname }"/>
<input type="hidden" name="user_id" value="${user_id }"/>
<div>
	<hr>
	<div class="tt">
		<div class="S_sec">제목</div>
		<div class="S_sec1" style="margin-left: 10px;">
		<input type="text" name="title" value="예약문의"  style="width : 200%;"/>
		</div>
		<div style="clear: both;"></div>
	</div>
	<hr>
	<div class="tt">
		<div style="padding : 15px 0;">
		<textarea name="content" rows="5" cols="30" style="margin-left:5%; resize: none; width: 90%; height: 300PX;">
문의사항 : 
 
		</textarea>
		</div>
		<div style="clear: both;"></div>
	</div>
	<hr>
  	<hr style="margin-bottom: 10px;">
	<div class="tt">
		<div align="center">
			<input type="submit" value="등록" />
			<a href="MyResQnAList">
				<input type="button" value="취소"/> 
			</a>
		</div>
	</div>
</div>
</form>
</div>