package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.BoardVo;
import Domain.Criteria;
import Domain.MemberVo;
import dbconn.Dbconn;

public class BoardDao {
	
	private Connection conn;	//멤버변수(전역기능) 객체변수(인스턴스변수)
	private PreparedStatement pstmt;	//멤버변수는 자동 초기화가 된다
	
	public BoardDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnect();
	}
	
	public int boardInsert(BoardVo bv) {
		int value = 0;
		
		String sql = "INSERT INTO BOARD0919(BIDX,ORIGINBIDX,DEPTH,LVL,SUBJECT,CONTENTS,WRITER,FILENAME,IP)"
				+ " VALUES(BIDX_SEQ.NEXTVAL,BIDX_SEQ.NEXTVAL,0,0,?,?,?,NULL,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getSubject());
			pstmt.setString(2, bv.getContents());
			pstmt.setString(3, bv.getWriter());
			pstmt.setString(4, bv.getIp());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return value;
	}

	public ArrayList<BoardVo>boardSelectAll(Criteria cri){
		System.out.println("페이지번호는"+cri.getPage());
		System.out.println("화면에 출력할 갯수는"+cri.getPerPageNum());
		
		ArrayList<BoardVo> alist =new ArrayList<BoardVo>();
		
		//String sql = "select * from Board0919 where delYn = 'N' order by originbidx desc, depth asc";
		String sql = "select * from("
				+ "select rownum as rnum, A.* from ("
				+ "select * from board0919 where delyn='N' order by originbidx desc, depth asc"
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
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("bidx"));
				bv.setSubject(rs.getString("subject"));
				bv.setContents(rs.getString("contents"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setLvl(rs.getInt("lvl"));
				bv.setOriginbidx(rs.getInt("originbidx"));
				bv.setDepth(rs.getInt("depth"));
				alist.add(bv);
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
	
	public BoardVo boardSeletOne(int bidx) {
		System.out.println("test");
		BoardVo bv = null;
		ResultSet rs = null;
		String sql="select * from board0919 where delyn='N' and bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //커서 이동해서 데이터가 있으면 ture 없으면 false
				bv = new BoardVo();
				bv.setSubject(rs.getString("subject"));
				bv.setContents(rs.getString("contents"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setBidx(rs.getInt("bidx"));
				bv.setOriginbidx(rs.getInt("originbidx"));
				bv.setDepth(rs.getInt("depth"));
				bv.setLvl(rs.getInt("lvl"));
				
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
		return bv;
	
	}
	
	public int boardUpdate(BoardVo bv) {
		int value=0;
		
		String sql = "Update board0919 set subject=?,contents=?, writer=?,ip=?,"
				+"writeday=sysdate where bidx=?";
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getSubject());
			pstmt.setString(2, bv.getContents());
			pstmt.setString(3, bv.getWriter());
			pstmt.setString(4, bv.getIp());
			pstmt.setInt(5, bv.getBidx());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return value;
		
	}public int boardDelete(BoardVo bv) {
		int value=0;
		
		String sql = "Update board0919 set delyn= 'Y', writeday=sysdate where bidx=?and pwd=?";
		
		try{
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bv.getBidx());
			pstmt.setString(2, bv.getPwd());
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	
	
	}public int boardRelpy(BoardVo bv) {
		System.out.println(bv.getOriginbidx());
		System.out.println(bv.getDepth());
		System.out.println(bv.getLvl());
		System.out.println(bv.getSubject());
		System.out.println(bv.getContents());
		System.out.println(bv.getWriter());
		System.out.println(bv.getIp());
		System.out.println(bv.getPwd());
		
		int value= 0 ; 
		int value2= 0;
		String sql="update board0919 set depth =depth+1 where originbidx = ? and depth > ?";
		String sql2 = "insert into board0919(bidx,originbidx,depth,lvl,subject,contents,writer,filename,ip,pwd)"
						+ " values(bidx_seq.nextval,?,?,?,?,?,?,null,?,?)";
		
		try {
			conn.setAutoCommit(false); //자동커밋 기능을 수동으로 변경한다
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth());
			value = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth()+1);
			pstmt.setInt(3, bv.getLvl()+1);		
			pstmt.setString(4, bv.getSubject());
			pstmt.setString(5, bv.getContents());
			pstmt.setString(6, bv.getWriter());
			pstmt.setString(7, bv.getIp());
			pstmt.setString(8, bv.getPwd());
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
		
		String sql = "select count(*) as cnt from board0919 where delyn='N'";
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