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
주소 ::::
</div>
<div>
${data.address }
</div>

<div>
폰넘버 ::::
</div>
<div>
${data.phone }
</div>

<script>

link_delete = function(page,id){
     if (confirm("정말로 탈퇴하시겠습니까??") == true){    //확인
            location.href="Usr_DeleteReg"
     }else{   //취소
         return false;
     }
}

</script>

<div>
<input type="button" value="정보 수정" onclick="location.href='Usr_ModifyForm'">
<input type="button" value="회원 탈퇴" onclick="link_delete()">
</div>
