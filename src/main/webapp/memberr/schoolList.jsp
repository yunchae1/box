<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Domain.SchoolVo" %>
<%
ArrayList<SchoolVo> alist = (ArrayList<SchoolVo>)request.getAttribute("alist");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뫄뫄여고 회원 목록</title>
</head>
<body>
뫄뫄여고 회원 목록입니다.

<table border=1 style="text-alingn:left.width:800px">
<tr>
<td>id</td>
<td>name</td>
<td>writeday</td>
<td>ip</td>
</tr>
<%for(SchoolVo sv : alist) { %>
<tr>
<td><%= sv.getMemberId() %></td>
<td><%= sv.getMemberName() %></td>
<td><%= sv.getWriteday() %></td>
<td><%= sv.getIp() %></td>
</tr>
<% } %>
</table>
</body>
</html>