<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>총동창회 소개</title>
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
            <span><a href="<%=request.getContextPath() %>/memberr/schoolJoin.do">회원가입</a></span>
            <span><a href="<%=request.getContextPath() %>/memberr/schoolLogin.do">로그인</a></span>
        </div>
        <div id="index">
            <ul>
                <li><a href="<%=request.getContextPath() %>/memberr/schoolintroduce.do">동창회 소개</a></li>
                <li><a href="<%=request.getContextPath() %>/board/Notice.do">공지사항</a></li>
                <li><a href="#">자유게시판</a></li>
                <li><a href="#">동창회개최</a></li>
                <li><a href="#">Q&A</a></li>
            </ul>
        </div>
        <hr>
    <section id="main">
        <div id="iphoto">
            <p>존경하고 사랑하는 뫄뫄동문 선후배 여러분, 반갑습니다.
                <br>
               우리 뫄뫄여고는 어언 개교 40년동안 약 2만여명의 졸업생을 배출하였고 사회 각계 직업군에서 독보적으로 활약하고 있습니다. 
                총동창회 또한 역대 집행부의 노력과 헌신으로, 직제, 시스템, 관련된 활동등에서 타 학교의 지표가 되고있습니다. 이러한 총동창회를 운영하고 발전시켜야할 진중한 책무에 무한 책임을 실감합니다. 
                저는 개인적으로 사회적 판단기준의 다양성을 인정할줄 알고, 객관적 사고 유지를 위해 노력해왔습니다. 
                미력함은 직제로 보완하고 저의 장점인 기획력과 조정능력으로 동창회의 새로운 도약과 화합을 위해 열정적으로 헌신하겠습니다.
                <br>
                첫째, 새로운 성장동력을 통한 이완된 참여도의 복원입니다.
                각종 SNS, 소모임 활성화로 인한 총회의 역할 감소, 높아진 눈높이와 기대에 못미치는 총회의 피드백, 유사한 이벤트의 반복노출로 인한 피로도 증가등 총회에 대한 관심도 & 참여도가 하강기조 국면에 처해진 실정입니다. 
                이에 동문들의 참여도 복원을 위한 정책개발과 실현에 최선의 노력을 다하겠습니다. 
                <br>
                둘째, 소통, 편안함, 즐거움이 상존하는 동창회를 만들겠습니다.
                중복된 이벤트의 정비와 개선으로 이완된 관심도를 증대시키고 누구나 편안하게 참여할수 있는 총동창회가 되도록 신선한 컨텐츠 도입을 강구하겠습니다. 
                총동창회의 새로운 도약과 화합을 위해 저의 합리적 융통성과 진성성을 바탕으로 최선의 노력을 다할 것을 약속드리며, 동문여러분들의 많은 참여와 지지를 부탁드립니다. 감사합니다.
            </p>
        </div>
        <div id="mindex">
            <ul>
                <li><a href="#"style="color:#BFB1AE;">동창회 소개</a>
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