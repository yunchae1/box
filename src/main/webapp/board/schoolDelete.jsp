<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String bidx = (String)request.getAttribute("bidx");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항-글삭제</title>
<script type="text/javascript">
//자바스크립트 유효성 검사
function check(){
	//alert("테스트입니다.");	
	var fm = document.frm;
	
	if(fm.pwd.value ==""){
		alert("비밀번호를 입력해주세요.");
		fm.pwd.focus();
		return;
	}
	//가상경로 지정
	fm.action = "<%=request.getContextPath() %>/board/schoolDeleteAction.do";
	fm.method = "post";	//감춰져서 넘기는 방식 Post
	fm.submit();	
	return;

	}

</script>
</head>
<style>
*{margin: 0;padding: 0;}
div{width: 50%;margin: 100px auto;
    padding: 30px;
    box-sizing: border-box;
    line-height: 36px;}
</style>
<body>
	<div>
		글삭제
		<hr>
		<form name="frm">
		<label>비밀번호입력</label>
			<input type="password" name="pwd">
			<input type="hidden" name="bidx" value="<%=bidx %>">
			<br>
		    <input type="button" name="btn" value="확인" onclick="check();">
			<input type="reset" name="btn" value="취소">
		</form>
		</div>
</body>
</html>