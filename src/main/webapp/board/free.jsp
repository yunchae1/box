<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Domain.*" %>
<%
ArrayList<NoticeVo> alist = (ArrayList<NoticeVo>)request.getAttribute("alist");
PageMaker pm = (PageMaker)request.getAttribute("pm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
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
                <li><a href="#"style="color:#BFB1AE;">자유게시판</a></li>
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
            <form>
                <fieldset>
                    <select>
                        <option>전체</option>
                        <option>제목+내용</option>
                        <option>작성자</option>
                    </select>
                    <input type="text">
                    <button>검색</button>
                </fieldset>
            </form>
            <button class="btn" onclick="location.href ='<%=request.getContextPath() %>/board/schoolWrite2.do'">등록</button>
            <p>-총 **개의 글이 있습니다.</p>
            <div id="num">
                <p><a href="">처음</a> ◀</p>
                <a href="">1</a>
                /
                <a href="">2</a>
                /
                <a href="">3</a>
                /
                <a href="">4</a>
                /
                <a href="">5</a>
                /
                <a href="">6</a>
                /
                <a href="">7</a>
                /
                <a href="">8</a>
                /
                <a href="">9</a>
                /
                <a href="">10</a>
                <p>▶ <a href="">마지막</a></p>
            </div>  
        <div>
            <table class="board-notice">
                <thead>
                <tr>
                    <th scope="col" class="th-num">No</th>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-date">작성자</th>
                    <th scope="col" class="th-date">등록일</th>
                    <th scope="col" class="th-date">조회</th>
                </tr>
                </thead>
                <tbody>
                <%for(NoticeVo nv : alist) { %>
                <tr>
                   <td><%=nv.getBidx() %></td>
                   <th>
                     <a href="<%=request.getContextPath() %>/board/schoolContents2.do?bidx=<%=nv.getBidx() %>"> <%=nv.getSubject() %></a></th>
                   <td><%=nv.getWriter() %></td>
                   <td><%=nv.getWriteday() %></td>
                   <td></td>
                </tr>
                </tbody>
              <% }%>
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