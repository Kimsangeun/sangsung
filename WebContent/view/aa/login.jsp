<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<br>
<h2>로그인</h2>
<hr style="margin-top:10px; margin-bottom: 30px;" size="5px" color="#ddd" >

<div align="center" style="width: 100%; height: 580px;">

			
<form action="LoginReg" method="post">
  <br><br><br><br>
				※예약관련 서비스는 로그인 후 이용하실 수 있습니다.	 
  
	<fieldset style="left:28%; top:40%; width: 45%; height: 15%; position:absolute; border-color: #d0d0d0;">
		<legend style="color: #111;">LOGIN</legend>
	<table width=100% height=80% border="0">
		<tr>
			<th width="20%">ID</th>
			<td width="80%"><input style="width: 80%" type="text" name="id"></td>
		</tr>
		<tr>
			<th>PW</th>
			<td width="80%"><input style="width: 80%" type="password" name="pw"></td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<a href="Join"><input type="button" value="회원가입"></a>
				<input type="submit" value="로그인"></a>
			</td>
		</tr>	
		<tr>
			<td align="center" colspan="2">
			<br><br>
				
			</td>
		</tr>	
	</table>
	</fieldset>
</form>
</div>
