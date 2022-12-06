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
<title>공지사항-글수정</title>
<script type="text/javascript">
//자바스크립트 유효성 검사
function check(){
	alert("테스트입니다.");	
	var fm = document.frm;
	
	if(fm.subject.value ==""){
		alert("제목을 입력해주세요.");
		fm.subject.focus();
		return;
	}else if(fm.writer.value ==""){
		alert("작성자를 입력해주세요.");
		fm.writer.focus();
		return;
	}else if(fm.contents.value ==""){
		alert("내용을 입력해주세요.");
		fm.contents.focus();
		return;
	}
	//가상경로 지정
	fm.action = "<%=request.getContextPath() %>/board/schoolModifyAction.do";
	fm.method = "post";	//감춰져서 넘기는 방식 Post
	fm.submit();	
	return;
}
</script>
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
            <h2>| 공지사항</h2>
            <hr>
        <div id="notice-write">
            <table width=900px height="600px">
            <form name="frm">
            <input type="hidden" name="bidx" value="<%=nv.getBidx()%>">
                <tr>
                    <td>
                        <input type="text" name="subject" value="<%=nv.getSubject() %>"
                        placeholder="제목을 입력하세요."
                        maxlength=20
                        style="width:100%" >
                    </td>
                </tr>
                <tr>
                	<td>
                	<input type="text" name="writer" value="<%=nv.getWriter() %>"
                		placeholder="작성자를 입력하세요."
                        maxlength=20
                        style="width:100%">
                	</td>
                </tr>
                <tr>
                    <td colspan="2" height=400>
                        <textarea name="contents" placeholder="내용을 입력하세요." style="width: 100%; height: 100%"><%=nv.getContents() %></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="file">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" value="취소">
                        <input type="button" value="확인" onclick="check();">
                    </td>
                </tr>
            </form>
            </table>
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