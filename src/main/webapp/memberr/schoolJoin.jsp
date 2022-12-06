<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<script type="text/javascript">
//자바스크립트 유효성 검사
function check(){
	alert("가입 성공입니다.");	
	var fm = document.frm;
	
	if(fm.memberId.value ==""){
		alert("아이디를 입력해주세요.");
		fm.memberId.focus();
		return;
	}else if(fm.memberPW.value ==""){
		alert("비밀번호를 입력해주세요.");
		fm.memberPW.focus();
		return;
	}else if(fm.memberPW2.value ==""){
		alert("비밀번호 확인이 없습니다.");
		fm.memberPW2.focus();
		return;
	}else if(fm.memberPW.value != fm.memberPW2.value){
		alert("비밀번호가 일치하지 않습니다.");
		fm.memberPW2.focus();
		return;
	}else if(fm.memberName.value ==""){
		alert("이름을 입력해주세요.");
		fm.memberName.focus();
		return;
	}else if(fm.memberBirth.value ==""){
		alert("생년월일을 입력해주세요.");
		fm.memberBirth.focus();
		return;
	}else if(fm.memberAddr.value ==""){
		alert("주소를 입력해주세요.");
		fm.memberAddr.focus();
		return;
	}else if(fm.memberEmail.value ==""){
		alert("이메일을 입력해주세요.");
		fm.memberEmail.focus();
		return;
	}else if(!email_check(fm.memberEmail.value) ){
		alert("이메일이 유효하지 않습니다.");
		fm.memberEmail.focus();
		return;	
	}else if(fm.memberPhone.value ==""){
		alert("연락처를 입력해주세요.");
		fm.memberPhone.focus();
		return;
	}	
		
	function email_check( email ) {    
	    var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	    return (email != '' && email != 'undefined' && regex.test(email)); 
	}

	
	fm.action = "<%=request.getContextPath() %>/memberr/schoolJoinAction.do";
	fm.method = "post";	//감춰져서 넘기는 방식 Post
	fm.submit();
	
	
	return;
}


</script>
    <link href="../images/logo.png"rel="shortcut icon">
    <style>
        *{margin: 0;padding: 0;position: relative;}
        #box{width: 850px; height: 654px;
            margin: 100px auto;
            background: #d6cecd;padding: 200px;
            box-sizing: border-box;
            line-height: 36px;
            }
        #box .logo{width: 120px; height: 120px;
            position: absolute;top: 20px;left: 95px;}
        #box .id{width: 60px;height: 60px; position: absolute; top: 159px;right: 343px;}
        #box .pw{width: 45px;height: 45px; position: absolute; top: 208px;right: 350px;}
        h2{text-align: center;margin: 20px;
            position: absolute;top: 60px;right: 288px;}
        form{width: 600px;height: 600px;font-size: 13px;
            position: absolute;top: 166px; left: 165px;}
        label[for]{display: inline-block; width: 110px;}
        label + input{width: 300px;height: 28px;
            margin-bottom: 5px;}
        ::placeholder{text-align: center;}
        #btn{width: 120px; padding: 7px; margin: 10px;
            border: 0.5px solid #d6cecd;  border-radius: 7px;
            position: absolute;top: -7px; right: 46px;}
        #btn2{width: 186px; padding: 14px; margin: 10px;
            border: 0.5px solid #d6cecd;  border-radius: 7px;
            position: absolute;top: 340px; right: 241px;
            font-size: 15px;color: red;}
    </style>
</head>
<body>
    <div id="box">
        <img src="../images/pagelogo.png" alt="로고" class="logo">
        <h2>회원가입 신청서 작성</h2>
        <form name="frm">
            <label for="id">아이디:</label>
            <input id="id" type="text"name="memberId"
            placeholder="영문+숫자">
            <button type="button" id="btn">중복확인</button>
            <br/>
            <label for="pw">비밀번호:</label>
            <input id="pw" type="password"name="memberPW"
            placeholder="영문7자 이상+특수문자">
            <br/>
            <label for="re">비밀번호 확인:</label>
            <input id="re" type="password"name="memberPW2"
            placeholder="비밀번호를 다시 입력해주세요.">
            <br/>
            <label for="name">이름:</label>
            <input id="name" type="text"name="memberName"
            placeholder="이름을 입력해주세요.">
            <br>
            <label for="birth">생년월일:</label>
            <input id="birth" type="text"name="memberBirth"
            placeholder="2000.01.01">
            <br>
            <label for="address">주소:</label>
            <input id="address" type="address"name="memberAddr"
            placeholder="전라북도 전주시 뫄뫄로 1">
			<select>
			<option value="">선택</option>
			<option value="전주">전주</option>
			<option value="서울">서울</option>
			<option value="대전">대전</option>
			</select>
            <br>
            <label for="em">이메일:</label>
            <input id="em" type="email"name="memberEmail"
            placeholder="example@naver.com">
            <select>
                <option>직접입력</option>
                <option>네이버</option>
                <option>구글</option>
                <option>카카오</option>
            </select>
            <br>
            <label for="tel">전화번호:</label>
            <input id="tel" type="tel"name="memberPhone"
            placeholder="010-0000-0000">
            <label for=""></label>
            <button id="btn2"onclick="check();">가입하기</button>    
        </form>
    </div>
</body>
</html>