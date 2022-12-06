<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.*" %>
<%@page import="service.MemberDao" %>
<%@page import="service.SchoolDao" %>
<%
//한글 깨짐 방지
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");

String memberIp = InetAddress.getLocalHost().getHostAddress();
%>
<jsp:useBean class="Domain.MemberVo" id="mv"/>	<!-- MemberVo 객체생성 -->
<jsp:setProperty name="mv" property="memberIp" value="<%= memberIp %>"/>
<jsp:setProperty name="mv" property="*"/>	<!-- input객체의 모든 값을 담겠다 -->

<jsp:useBean class="Domain.SchoolVo" id="sv"/>	<!-- SchoolVo 객체생성 -->
<jsp:setProperty name="sv" property="memberIp" value="<%= memberIp %>"/>
<jsp:setProperty name="sv" property="*"/>	<!-- input객체의 모든 값을 담겠다 -->

<% 
MemberDao md = new MemberDao();
md.memberInsert(mv);
%>
<%
SchoolDao sd = new SchoolDao();
sd.memberInsert(sv);
%>














