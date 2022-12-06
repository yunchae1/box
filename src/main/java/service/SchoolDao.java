package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.MemberVo;
import Domain.NoticeVo;
import Domain.SchoolVo;
import dbconn.Dbconn;

public class SchoolDao {
	private PreparedStatement pstmt;
	private Connection conn;	//멤버변수 전역변수 객체변수인 conn에 연결정보 담기
	
	public SchoolDao() {	//생성자를 통해서 db연결 객체정보를 가져온다
		Dbconn dbconn = new Dbconn();
		this.conn = dbconn.getConnect();
	}

	public int memberInsert(SchoolVo sv){
		
		
		int value = 0;
		String sql = "INSERT INTO MEMBER1130(MIDX,MEMBERID,MEMBERPW,MEMBERNAME,MEMBERBIRTH,MEMBERADDR,MEMBERPHONE,MEMBEREMAIL,IP) VALUES(MIDX_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
		//테이블에 데이터를 입력하는 쿼리문
		PreparedStatement pstmt = null;
		try{
		pstmt = conn.prepareStatement(sql);	//쿼리를 실행하기 위한 클래스 객체를 반환
		pstmt.setString(1,sv.getMemberId()); //물음표에 실제 입력한 값 셋팅
		pstmt.setString(2,sv.getMemberPW());
		pstmt.setString(3,sv.getMemberName());
		pstmt.setString(4,sv.getMemberBirth());
		pstmt.setString(5,sv.getMemberAddr());
		pstmt.setString(6,sv.getMemberPhone());
		pstmt.setString(7,sv.getMemberEmail());
		pstmt.setString(8,sv.getIp());
		
		value = pstmt.executeUpdate();	//쿼리를 실행시켜서 성공이면 1 실패면 0이 리턴
		System.out.println(value);
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
	


	public ArrayList<SchoolVo> schoolSelectAll() {
		ArrayList<SchoolVo> alist =new ArrayList<SchoolVo>();
		
		String sql = "select * from member1130 where delYn='N'order by midx desc";
		ResultSet rs = null;	//db의 데이터를 받기 위한 전용 클래스
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//처음에서 다음 행으로 이동해서 데이터가 있는지 체크해서 있으면 true
			while(rs.next()) {
				SchoolVo mv = new SchoolVo();
				mv.setMemberId(rs.getString("memberId"));  //rs에 담긴 값을 vo에 옮겨담는다
				mv.setMemberName(rs.getString("memberName"));
				mv.setWriteday(rs.getString("writeday"));
				mv.setIp(rs.getString("ip"));
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

	public ArrayList<NoticeVo> boardSelectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public SchoolVo memberLogin(String memberId,String memberPw) {
		
		
		ResultSet rs = null;
		String sql = "select memberId, memberName from member1130 where delyn='N' and memberid=? and memberpw=? ";
		SchoolVo mv = null;
		
		try {	//sql문장을 수행, 리턴값을 받음
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rs = pstmt.executeQuery();	//ResultSet:컬럼값을 담아오는 데이터 전용 클래스
			
			if(rs.next()) {//next():커서를 이동시켜서 이동한 행에 값이 있으면 true
				mv = new SchoolVo();
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

	

	 
	
	
}
