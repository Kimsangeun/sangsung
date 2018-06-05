<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="Intro_notice_InsertReg" method="post">
<style type="text/css">
*{
margin: 0;
padding: 0;
}

.sec{
float: left;
padding : 5px 0;
}
</style>

<!-- <script>
	link_list = function(page) { 
		location.href= "Intro_notice_listForm?page="+page
	}

</script> -->
<div>
<div class="tt">
		<div class="sec" style="width: 100px;">제목</div>
		<div class="sec">
		<input type="text" name="title"/>
		</div>
		<div style="clear: both;"></div>
	</div>
	<hr>
	<div class="tt">
		<div class="sec">
		<textarea name="content" rows="20" cols="80" style="margin-left:30px; resize: none;">내용을 입력하세요.</textarea>
		</div>
		<div style="clear: both;"></div>
	</div>
	<div class="tt">
		<input type="submit" value="작성"/>
		<input type="reset" value="초기화" /> 
		<input type="button" value="목록으로" onclick="location.href='Intro_notice_listForm?page=${param.page}'">
	</div>
 <!-- onclick="link_list()"  -->
</div>
</form>