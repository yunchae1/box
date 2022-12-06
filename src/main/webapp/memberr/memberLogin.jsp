<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String bidx = (String)request.getAttribute("bidx");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript">
//자바스크립트 유효성 검사
function check(){
	alert("로그인");	
	var fm = document.frm;
	
	if(fm.memberId.value ==""){
		alert("아이디를 입력해주세요.");
		fm.memberId.focus();
		return;
	}else if(fm.memberPw.value ==""){
		alert("비밀번호를 입력해주세요.");
		fm.memberPw.focus();
		return;
	}
	//가상경로 지정
	fm.action = "<%=request.getContextPath() %>/memberr/memberLoginAction.do";
	fm.method = "post";	//감춰져서 넘기는 방식 Post
	fm.submit();	//액션쪽으로 이동
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
		로그인
		<hr>
		<form name="frm">
		<label>아이디:</label>
			<input type="text" name="memberId">
		<br>
		<label>비밀번호:</label>
			<input type="password" name="memberPw">
			<input type="hidden" name="bidx" value="<%=bidx %>">
			<br>
		    <input type="button" name="btn" value="확인" onclick="check();">
			<input type="reset" name="btn" value="취소">
		</form>
		</div>
</body>
</html>