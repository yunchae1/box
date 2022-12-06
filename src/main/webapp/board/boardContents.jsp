<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Domain.BoardVo" %>

<%
BoardVo bv = (BoardVo)request.getAttribute("bv");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
}
	//가상경로 지정
	fm.action = "<%=request.getContextPath() %>/board/boardContents.do";
	fm.method = "post";	//감춰져서 넘기는 방식 Post
	fm.submit();	
	return;
}
</script>
<title>Insert title here</title>
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
	내용보기
		<table border="1"width="500">
                <tr>
                    <th>제목</th>
                    <td colspan="4"><%=bv.getSubject() %></td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td td colspan="4"><%=bv.getWriter() %></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td td colspan="4"><%=bv.getContents() %></td>
                </tr>
                <tr>
                <td></td>
                <td><input type = "button" value="수정" onclick="location.href='<%=request.getContextPath()%>/board/boardModify.do?bidx=<%=bv.getBidx() %>'">
                <button onclick="location.href='<%=request.getContextPath()%>/board/boardDelete.do?bidx=<%=bv.getBidx() %>'">삭제</button>
                <button onclick="location.href='<%=request.getContextPath()%>/board/boardReply.do?bidx=<%=bv.getBidx() %>&originbidx=<%=bv.getOriginbidx() %>&depth=<%=bv.getDepth() %>&lvl=<%=bv.getLvl() %>'">답변</button>
                <input type = "button" value="목록" onclick="location.href='<%=request.getContextPath()%>/board/boardList.do'"></td>
                </tr>
            </table>
	</div>
</body>
</html>