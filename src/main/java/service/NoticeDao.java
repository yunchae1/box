package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.BoardVo;
import Domain.Criteria;
import Domain.MemberVo;
import Domain.NoticeVo;
import dbconn.Dbconn;

public class NoticeDao {
	
	private Connection conn;	//멤버변수(전역기능) 객체변수(인스턴스변수)
	private PreparedStatement pstmt;	//멤버변수는 자동 초기화가 된다
	
	public NoticeDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnect();
	}
	
	public int boardInsert(NoticeVo nv) {
		int value = 0;
		
		String sql = "INSERT INTO BOARD1130(BIDX,ORIGINBIDX,DEPTH,LVL,SUBJECT,CONTENTS,WRITER,FILENAME,IP)"
				+ " VALUES(BIDX_SEQ.NEXTVAL,BIDX_SEQ.NEXTVAL,0,0,?,?,?,NULL,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nv.getSubject());
			pstmt.setString(2, nv.getContents());
			pstmt.setString(3, nv.getWriter());
			pstmt.setString(4, nv.getIp());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return value;
	}public int freeInsert(NoticeVo nv) {
		int value = 0;
		
		String sql = "INSERT INTO BOARD1205(BIDX,ORIGINBIDX,DEPTH,LVL,SUBJECT,CONTENTS,WRITER,FILENAME,IP)"
				+ " VALUES(BIDX_SEQ.NEXTVAL,BIDX_SEQ.NEXTVAL,0,0,?,?,?,NULL,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nv.getSubject());
			pstmt.setString(2, nv.getContents());
			pstmt.setString(3, nv.getWriter());
			pstmt.setString(4, nv.getIp());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return value;
	}

	public ArrayList<NoticeVo>noticeSelectAll(Criteria cri){
		//System.out.println("페이지번호는"+cri.getPage());
		//System.out.println("화면에 출력할 갯수는"+cri.getPerPageNum());
		
		ArrayList<NoticeVo> alist =new ArrayList<NoticeVo>();
		
		//String sql = "select * from Board1130 where delYn = 'N' order by originbidx desc, depth asc";
		String sql = "select * from("
				+ "select rownum as rnum, A.* from ("
				+ "select * from board1130 where delyn='N' order by originbidx desc, depth asc"
				+ ")A"
				+ ") where rnum between ? and ?";
		
		
		ResultSet rs = null;	//db의 데이터를 받기 위한 전용 클래스
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cri.getPage()-1)*cri.getPerPageNum()+1 );
			pstmt.setInt(2, cri.getPage()*cri.getPerPageNum());
			rs = pstmt.executeQuery();
			
			//처음에서 다음 행으로 이동해서 데이터가 있는지 체크해서 있으면 true
			while(rs.next()) { //커서 이동해서 데이터가 있으면 ture 없으면 false
				NoticeVo nv = new NoticeVo();
				nv.setBidx(rs.getInt("bidx"));
				nv.setSubject(rs.getString("subject"));
				nv.setContents(rs.getString("contents"));
				nv.setWriter(rs.getString("writer"));
				nv.setWriteday(rs.getString("writeday"));
				nv.setLvl(rs.getInt("lvl"));
				nv.setOriginbidx(rs.getInt("originbidx"));
				nv.setDepth(rs.getInt("depth"));
				alist.add(nv);
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
	}public ArrayList<NoticeVo>freeSelectAll(Criteria cri){
		//System.out.println("페이지번호는"+cri.getPage());
		//System.out.println("화면에 출력할 갯수는"+cri.getPerPageNum());
		
		ArrayList<NoticeVo> alist =new ArrayList<NoticeVo>();
		
		//String sql = "select * from Board1130 where delYn = 'N' order by originbidx desc, depth asc";
		String sql = "select * from("
				+ "select rownum as rnum, A.* from ("
				+ "select * from board1205 where delyn='N' order by originbidx desc, depth asc"
				+ ")A"
				+ ") where rnum between ? and ?";
		
		
		ResultSet rs = null;	//db의 데이터를 받기 위한 전용 클래스
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cri.getPage()-1)*cri.getPerPageNum()+1 );
			pstmt.setInt(2, cri.getPage()*cri.getPerPageNum());
			rs = pstmt.executeQuery();
			
			//처음에서 다음 행으로 이동해서 데이터가 있는지 체크해서 있으면 true
			while(rs.next()) { //커서 이동해서 데이터가 있으면 ture 없으면 false
				NoticeVo nv = new NoticeVo();
				nv.setBidx(rs.getInt("bidx"));
				nv.setSubject(rs.getString("subject"));
				nv.setContents(rs.getString("contents"));
				nv.setWriter(rs.getString("writer"));
				nv.setWriteday(rs.getString("writeday"));
				nv.setLvl(rs.getInt("lvl"));
				nv.setOriginbidx(rs.getInt("originbidx"));
				nv.setDepth(rs.getInt("depth"));
				alist.add(nv);
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
	
	public NoticeVo boardSeletOne(int bidx) {
		System.out.println("test");
		NoticeVo nv = null;
		ResultSet rs = null;
		String sql="select * from board1130 where delyn='N' and bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //커서 이동해서 데이터가 있으면 ture 없으면 false
				nv = new NoticeVo();
				nv.setSubject(rs.getString("subject"));
				nv.setContents(rs.getString("contents"));
				nv.setWriter(rs.getString("writer"));
				nv.setWriteday(rs.getString("writeday"));
				nv.setBidx(rs.getInt("bidx"));
				nv.setOriginbidx(rs.getInt("originbidx"));
				nv.setDepth(rs.getInt("depth"));
				nv.setLvl(rs.getInt("lvl"));
				
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
		return nv;
	
	}public NoticeVo freeSeletOne(int bidx) {
		System.out.println("test");
		NoticeVo nv = null;
		ResultSet rs = null;
		String sql="select * from board1205 where delyn='N' and bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //커서 이동해서 데이터가 있으면 ture 없으면 false
				nv = new NoticeVo();
				nv.setSubject(rs.getString("subject"));
				nv.setContents(rs.getString("contents"));
				nv.setWriter(rs.getString("writer"));
				nv.setWriteday(rs.getString("writeday"));
				nv.setBidx(rs.getInt("bidx"));
				nv.setOriginbidx(rs.getInt("originbidx"));
				nv.setDepth(rs.getInt("depth"));
				nv.setLvl(rs.getInt("lvl"));
				
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
		return nv;
	
	}
	
	public int boardUpdate(NoticeVo nv) {
		int value=0;
		
		String sql = "Update board1130 set subject=?,contents=?, writer=?,ip=?,"
				+"writeday=sysdate where bidx=?";
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nv.getSubject());
			pstmt.setString(2, nv.getContents());
			pstmt.setString(3, nv.getWriter());
			pstmt.setString(4, nv.getIp());
			pstmt.setInt(5, nv.getBidx());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return value;
		
	}public int freeUpdate(NoticeVo nv) {
		int value=0;
		
		String sql = "Update board1205 set subject=?,contents=?, writer=?,ip=?,"
				+"writeday=sysdate where bidx=?";
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nv.getSubject());
			pstmt.setString(2, nv.getContents());
			pstmt.setString(3, nv.getWriter());
			pstmt.setString(4, nv.getIp());
			pstmt.setInt(5, nv.getBidx());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return value;
		
	}public int boardDelete(NoticeVo nv) {
		int value=0;
		
		String sql = "Update board1130 set delyn= 'Y', writeday=sysdate where bidx=?and pwd=?";
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nv.getBidx());
			pstmt.setString(2, nv.getPwd());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	
	
	}public int freeDelete(NoticeVo nv) {
		int value=0;
		
		String sql = "Update board1205 set delyn= 'Y', writeday=sysdate where bidx=?and pwd=?";
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nv.getBidx());
			pstmt.setString(2, nv.getPwd());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	
	
	}public int boardRelpy(NoticeVo nv) {
		System.out.println(nv.getOriginbidx());
		System.out.println(nv.getDepth());
		System.out.println(nv.getLvl());
		System.out.println(nv.getSubject());
		System.out.println(nv.getContents());
		System.out.println(nv.getWriter());
		System.out.println(nv.getIp());
		System.out.println(nv.getPwd());
		
		int value= 0 ; 
		int value2= 0;
		String sql="update board1130 set depth =depth+1 where originbidx = ? and depth > ?";
		String sql2 = "insert into board1130(bidx,originbidx,depth,lvl,subject,contents,writer,filename,ip,pwd)"
						+ " values(bidx_seq.nextval,?,?,?,?,?,?,null,?,?)";
		
		try {
			conn.setAutoCommit(false); //자동커밋 기능을 수동으로 변경한다
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nv.getOriginbidx());
			pstmt.setInt(2, nv.getDepth());
			value = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, nv.getOriginbidx());
			pstmt.setInt(2, nv.getDepth()+1);
			pstmt.setInt(3, nv.getLvl()+1);		
			pstmt.setString(4, nv.getSubject());
			pstmt.setString(5, nv.getContents());
			pstmt.setString(6, nv.getWriter());
			pstmt.setString(7, nv.getIp());
			pstmt.setString(8, nv.getPwd());
			value2 = pstmt.executeUpdate();	
			
			conn.commit(); //트랜잭션 일괄 실행
			
		} catch (SQLException e) { //에러발생
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
		
		return value2;
	}

	public int boardTatalCount() {
		int value = 0;
		
		String sql = "select count(*) as cnt from board1130 where delyn='N'";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				value = rs.getInt("cnt");
			}
		}catch (SQLException e) { //에러발생
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return value;
	}
	
	
}