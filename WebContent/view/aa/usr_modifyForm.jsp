<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="Usr_ModifyReg" method="post">

<div>
	아이디 아이디 :
</div> 

<div>
${id }
</div>


<div>
	이름 이름:
</div> 

<div>
${data.name }
</div>

<div>
비밀번호::::::
</div>
<div>
<input type="passward"  name="pw"/>
</div>



<div>
주소 ::::
</div>
<div>
<input type="text" value="${data.address }" name="address"/>
</div>

<div>
폰넘버 ::::
</div>
<div>
<input type="text" value="${data.phone }" name="phone"/>
</div>




<div>
<input type="submit" value="수정 완료">
<input type="button" value="뒤로가기" onclick="location.href='Usr_ListForm'">
</div>
</form>