<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
//자바스크립트 유효성 검사
function check(){
	alert("테스트입니다.");	
	var fm = document.frm;
	
	if(fm.memberId.value ==""){
		alert("아이디를 입력해주세요.");
		fm.memberId.focus();
		return;
	}else if(fm.idchk.value != 3){
		alert("아이디 중복체크를 해주세요.");
		fm.idchk.focus();
		return;
	}else if(fm.idchk2.value != 0){
		alert("사용할 수 없는 아이디입니다.");
		fm.idchk2.focus();
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
	}else if(fm.memberGender.value ==""){
			alert("성별을 입력해주세요.");
			fm.memberGender.focus();
			return;
	}else if(fm.memberPhone.value ==""){
		alert("연락처를 입력해주세요.");
		fm.memberPhone.focus();
		return;
	}else if(fm.memberEmail.value ==""){
		alert("이메일을 입력해주세요.");
		fm.memberEmail.focus();
		return;
	}else if(!email_check(fm.memberEmail.value) ){
		alert("이메일이 유효하지 않습니다.");
		fm.memberEmail.focus();
		return;	
	}else if(fm.memberAddr.value ==""){
		alert("주소를 입력해주세요.");
		fm.memberAddr.focus();
		return;
	}	
		
	function email_check( email ) {    
	    var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	    return (email != '' && email != 'undefined' && regex.test(email)); 
	}

	
	fm.action = "<%=request.getContextPath() %>/memberr/memberJoinAction.do";
	fm.method = "post";	//감춰져서 넘기는 방식 Post
	fm.submit();

	return;
}

$(document).ready(function(){
	
	alert("test jquery 사용");
	
	$("#idcheck").click(function(){
		alert("버튼클릭했어요");	//ajax실행
		
		var memberId = $("#memberId").val();
		var url = "<%=request.getContextPath() %>/memberr/memberIdCheck.do";
		
		$.ajax({
			type:'post', //데이터를 넘기는 형식들 구분
			url:url,
			dataType:'json',
			data:{"memberId":memberId},
			success:function(data){	//json값 넘어옴
				
				if(data.length != 0){
					//alert(data.idYn);
					if(data.idYn ==0){
						alert("사용가능한 아이디입니다.");
						$("#idchk2").val(0);
						
					}else{
						alert("사용불가능한 아이디입니다.");
						$("#idchk2").val(1);
						
					}
					
					$("#idchk").val(3);
				}
				
				
			},
			error:function(error){
				alert("실패");
				
			}
		});
		
		
		
	});
	
});

function def(){
	$("#idchk").val(1);
}
</script>

</head>
<style>
*{margin: 0;padding: 0;}
div{width: 25%;margin: 100px auto;
    padding: 30px;
    box-sizing: border-box;
    line-height: 36px;}
</style>
<body>
	<div>
		회원가입
		<hr>
		<form name="frm">
		<input type="hidden"name="idchk"id="idchk"value="1">
		<input type="hidden"name="idchk2"id="idchk2"value="1">
			<p>
			<label>ID:</label><br>
			<input type="text"name="memberId" id="memberId"onblur="def();"><button type="button" id="idcheck">중복체크</button>
			</p>
			<p>
			<label>Password:</label><br>
			<input type="password"name="memberPW">
			</p>
			<p>
			<label>Password 확인:</label><br>
			<input type="password"name="memberPW2">
			</p>
			<p>
			<label>이름:</label><br>
			<input type="text"name="memberName">
			</p>
			<p>
			<label>생년월일:</label><br>
			<input type="date"name="memberBirth">
			</p>
			<p>
			<label>성별:
			남 <input type="radio"name="memberGender"value="남">
			여 <input type="radio"name="memberGender"value="여">
			</label>
			</p>
			<p>
			<label>연락처:</label><br>
			<input type="number"name="memberPhone"max="10000000000">
			</p>
			<p>
			<label>이메일:</label><br>
			<input type="email"name="memberEmail">
			</p>
			<p>
			<label>주소:</label><br>
			<select name="memberAddr">
			<option value="">선택</option>
			<option value="전주">전주</option>
			<option value="서울">서울</option>
			<option value="대전">대전</option>
			</select>
			</p>
			<input type="button" name="btn" value="확인" onclick="check();">
			<input type="reset" name="btn" value="취소">
		</form>
	</div>
</body>
</html>