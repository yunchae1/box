<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*"%>
<%@ page import = "Domain.MemberVo" %>
<%@ page import = "Domain.SchoolVo" %>
<%!

public int memberInsert(Connection conn,MemberVo mv){
	int value = 0;
	String sql = "INSERT INTO MEMBER0919(MIDX,MEMBERID,MEMBERPW,MEMBERNAME,MEMBERBIRTH,MEMBERGENDER,MEMBERADDR,MEMBERPHONE,MEMBEREMAIL,IP) VALUES(MIDX_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	PreparedStatement pstmt = null;
	try{
	pstmt = conn.prepareStatement(sql);	//쿼리를 실행하기 위한 클래스 객체를 반환
	pstmt.setString(1,mv.getMemberId());
	pstmt.setString(2,mv.getMemberPW());
	pstmt.setString(3,mv.getMemberName());
	pstmt.setString(4,mv.getMemberBirth());
	pstmt.setString(5,mv.getMemberGender());
	pstmt.setString(6,mv.getMemberAddr());
	pstmt.setString(7,mv.getMemberPhone());
	pstmt.setString(8,mv.getMemberEmail());
	pstmt.setString(9,mv.getIp());
	
	value = pstmt.executeUpdate();	//쿼리를 실행시켜서 성공이면 1 실패면 0이 리턴
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
		pstmt.close();
		conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	return value;
}

public int schoolInsert(Connection conn,SchoolVo mv){
	int value = 0;
	String sql = "INSERT INTO MEMBER0919(MIDX,MEMBERID,MEMBERPW,MEMBERNAME,MEMBERBIRTH,MEMBERADDR,MEMBERPHONE,MEMBEREMAIL,IP) VALUES(MIDX_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,)";
	PreparedStatement pstmt = null;
	try{
	pstmt = conn.prepareStatement(sql);	//쿼리를 실행하기 위한 클래스 객체를 반환
	pstmt.setString(1,mv.getMemberId());
	pstmt.setString(2,mv.getMemberPW());
	pstmt.setString(3,mv.getMemberName());
	pstmt.setString(4,mv.getMemberBirth());
	pstmt.setString(5,mv.getMemberAddr());
	pstmt.setString(6,mv.getMemberPhone());
	pstmt.setString(7,mv.getMemberEmail());
	pstmt.setString(8,mv.getIp());
	
	value = pstmt.executeUpdate();	//쿼리를 실행시켜서 성공이면 1 실패면 0이 리턴
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
		pstmt.close();
		conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	return value;
}



%>
    