<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var idchk = function(){
		alert("���̵��ߺ�üũ");
		var frm = document.frm;
		var id = frm.id.value;
		if(id!=""){
			frm.action = "idChkReg";
			frm.submit();
		}else{
			alert('���̵� �Է����ּ���');	
		}
	}
</script>
<div style="height: 730px;">
<div style="position: absolute; width: 100%; height: 100%">
<div align="center" style="width: 100%; height: 70%; border: 1px thick black;">
<br>
<h3>�̿���</h3>
	<div align="left" style="width: 80%; height: 80%; border: 5px solid #244; position: relative; overflow: auto;">
		�ᰳ�������� ���� �� �̿�<br><br>

	1. ���������� �������̿� ����<br>
 	��AS���ͼ��� ȸ���� AS����Ȩ������ ����ȭ���� �̿��� �������� �ϴ� ��� <br><br>

	2. �����Ϸ��� ���������� �׸�<br>
	 AS���ͼ��񽺴� ȸ������ �� ��Ȱ�� ���� ��� �� �⺻���� ���� ������ ���� �Ʒ��� ���� ���������� �����ϰ� �ֽ��ϴ�.<br>
	 ��AS���ͼ��� ȸ����ȣ, ��ȸ�� ���̵�, ��ȸ�� ��й�ȣ, �̸�, AS���ͼ��� ���Լ���, �ڵ���, �̸��� <br><br>

	3. ���������� ���� �� �̿� �Ⱓ<br>
	 ȸ�� �̿����� ���������� ȸ�������Ϸκ��� Ż�� �ñ��� ���� �� �̿�Ǹ�, ��Ģ������ ���������� ���� �� �̿������ �޼��Ǹ� ��ü ���� �ı��մϴ�. ���� �츮 ������ ������ü�� ȸ��Ż�� ��û �� �� ȸ���� ���������� ��ü ���� �ı��մϴ�.<br><br>

	4. ���ǰźα� �� ���� �źο� ���� ������<br>
 	�����ڴ� �������� ���� �� �̿뿡 ���Ͽ� �ź��� �� �ִ� �Ǹ��� �ֽ��ϴ�. ��, �̿� ���� ���Ǹ� �ź��� ��쿡�� ȸ�������� �Ұ����մϴ�<br><br>

	5. ������ü�� �Ǹ�<br>
	 AS���ͼ��� ȸ���� �ڽ��� �������� ó���� �����Ͽ� �Ʒ��� ���� �Ǹ��� �����ϴ�.<br>
 	�� ���������� ó�� ���θ� Ȯ���ϰ� ���������� ���Ͽ� ����(�纻�� �߱��� �����Ѵ�. ���� ����)�� �䱸�� �Ǹ�<br>
 	�� ���������� ó�� ����, ���������� �� �ı⸦ �䱸�� �Ǹ�<br>
 	�� ���������� ó���� ���Ͽ� �߻��� ���ظ� �ż��ϰ� ������ ������ ���� �������� �Ǹ�<br>
 	�̿��� �е��� �������� ��ȣ�� ���� ������������ȣ������ �ؼ��ϰ� �ֽ��ϴ�.<br>
	</div>
</div>
<form name="frm" action="JoinReg" method="post">
<input type="hidden" value="${id}" name="userid">
	<fieldset>
		<legend align="center">ȸ������</legend>
		<table width=100%; border="1">
			<tr>
				<th width="30%">ID</th>
				<td width="60%"><input style="width: 95%;" type="text" name="id"  <c:if test="${flag }">disabled="true"</c:if> value="${id}"></td>
				<td width="10%"><input style="width: 100%;" type="button" onclick="idchk()" value="���̵� �ߺ� üũ"></td>
			</tr>
			<tr>
				<th>PW</th>
				<td width="60% colspan="2"><input style="width: 95%;" type="password" name="pw"></td>
			</tr>	
			<tr>
				<th>�̸�</th>
				<td width="60% colspan="2"><input style="width: 95%;" type="text" name="name"></td>
			</tr>	
			<tr>
				<th>�ּ�</th>
				<td width="60% colspan="2"><textarea style="width: 95%;" rows="1" cols="30" name="address"></textarea></td>
			</tr>
			<tr>
				<th>��ȭ��ȣ</th>
				<td width="60% colspan="2"><input style="width: 95%;" type="tel" name="phone"></td>
			</tr>	
			<tr>
				<td align="center"; colspan="3"><input type="submit" value="ȸ������" <c:if test="${!flag }">disabled="true"</c:if> >
			</tr>	
		</table>
	</fieldset>

</form>
</div></div>