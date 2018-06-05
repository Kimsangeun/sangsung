<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">	
	
	var remove_div = function(test){		
		$("#"+test.id).remove();		
	}
	
	var cnt = 1;
	var add_row = function(){
		var kk = document.getElementById('kk');
		kk.innerHTML = '<input type="hidden" name="cnt" value="'+cnt+'">';
		if(cnt==5){
			alert("더이상 추가불가능!");
			return;
		}
		var my_tbody = document.getElementById('my-tbody');
	    // var row = my_tbody.insertRow(0); // 상단에 추가
	    var row = my_tbody.insertRow( my_tbody.rows.length ); // 하단에 추가
	    var cell1 = row.insertCell(0);
	    var cell2 = row.insertCell(1);
	    cell1.innerHTML = '';
	    cell2.innerHTML = '<input type="file" name="imgfile'+cnt+'">';
	    cnt++;
	}
	
	function delete_row() {
	    var my_tbody = document.getElementById('my-tbody');
	    if (my_tbody.rows.length < 1) return;
	    // my_tbody.deleteRow(0); // 상단부터 삭제
	    my_tbody.deleteRow( my_tbody.rows.length-1); // 하단부터 삭제
	    cnt--;
	    var kk = document.getElementById('kk');
		kk.innerHTML = '<input type="hidden" name="cnt" value="'+cnt+'">';
	  }
</script>

<div style="width: 100%; height: 95%; border:5px outset #224;">
	<form action="InsertReg" method="post" enctype="multipart/form-data">
	<input type="hidden" value=${id } name="id">
	<input type="hidden" value="${cate }" name="cate">
	<input type="hidden" value="${rang }" name="rang">
		<table width = 100% border="">
			<tr>
				<td>제목(TITLE)</td>
				<td colspan="2"><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>사진(PHOTO)</td>
				<td><input type="file" name="imgfile"></td>
				<td>
					<input style="width: 30%;" type="button" value="사진추가" onclick="add_row()">
					<input style="width: 30%;" type="button" value="삭제" onclick="delete_row()">
				</td>
			</tr>
			<tbody id="my-tbody"></tbody>
			<tr>
				<td>드라이버(Driver)</td>
				<td colspan="2"><input type="file" name="driver"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="2"><textarea style="width: 100%;" cols="50" rows="10" name="content">[내용]</textarea></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<a style="text-decoration:none;" href="ListView?id=${id }&cate=${cate }&rang=${rang}">
						<input type="button" value="이전으로">
					</a>
					<input type="reset" value="초기화">
					<input type="submit" value="전송">
				</td>
			</tr>
		</table>
		<div id="kk"><input type="hidden" name="cnt" value="0"></div>
	</form>
</div>
