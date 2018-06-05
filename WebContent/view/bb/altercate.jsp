<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
function chageRageSelect(){
	var langSelect = document.getElementById("rage");
    var map = ${data}
    // select element에서 선택된 option의 value가 저장된다.
    var selectValue = langSelect.options[langSelect.selectedIndex].value;
    <c:set var="key" value="selectValue"/>
  
    alert(<c:out value="${key }"/>);
 /*    <c:set var="item" value="${data.get(key)}"/>
    	alert(<c:out value="${item }"/>);
     */
    var cate = document.getElementById("categori");
 	var objOption = document.createElement("option");    
 	
   /*  objOption.text = valArr[i] + "개";
    objOption.value = valArr[i];
    
    objSel.options.add(objOption);*/ 
    }
</script>
<form action="Delete">

	<select id="rage"" name="rage" onchange="chageRageSelect()">
		<option value="" selected disabled>-- 선택 --</option>              
		<c:forEach var="name" items="${data.keySet()}">
			<option value='${name }'>${name }</option>
		</c:forEach>
	</select>
	<select id="categori" name="cate">
		<option value="" selected disabled>-- 선택 --</option>              
	</select>
</form>