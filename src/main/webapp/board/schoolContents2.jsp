<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Domain.NoticeVo" %>

<%
NoticeVo nv = (NoticeVo)request.getAttribute("nv");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
}
	//가상경로 지정
	fm.action = "<%=request.getContextPath() %>/board/schoolContents2.do";
	fm.method = "post";	//감춰져서 넘기는 방식 Post
	fm.submit();	
	return;
}
</script>
<title>자유게시판-글보기</title>
    <link href="../style.css"rel="stylesheet">
    <link href="../images/logo.png"rel="shortcut icon">
</head>
<body>
    <section id="header">
        <div id="logo">
            <p><img src="../images/pagelogo.png"alt="학교로고"
                style="width: 160px;height: 160px;margin-left: 50px;"></p>
        </div>
        <div id="util">
            <ul>
                <span><a href="#">회원가입</a></span>
                <span><a href="#">로그인</a></span>
            </ul>
        </div>
        <div id="index">
            <ul>
                <li><a href="#">동창회 소개</a></li>
                <li><a href="#">공지사항</a></li>
                <li><a href="#">자유게시판</a></li>
                <li><a href="#">동창회개최</a></li>
                <li><a href="#">Q&A</a></li>
            </ul>
        </div>
        <hr>
    </section>
    <section id="main">
        <div id="mindex">
            <ul>
                <li><a href="#">동창회 소개</a>
                    <div class="sub">
                        <div class="sub_menu">인사말/동창회 소개</div>
                        <div class="sub_txbg"></div>   
                    </div>
                </li>
                <li><a href="#">공지사항</a></li>
                <li><a href="#">자유게시판</a></li>
                <li><a href="#">동창회개최</a></li>
                <li><a href="#">Q&A</a>
                    <div class="sub">
                        <div class="sub_menu"><a href="#">Q&A</a></div>
                        <div class="sub_menu"><a href="#">신고합니다</a></div>
                        <div class="sub_txbg"></div>   
                    </div>
                </li>
            </ul>
        </div>
        <div id="notice">
            <h2>| 자유게시판</h2>
            <hr>
        <div id="notice-view">
            <h3><%=nv.getSubject() %></h3>
            <span>
                <p><%=nv.getWriter() %></p>
                <p>1</p>
                <p>13</p>
                <img src="../images/reply.png" alt="댓글">
                <img src="../images/eye.png" alt="조회">
            </span>
            <section>
                <p><%=nv.getContents() %></p>
            </section>
            <span>
                <p id="schedule"><a>웹개발 취업스터디 참가 양식.hwp</a></p>
                <img id="file" src="../images/file.png" alt="파일첨부" class="file">
                <br>
                <p><img id="profile" src="../images/사람.png" alt="회원사진">한무화과 : 쪽지 보내요.</p>
            </span>
            <hr>
            <button onclick="location.href='<%=request.getContextPath()%>/board/schoolModify2.do?bidx=<%=nv.getBidx() %>'">수정</button>
            <button onclick="location.href='<%=request.getContextPath()%>/board/schoolDelete2.do?bidx=<%=nv.getBidx() %>'">삭제</button>
            <button onclick="location.href='<%=request.getContextPath()%>/board/schoolReply.do?bidx=<%=nv.getBidx() %>&originbidx=<%=nv.getOriginbidx() %>&depth=<%=nv.getDepth() %>&lvl=<%=nv.getLvl() %>'">답변</button>
            <button onclick="location.href='<%=request.getContextPath()%>/board/Free.do'">목록</button>
        </div>
        
    </section>
    <section id="footer">
        <address>
            <p>뫄뫄여자고등학교 총동창회     Tel : 063-222-3232     Fax : 063-222-9696</p>
            <p>전라북도 완산구 뫄뫄1길 27</p>    
            <p>Copyright 2008. ALL RIGHT RESERVED.</p>
            <img src="../images/pagelogo.png" alt="">
        </address>
    </section>
</body>
</html>