<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뫄뫄여고 총동창회</title>
    <link href="style.css"rel="stylesheet" type="text/css">
    <link href="images/logo.png"rel="shortcut icon">
</head>
<body>
    <section id="header">
        <div id="logo">
            <p><img src="images/pagelogo.png"alt="학교로고"
                style="width: 160px;height: 160px;margin-left: 50px;"></p>
        </div>
        <div id="util">
            <span><a href = "<%=request.getContextPath() %>/memberr/schoolJoin.do">회원가입</a></span>
            <span><a href="<%=request.getContextPath() %>/memberr/schoolLogin.do">로그인</a></span>
        </div>
        <div id="index">
            <ul>
                <li><a href="<%=request.getContextPath() %>/memberr/schoolIntroduce.do">동창회 소개</a></li>
                <li><a href="<%=request.getContextPath() %>/board/Notice.do">공지사항</a></li>
                <li><a href="<%=request.getContextPath() %>/board/Free.do">자유게시판</a></li>
                <li><a href="#">동창회개최</a></li>
                <li><a href="#">Q&A</a></li>
            </ul>
        </div>
    </section>
    <section id="main">
        <div id="mphoto"></div>
        <div id="money">
            <p>동문회비/후원계좌<br>
            동문회비 : 011-222-333444<br>
            (뫄뫄은행 , 뫄뫄여고총동창회)<br>
            ------------------------------<br>
            후원계좌 : 011-555-666777<br>
            (뫄뫄은행 , 뫄뫄여고총동창회)
            </p>
        </div>
        <div id="news">
            <p><b>동창회 소식</b></p>
            <ul>
                <li><a href="#">동창회 개최</a></li>
                <li><a href="#">자유게시판</a></li>
                <li><a href="#">공지사항</a></li>
            </ul>
        </div>
        <a href="#"><div id="news2">
            <p>
                동문회비 납부 건 확...<br>
                올해 상반기...
            </p>
        </div></a>
        <a href="#"><div id="news2">
            <p>
                2014년 졸업생 김레...<br>
                안녕하세요...
            </p>
        </div></a>
        <a href="#"><div id="news2">
            <p>
                2014년 동창회 문의...<br>
                안녕하세요...
            </p>
            <img id="one" src="images/new.png" alt="new">
            <img id="two" src="images/new.png" alt="new">
            <img id="thr" src="images/new.png" alt="new">
        </div></a>
    </section>
    <section id="footer">
        <address>
            <p>뫄뫄여자고등학교 총동창회     Tel : 063-222-3232     Fax : 063-222-9696</p>
            <p>전라북도 완산구 뫄뫄1길 27</p>    
            <p>Copyright 2008. ALL RIGHT RESERVED.</p>
            <img src="images/pagelogo.png" alt="">
        </address>
    </section>
</body>
</html>