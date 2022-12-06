<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Domain.BoardVo" %>

<%
BoardVo bv = (BoardVo)request.getAttribute("bv");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//자바스크립트 유효성 검사
function check(){
	//alert("테스트입니다.");	
	var fm = document.frm;
	
	if(fm.subject.value ==""){
		alert("제목을 입력해주세요.");
		fm.subject.focus();
		return;
	}else if(fm.writer.value ==""){
		alert("작성자를 입력해주세요.");
		fm.writer.focus();
		return;
	}else if(fm.pwd.value ==""){
		alert("비밀번호를 입력해주세요.");
		fm.pwd.focus();
		return;
	}else if(fm.contents.value ==""){
		alert("내용을 입력해주세요.");
		fm.contents.focus();
		return;
	}
	//가상경로 지정
	fm.action = "<%=request.getContextPath() %>/board/boardReplyAction.do";
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
		답글달기
		<hr>
		<form name="frm">
		<input type="hidden" name="bidx" value="<%=bv.getBidx() %>">
		<input type="hidden" name="originbidx" value="<%=bv.getOriginbidx()%>">
		<input type="hidden" name="depth" value="<%=bv.getDepth()%>">
		<input type="hidden" name="lvl" value="<%=bv.getLvl()%>">
		
			<p>
			<label>제목:</label>
			<input type="text"name="subject">
			</p>
			<p>
			<label>작성자:</label>
			<input type="text"name="writer">
			</p>
			<p>
			<label>비밀번호:</label>
			<input type="password"name="pwd">
			</p>
			<p>
			<label>내용:</label><br>
			<textarea cols="120" rows="40"name="contents"></textarea>
			</p>
			<td><input type="button" name="btn" value="확인" onclick="check();">
				<input type="reset" name="btn" value="취소">
		</form>
		</div>
</body>
</html>