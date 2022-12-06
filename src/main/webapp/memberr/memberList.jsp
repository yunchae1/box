<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Domain.MemberVo" %>
<%
ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
멤버 목록입니다.

<table border=1 style="text-alingn:left.width:800px">
<tr>
<td>id</td>
<td>name</td>
<td>writeday</td>
<td>ip</td>
</tr>
<%for(MemberVo mv : alist) { %>
<tr>
<td><%= mv.getMemberId() %></td>
<td><%= mv.getMemberName() %></td>
<td><%= mv.getWriteday() %></td>
<td><%= mv.getIp() %></td>
</tr>
<% } %>
</table>
</body>
</html>