<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action= "Chk_Pw2" method="post">
<div style="height: 750px;">
	<hr style="margin-bottom: 30px;">

	<div style="width: 100%; margin-top: 10%; text-align: center;">
		비밀번호를 입력해 주세용</div>

	<div style="width: 100%; margin-top: 10%; margin-bottom: 20%;"
		align="center">
		<table border="" cellspacing="1">
			<tr>
			<tr>
				<td><div class="sec">PW</div></td>
			</tr>
			<tr>
				<td>
					<div class="sec">
						<input type="password" name="pw" />
					</div>
					<div style="clear: both;"></div>
				</td>
			</tr>
	</div>
	
	<div>
<input type="submit" value="확인">
<input type="button" value="뒤로가기">
</div>
</form>