<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	
	var delete_click = function(i){
		var txt;
		var url;
		if(i==1){
			txt = '사진';
			url = 'DeletePhoto';
		}else{
			txt = '파일';
			url = 'DeleteDriver';
		}
		
		if(confirm(txt+'을 삭제하시겠습니까? 삭제후 복구는 불가능합니다.')){
			var frm = document.frm;
			frm.action = url;
			frm.submit();
		}
	}
	var cnt = 1;
	var end = 5-${size-1};
	
	var add_row = function(){
		var kk = document.getElementById('kk');
		kk.innerHTML = '<input type="hidden" name="cnt" value="'+cnt+'">';
		if(cnt==end){	
			alert("더이상 추가불가능!");
			return;
		}
		var my_tbody = document.getElementById('my-tbody');
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
	<form name ="frm" action="ModifyReg" method="post" enctype="multipart/form-data">
	<input type="hidden" value=${id } name="id">
	<input type="hidden" value="${data.id }" name="textid">
	<input type="hidden" value="${cate }" name="cate">
	<input type="hidden" value="${rang }" name="rang">
	<input type="hidden" value="${param.page }" name= "page">
		<table width = 100% border="">
			<tr>
				<td>제목(TITLE)</td>
				<td colspan="2"><input type="text" name="title" value="${data.title }"></td>
			</tr>
			<tr>
				<td>사진(PHOTO)</td>
				<c:choose>
					<c:when test="${data.photo!=null }">
						<td><strong>${data.photo }</strong></td>
						<input type="hidden" value="${data.photo}" name="photo">
					</c:when>
					<c:otherwise>
						<td><input type="file" name="imgfile"></td>
					</c:otherwise>
				</c:choose>
				<td>
					<c:if test="${data.photo!=null }">
						<input type="button" value="파일삭제" onclick="delete_click(1)">
					</c:if>
					<input style="width: 30%;" type="button" value="컬럼추가" onclick="add_row()">
					<input style="width: 30%;" type="button" value="컬럼삭제" onclick="delete_row()">
				</td>
			</tr>
			<tbody id="my-tbody"></tbody>
			<tr>
				<c:choose>
					<c:when test="${data.driver!=null }">
						<td>드라이버(Driver)</td>
						<td><strong>${data.driver }</strong></td>
						<input type="hidden" value="${data.driver }" name="dfile">
						<td><input type="button" value="파일삭제" onclick="delete_click(2)"></td>
					</c:when>
					<c:otherwise>
						<td>드라이버(Driver)</td>
						<td colspan="2"><input type="file" name="driver"></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="2"><textarea style="width: 100%;" cols="50" rows="10" name="content">${data.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<a style="text-decoration:none;" href="Detail?userid=${id }&textid=${data.id}&page=${param.page}">
						<input type="button" value="이전으로">
					</a>
					<a href="AdjustForm?id=${id }&textid=${data.id}"><input type="button" value="초기화"></a>
					<input type="submit" value="전송">
				</td>
			</tr>
		</table>
		<div id="kk"><input type="hidden" name="cnt" value="0"></div>
	</form>
</div>