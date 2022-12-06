<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript">
//자바스크립트 유효성 검사
function check(){
	alert("로그인");	
	var fm = document.frm;
	
	if(fm.memberId.value ==""){
		alert("아이디를 입력해주세요.");
		fm.memberId.focus();
		return;
	}else if(fm.memberPw.value ==""){
		alert("비밀번호를 입력해주세요.");
		fm.memberPw.focus();
		return;
	}
	//가상경로 지정
	fm.action = "<%=request.getContextPath() %>/memberr/schoolLoginAction.do";
	fm.method = "post";	//감춰져서 넘기는 방식 Post
	fm.submit();	
	return;

	}

</script>
<link href="../images/logo.png"rel="shortcut icon">
    <style>
        *{margin: 0;padding: 0;position: relative;}
        #box{width: 800px; margin: 100px auto;
            background: #d6cecd;padding: 200px;
            box-sizing: border-box;
            line-height: 36px;
            }
        #box .logo{width: 250px; height: 250px;
            position: absolute;top: 60px;left: 96px;}
        #box .id{width: 60px;height: 60px; position: absolute; top: 159px;right: 343px;}
        #box .pw{width: 45px;height: 45px; position: absolute; top: 208px;right: 350px;}
        #box .service{position: absolute;top: 127px;right: 113px;
            font-size: 11px;}
        h2{text-align: center;margin: 20px;
            position: absolute;top: 60px;right: 200px;}
        form{width: 200px;height: 200px;
            position: absolute;top: 166px; left: 446px;}
        input{width: 200px;height: 28px;margin-bottom: 10px;}
        ::placeholder{text-align: center;}
        button{width: 120px; padding: 7px; margin: 10px;
            border: 0.5px solid #d6cecd;  border-radius: 7px;
            position: absolute;top: 15px; right: 35px;}
        span a{width: 200px;height: 37px;
            text-decoration: none; color: #333;font-size: 11px;
            position: absolute;top: 43px;left: 343px;}
    </style>
</head>
<body>
    <div id="box">
        <img src="../images/pagelogo.png" alt="로고" class="logo">
        <img src="../images/id.png" alt="아이디" class="id">
        <img src="../images/pw.png" alt="비밀번호" class="pw">
        <p class="service">원하시는 서비스를 이용하시려면 로그인이 필요합니다.</p>
        <h2>로그인</h2>
        <form name="frm">
            <input id="id" type="text" name="memberId"placeholder="ID or 학번">
            <br>
            <input id="pw" type="password" name="memberPw"placeholder="숫자 6자리 이상 + 특수문자">
            <p>
                <button  onclick="check();" type="submit">LOGIN</button>
            </p>
        </form>
        <span><a href="#">아이디/비밀번호 찾기</a></span>
    </div>
</body>
</html>