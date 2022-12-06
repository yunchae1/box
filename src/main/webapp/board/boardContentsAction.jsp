<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.*" %>
<%@page import="service.BoardDao" %>

<%
//한글 깨짐 방지
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");

String Ip = InetAddress.getLocalHost().getHostAddress();
%>
<jsp:useBean class="Domain.BoardVo" id="bv"/>	<!-- MemberVo 객체생성 -->
<jsp:setProperty name="bv" property="Ip" value="<%= Ip %>"/>
<jsp:setProperty name="bv" property="*"/>	<!-- input객체의 모든 값을 담겠다 -->

<%
BoardDao bd = new BoardDao();
bd.boardInsert(bv);


%>