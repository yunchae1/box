package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.MemberVo;
import dbconn.Dbconn;

public class MemberDao {
	private PreparedStatement pstmt;
	private Connection conn;	//멤버변수 전역변수 객체변수인 conn에 연결정보 담기
	
	public MemberDao() {	//생성자를 통해서 db연결 객체정보를 가져온다
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnect();
	}

	public int memberInsert(MemberVo mv){
		
		
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
	
	public ArrayList<MemberVo>memberSelectAll(){
		ArrayList<MemberVo> alist =new ArrayList<MemberVo>();
		
		String sql = "select * from member0919 where delYn='N'order by midx desc";
		ResultSet rs = null;	//db의 데이터를 받기 위한 전용 클래스
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//처음에서 다음 행으로 이동해서 데이터가 있는지 체크해서 있으면 true
			while(rs.next()) {
				MemberVo mv = new MemberVo();
				mv.setMemberId(rs.getString("memberId"));  //rs에 담긴 값을 vo에 옮겨담는다
				mv.setMemberName(rs.getString("memberName"));
				mv.setWriteday(rs.getString("writeday"));
				mv.setIp(rs.getString("Ip"));
				alist.add(mv);	//옮겨담은 vo를 하나씩 alist에 추가한다
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return alist;
	}
	
	public MemberVo memberLogin(String memberId,String memberPw) {
		System.out.println("aaaaa"+memberId);
		System.out.println("bbbbb"+memberPw);
		
		ResultSet rs = null;
		String sql = "select memberId, memberName from member0919 where delyn='N' and memberid=? and memberpw=? ";
		MemberVo mv = null;
		
		try {	//sql문장을 수행, 리턴값을 받음
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rs = pstmt.executeQuery();	//ResultSet:컬럼값을 담아오는 데이터 전용 클래스
			
			if(rs.next()) {//next():커서를 이동시켜서 이동한 행에 값이 있으면 true
				System.out.println("testssss");
				mv = new MemberVo();
				System.out.println(mv);
				
				mv.setMemberId(rs.getString("memberid"));	//cnt값이 0이면 아이디패스워스 값이 없는 것
				mv.setMemberName(rs.getString("membername"));
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}	
		
		return mv;
		
	}
	
	public int memberIdCheck(String memberId) {
		int value = 0;
		ResultSet rs = null;
		
		String sql = "select count(*) as cnt from member0919 where memberId=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				value = rs.getInt("cnt");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		return value;
	}
	
		}
		return value;
	
	}
}
