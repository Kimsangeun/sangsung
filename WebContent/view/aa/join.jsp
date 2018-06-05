<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var idchk = function(){
		alert("아이디중복체크");
		var frm = document.frm;
		var id = frm.id.value;
		if(id!=""){
			frm.action = "idChkReg";
			frm.submit();
		}else{
			alert('아이디를 입력해주세요');	
		}
	}
</script>
<div style="height: 730px;">
<div style="position: absolute; width: 100%; height: 100%">
<div align="center" style="width: 100%; height: 70%; border: 1px thick black;">
<br>
<h3>이용약관</h3>
	<div align="left" style="width: 80%; height: 80%; border: 5px solid #244; position: relative; overflow: auto;">
		■개인정보의 수집 및 이용<br><br>

	1. 개인정보의 수집ㆍ이용 목적<br>
 	⊙AS센터서비스 회원이 AS센터홈페이지 개인화서비스 이용을 목적으로 하는 경우 <br><br>

	2. 수집하려는 개인정보의 항목<br>
	 AS센터서비스는 회원가입 및 원활한 서비스 상담 등 기본적인 서비스 제공을 위해 아래와 같은 개인정보를 수집하고 있습니다.<br>
	 ⊙AS센터서비스 회원번호, 웹회원 아이디, 웹회원 비밀번호, 이름, AS센터서비스 가입센터, 핸드폰, 이메일 <br><br>

	3. 개인정보의 보유 및 이용 기간<br>
	 회원 이용자의 개인정보는 회원가입일로부터 탈퇴 시까지 보유 및 이용되며, 원칙적으로 개인정보의 수집 및 이용목적이 달성되면 지체 없이 파기합니다. 따라서 우리 센터은 정보주체의 회원탈퇴 요청 시 그 회원의 개인정보를 지체 없이 파기합니다.<br><br>

	4. 동의거부권 및 동의 거부에 따른 불이익<br>
 	가입자는 개인정보 수집 및 이용에 대하여 거부할 수 있는 권리가 있습니다. 단, 이에 대한 동의를 거부할 경우에는 회원가입이 불가능합니다<br><br>

	5. 정보주체의 권리<br>
	 AS센터서비스 회원은 자신의 개인정보 처리와 관련하여 아래와 같은 권리를 가집니다.<br>
 	⊙ 개인정보의 처리 여부를 확인하고 개인정보에 대하여 열람(사본의 발급을 포함한다. 이하 같다)을 요구할 권리<br>
 	⊙ 개인정보의 처리 정지, 정정·삭제 및 파기를 요구할 권리<br>
 	⊙ 개인정보의 처리로 인하여 발생한 피해를 신속하고 공정한 절차에 따라 구제받을 권리<br>
 	이용자 분들의 개인정보 보호를 위해 「개인정보보호법」을 준수하고 있습니다.<br>
	</div>
</div>
<form name="frm" action="JoinReg" method="post">
<input type="hidden" value="${id}" name="userid">
	<fieldset>
		<legend align="center">회원가입</legend>
		<table width=100%; border="1">
			<tr>
				<th width="30%">ID</th>
				<td width="60%"><input style="width: 95%;" type="text" name="id"  <c:if test="${flag }">disabled="true"</c:if> value="${id}"></td>
				<td width="10%"><input style="width: 100%;" type="button" onclick="idchk()" value="아이디 중복 체크"></td>
			</tr>
			<tr>
				<th>PW</th>
				<td width="60% colspan="2"><input style="width: 95%;" type="password" name="pw"></td>
			</tr>	
			<tr>
				<th>이름</th>
				<td width="60% colspan="2"><input style="width: 95%;" type="text" name="name"></td>
			</tr>	
			<tr>
				<th>주소</th>
				<td width="60% colspan="2"><textarea style="width: 95%;" rows="1" cols="30" name="address"></textarea></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td width="60% colspan="2"><input style="width: 95%;" type="tel" name="phone"></td>
			</tr>	
			<tr>
				<td align="center"; colspan="3"><input type="submit" value="회원가입" <c:if test="${!flag }">disabled="true"</c:if> >
			</tr>	
		</table>
	</fieldset>

</form>
</div></div>