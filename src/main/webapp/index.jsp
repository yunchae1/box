<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Domain.*" %>    
<% MemberVo mv = (MemberVo)session.getAttribute("mv");
String memberName = "";
String memberId = "";
String logOut = "";
if(mv != null) {
	memberName = mv.getMemberName();
	memberId = mv.getMemberId();	//
	
	logOut = "<button onclick=location.href='"+request.getContextPath()+"/memberr/memberLogOut.do'>로그아웃</button>";
	
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
회원이름:<%=memberName %><br>
회원아이디:<%=memberId %><br>
<%=logOut %><br>
------------------------------------
<br>
<a href = "<%=request.getContextPath() %>/memberr/memberJoin.do">회원가입하기</a>
<a href = "<%=request.getContextPath() %>/memberr/memberList.do">회원목록가기</a>
<a href = "<%=request.getContextPath() %>/memberr/memberLogin.do">로그인</a>
<a href = "<%=request.getContextPath() %>/board/boardWrite.do">글쓰기</a>
<a href = "<%=request.getContextPath() %>/board/boardList.do">글목록가기</a>
</body>
</html>