<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Domain.*" %>
<%
ArrayList<BoardVo> alist = (ArrayList<BoardVo>)request.getAttribute("alist");
PageMaker pm = (PageMaker)request.getAttribute("pm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
게시판 목록
<table border=1 style="width:500px">
<tr>
<td>글번호</td>
<td>제목</td>
<td>작성자</td>
<td>등록일</td>
</tr>
<%for(BoardVo bv : alist) { %>
<tr>
<td><%=bv.getBidx() %></td>
<td>

<%for(int i =1;i<=bv.getLvl();i++){ 
	out.println("&nbsp;&nbsp;");
	
		if(i ==bv.getLvl()){ 
			out.println("ㄴ");
		}
} 
%>

<a href="<%=request.getContextPath() %>/board/boardContents.do?bidx=<%=bv.getBidx() %>"> <%=bv.getSubject() %></a></td>
<td><%=bv.getWriter() %></td>
<td><%=bv.getWriteday() %></td>
</tr>
<% } %>
<table style="text-align:center;width:500px">
<tr>
<td style="text-align:right;width:100px">
<%if (pm.isPrev()) { %>
<a href="<%=request.getContextPath() %>/board/boardList.do?page=<%=pm.getStartPage()-1%>">◀</a>
<%} %>
</td>
<td style="text-align:center">
<%
for(int i =pm.getStartPage(); i<=pm.getEndPage(); i++){
%>
<a href="<%=request.getContextPath() %>/board/boardList.do?page=<%=i%>"><%=i %></a>	
<%	
}
%>

</td>
<td style="text-align:left;width:100px">
<%if (pm.isNext() && pm.getEndPage()>0) {%>
<a href="<%=request.getContextPath() %>/board/boardList.do?page=<%=pm.getEndPage()+1%>">▶</a>
<%} %>

</td>
</tr>
</table>
</body>
</html>