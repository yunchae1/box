<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 

<%   
    
Class.forName("oracle.jdbc.driver.OracleDriver");	//해당 드라이버의 프로그램을 (동적로딩) 메모리에 올려놓는다


String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";	//오라클 접속주소
String user = "c##apic";
String password = "1234";
Connection conn = DriverManager.getConnection(url,user,password);
//out.print("연결객체정보는"+conn);    
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>