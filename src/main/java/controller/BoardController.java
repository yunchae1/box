package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.BoardVo;
import Domain.Criteria;
import Domain.MemberVo;
import Domain.NoticeVo;
import Domain.PageMaker;
import Domain.SchoolVo;
import service.BoardDao;
import service.NoticeDao;


	@WebServlet("/BoardController")
	public class BoardController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		//전역변수로 MemberDao 객체를 쓰게 한다
		private BoardDao bd;
		private NoticeDao nd;
		private String action;
		
		public BoardController(String url) {
			bd = new BoardDao();
			nd = new NoticeDao();
			this.action = url;
		}
	       
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doRun(request,response);
		}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doRun(request,response);
		}
		
		private void doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			//한글 깨짐 방지
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			
			System.out.println("action-->"+action);
			
			if(action.equals("boardWrite.do")) {
			
				//실제 내부 페이지로 이동시킨다
				String path = "/board/boardWrite.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
				
			}else if(action.equals("boardWriteAction.do")) {
				
				//1.데이터를 넘겨받는다
				String subject = request.getParameter("subject");
				System.out.println("subject"+subject);
				String contnets = request.getParameter("contents");
				String writer = request.getParameter("writer");
				String ip = InetAddress.getLocalHost().getHostAddress();
				
				BoardVo bv = new BoardVo();
				bv.setSubject(subject);
				bv.setContents(contnets);
				bv.setWriter(writer);
				bv.setIp(ip);
				
				//2.테이블에 입력하는 처리를 진행한다
				int value = bd.boardInsert(bv); //입력성공1 입력실패0
				System.out.println("value"+value);
				
				//3.처리가 되었으면 게시판 목록으로 이동한다
				
				String spath="";
				if(value==1) {	//성공했을시 이동경로
					spath = request.getContextPath()+"/board/boardList.do";
					
				}else {	//실패시
					spath = request.getContextPath()+"/board/boardWrite.do";
					
				}
				response.sendRedirect(spath);
			}else if(action.equals("boardList.do")) {	//가상경로 boardList.do로 넘어간다
				String page = request.getParameter("page"); //페이지 번호 넘겨줌
				if(page == null) {
					page = "1";	//값이 없으면 페이지번호
				}
				
				Criteria cri= new Criteria();
				cri.setPage(Integer.parseInt(page));
				
				PageMaker pm = new PageMaker();
				System.out.println("총 갯수"+nd.boardTatalCount());
				pm.setCri(cri);
				pm.setTotalCount(nd.boardTatalCount());
				System.out.println("전체갯수"+pm.getTotalCount());
				
				
				//db의 회원목록을 가져올 dao메소드를 호출해서 데이터를 가져온다
				ArrayList<BoardVo> alist = bd.boardSelectAll(cri);
				request.setAttribute("alist",alist);	//alist이름으로 alist 객체를 담는다
				request.setAttribute("pm", pm);
				
				//실제 내부 페이지로 이동시킨다
				String path = "/board/boardList.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path); //get방식으로 넘겨받음
				rd.forward(request, response);
				
			
				
			}else if(action.equals("boardContents.do")) {	//가상경로 boardContents.do로 넘어간다
				
				//1.넘어온 키값을
				String bidx = request.getParameter("bidx");
				int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
				
				//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
				BoardVo bv = bd.boardSeletOne(bidx_);
				
				//3.내부이동하는 페이지를 값을 담아서 가져간다
				request.setAttribute("bv", bv);
				
				//실제 내부 페이지로 이동시킨다
				String path = "/board/boardContents.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
				
			}else if(action.equals("boardModify.do")) {	//가상경로 boardWrite.do로 넘어간다
				System.out.println("test");
				//1.넘어온 키값을
				String bidx = request.getParameter("bidx");
				int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
				
				//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
				BoardVo bv = bd.boardSeletOne(bidx_);
				
				//3.내부이동하는 페이지를 값을 담아서 가져간다
				request.setAttribute("bv", bv);
				
				//실제 내부 페이지로 이동시킨다
				String path = "/board/boardModify.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
				
			}else if(action.equals("boardModifyAction.do")) {
				
				String subject = request.getParameter("subject");	//input객체 변수값을 변수에 담는다
				System.out.println("subject"+subject);
				String contnets = request.getParameter("contents");
				String writer = request.getParameter("writer");
				String bidx = request.getParameter("bidx");
				int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
				String ip = InetAddress.getLocalHost().getHostAddress();
				
				BoardVo bv = new BoardVo();
				bv.setSubject(subject);
				bv.setContents(contnets);
				bv.setWriter(writer);
				bv.setIp(ip);
				bv.setBidx(bidx_);
				
				int value = bd.boardUpdate(bv);
				
				String spath="";
				if(value==1) {	//성공했을시 이동경로
					spath = request.getContextPath()+"/board/boardContents.do?bidx="+bidx;
					
				}else {	//실패시
					spath = request.getContextPath()+"/board/boardModify.do?bidx="+bidx;
					
				}
				response.sendRedirect(spath);
				System.out.println("value"+value);
				
			}else if(action.equals("boardDelete.do")) {	//가상경로 boardWrite.do로 넘어간다
				
				//1.넘어온 키값을
				String bidx = request.getParameter("bidx");
				int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
				
				//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
				BoardVo bv = bd.boardSeletOne(bidx_);
				
				//3.내부이동하는 페이지를 값을 담아서 가져간다
				request.setAttribute("bidx", bidx);
				
				//실제 내부 페이지로 이동시킨다
				String path = "/board/boardDelete.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
				
			}else if(action.equals("boardDeleteAction.do")) {
			
				System.out.println("test");
				String pwd = request.getParameter("pwd");	//input객체 변수값을 변수에 담는다
				System.out.println("pwd"+pwd);
				String bidx = request.getParameter("bidx");
				int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
				
				BoardVo bv = new BoardVo();
				bv.setPwd(pwd);
				bv.setBidx(bidx_);
			
				int value = bd.boardDelete(bv);
				//입력성공1 입력실패0
				System.out.println("value"+value);
				
				String spath="";
				if(value==1) {	//성공했을시 이동경로
				spath = request.getContextPath()+"/board/boardList.do?bidx="+bidx;
				
			}else {	//실패시
				spath = request.getContextPath()+"/board/boardDelete.do?bidx="+bidx;
				
			}
			response.sendRedirect(spath);
			
			
		}else if(action.equals("boardReply.do")) {	//가상경로 boardWrite.do로 넘어간다
			
			//1.넘어온 키값을
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
			String originbidx = request.getParameter("originbidx");
			int originbidx_ = Integer.parseInt(originbidx);//문자형을 숫자형으로 변환
			String depth = request.getParameter("depth");
			int depth_ = Integer.parseInt(depth);//문자형을 숫자형으로 변환
			String lvl = request.getParameter("lvl");
			int lvl_ = Integer.parseInt(lvl);//문자형을 숫자형으로 변환
			
			
			//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
			BoardVo bv = new BoardVo();
			bv.setBidx(bidx_);
			bv.setOriginbidx(originbidx_);
			bv.setDepth(depth_);
			bv.setLvl(lvl_);
			
			//3.내부이동하는 페이지를 값을 담아서 가져간다
			request.setAttribute("bv", bv);
			
			//실제 내부 페이지로 이동시킨다
			String path = "/board/boardReply.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("boardReplyAction.do")) {
			
			//1.데이터를 넘겨받는다
			String subject = request.getParameter("subject");
			System.out.println("subject"+subject);
			String Contents = request.getParameter("contents");
			System.out.println("contents"+Contents);
			String Writer = request.getParameter("writer");
			System.out.println("writer"+Writer);
			String ip = InetAddress.getLocalHost().getHostAddress();
			String pwd =request.getParameter("pwd");
			
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			String originbidx = request.getParameter("originbidx");
			int originbidx_ = Integer.parseInt(originbidx);
			String depth = request.getParameter("depth");
			int depth_ = Integer.parseInt(depth);
			String lvl = request.getParameter("lvl");
			int lvl_ = Integer.parseInt(lvl);
			
			
			BoardVo bv = new BoardVo();
			bv.setSubject(subject);
			bv.setContents(Contents);
			bv.setWriter(Writer);
			bv.setIp(ip);
			bv.setBidx(bidx_);
			bv.setOriginbidx(originbidx_);
			bv.setDepth(depth_);
			bv.setLvl(lvl_);
			bv.setPwd(pwd);
			
			
			//2.테이블에 입력하는 처리를 진행한다
			int value = bd.boardRelpy(bv); //입력성공1 입력실패0
			System.out.println("value"+value);
			
			//3.처리가 되었으면 게시판 목록으로 이동한다
			
			String spath="";
			if(value==1) {	//성공했을시 이동경로
				spath = request.getContextPath()+"/board/boardList.do";
				
			}else {	//실패시
				spath=request.getContextPath()+"/board/boardReply.do?bidx="+bidx+"&originbidx="+originbidx+"&depth="+depth+"&lvl="+lvl;
				
			}
			response.sendRedirect(spath);
			
		}else if(action.equals("Notice.do")) {	//가상경로 Notice.do로 넘어간다
			String page = request.getParameter("page"); //페이지 번호 넘겨줌
			if(page == null) {
				page = "1";	//값이 없으면 페이지번호
			}
			
			Criteria cri= new Criteria();
			cri.setPage(Integer.parseInt(page));
			
			PageMaker pm = new PageMaker();
			System.out.println("총 갯수"+nd.boardTatalCount());
			pm.setCri(cri);
			pm.setTotalCount(nd.boardTatalCount());
			System.out.println("전체갯수"+pm.getTotalCount());
			
			//db의 회원목록을 가져올 dao메소드를 호출해서 데이터를 가져온다
			ArrayList<NoticeVo> alist = nd.noticeSelectAll(cri);
			request.setAttribute("alist",alist);	//alist이름으로 alist 객체를 담는다
			request.setAttribute("pm", pm);
			
			//실제 내부 페이지로 이동시킨다
			String path = "/board/notice.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path); //get방식으로 넘겨받음
			rd.forward(request, response);
			
		
			
		}else if(action.equals("schoolContents.do")) {	//가상경로 schoolContents.do로 넘어간다
			
			//1.넘어온 키값을
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
			
			//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
			NoticeVo nv = nd.boardSeletOne(bidx_);
			
			//3.내부이동하는 페이지를 값을 담아서 가져간다
			request.setAttribute("nv", nv);
			
			//실제 내부 페이지로 이동시킨다
			String path = "/board/schoolContents.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}if(action.equals("schoolWrite.do")) {
			
			//실제 내부 페이지로 이동시킨다
			String path = "/board/schoolWrite.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("schoolWriteAction.do")) {
			
			//1.데이터를 넘겨받는다
			String subject = request.getParameter("subject");
			System.out.println("subject"+subject);
			String contnets = request.getParameter("contents");
			String writer = request.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			NoticeVo nv = new NoticeVo();
			nv.setSubject(subject);
			nv.setContents(contnets);
			nv.setWriter(writer);
			nv.setIp(ip);
			
			//2.테이블에 입력하는 처리를 진행한다
			int value = nd.boardInsert(nv); //입력성공1 입력실패0
			System.out.println("value"+value);
			
			//3.처리가 되었으면 게시판 목록으로 이동한다
			
			String spath="";
			if(value==1) {	//성공했을시 이동경로
				spath = request.getContextPath()+"/board/Notice.do";
				
			}else {	//실패시
				spath = request.getContextPath()+"/board/schoolWrite.do";
				
			}
			response.sendRedirect(spath);
		}else if(action.equals("schoolModify.do")) {	//가상경로 schoolModify.do로 넘어간다
			System.out.println("test");
			//1.넘어온 키값을
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
			
			//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
			NoticeVo nv = nd.boardSeletOne(bidx_);
			
			//3.내부이동하는 페이지를 값을 담아서 가져간다
			request.setAttribute("nv", nv);
			
			//실제 내부 페이지로 이동시킨다
			String path = "/board/schoolModify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("schoolModifyAction.do")) {
			
			String subject = request.getParameter("subject");	//input객체 변수값을 변수에 담는다
			System.out.println("subject"+subject);
			String contnets = request.getParameter("contents");
			String writer = request.getParameter("writer");
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			NoticeVo nv = new NoticeVo();
			nv.setSubject(subject);
			nv.setContents(contnets);
			nv.setWriter(writer);
			nv.setIp(ip);
			nv.setBidx(bidx_);
			
			int value = nd.boardUpdate(nv);
			
			String spath="";
			if(value==1) {	//성공했을시 이동경로
				spath = request.getContextPath()+"/board/schoolContents.do?bidx="+bidx;
				
			}else {	//실패시
				spath = request.getContextPath()+"/board/schoolModify.do?bidx="+bidx;
				
			}
			response.sendRedirect(spath);
			System.out.println("value"+value);
			
		}else if(action.equals("schoolDelete.do")) {	//가상경로 schoolDelete.do로 넘어간다
			
			//1.넘어온 키값을
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
			
			//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
			NoticeVo bv = nd.boardSeletOne(bidx_);
			
			//3.내부이동하는 페이지를 값을 담아서 가져간다
			request.setAttribute("bidx", bidx);
			
			//실제 내부 페이지로 이동시킨다
			String path = "/board/schoolDelete.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("schoolDeleteAction.do")) {
		
			System.out.println("test");
			String pwd = request.getParameter("pwd");	//input객체 변수값을 변수에 담는다
			System.out.println("pwd"+pwd);
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
			
			NoticeVo nv = new NoticeVo();
			nv.setPwd(pwd);
			nv.setBidx(bidx_);
		
			int value = nd.boardDelete(nv);
			//입력성공1 입력실패0
			System.out.println("value"+value);
			
			String spath="";
			if(value==1) {	//성공했을시 이동경로
			spath = request.getContextPath()+"/board/Notice.do?bidx="+bidx_;
			
		}else {	//실패시
			spath = request.getContextPath()+"/board/schoolDelete.do?bidx="+bidx;
			
		}
		response.sendRedirect(spath);
		
		
	}else if(action.equals("Free.do")) {	//가상경로 Free.do로 넘어간다
		String page = request.getParameter("page"); //페이지 번호 넘겨줌
		if(page == null) {
			page = "1";	//값이 없으면 페이지번호
		}
		
		Criteria cri= new Criteria();
		cri.setPage(Integer.parseInt(page));
		
		PageMaker pm = new PageMaker();
		System.out.println("총 갯수"+nd.boardTatalCount());
		pm.setCri(cri);
		pm.setTotalCount(nd.boardTatalCount());
		System.out.println("전체갯수"+pm.getTotalCount());
		
		//db의 회원목록을 가져올 dao메소드를 호출해서 데이터를 가져온다
		ArrayList<NoticeVo> alist = nd.freeSelectAll(cri);
		request.setAttribute("alist",alist);	//alist이름으로 alist 객체를 담는다
		request.setAttribute("pm", pm);
		
		//실제 내부 페이지로 이동시킨다
		String path = "/board/free.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path); //get방식으로 넘겨받음
		rd.forward(request, response);
		
	
		
	}else if(action.equals("schoolContents2.do")) {	//가상경로 schoolContents2.do로 넘어간다
		
		//1.넘어온 키값을
		String bidx = request.getParameter("bidx");
		int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
		
		//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
		NoticeVo nv = nd.freeSeletOne(bidx_);
		
		//3.내부이동하는 페이지를 값을 담아서 가져간다
		request.setAttribute("nv", nv);
		
		//실제 내부 페이지로 이동시킨다
		String path = "/board/schoolContents2.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}if(action.equals("schoolWrite2.do")) {
		System.out.println("schoolWrite2입니다.");
		//실제 내부 페이지로 이동시킨다
		String path = "/board/schoolWrite2.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}else if(action.equals("schoolWrite2Action.do")) {
		System.out.println("schoolWrite2Action입니다.");
		//1.데이터를 넘겨받는다
		String subject = request.getParameter("subject");
		System.out.println("subject"+subject);
		String contnets = request.getParameter("contents");
		String writer = request.getParameter("writer");
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		NoticeVo nv = new NoticeVo();
		nv.setSubject(subject);
		nv.setContents(contnets);
		nv.setWriter(writer);
		nv.setIp(ip);
		
		//2.테이블에 입력하는 처리를 진행한다
		int value = nd.freeInsert(nv); //입력성공1 입력실패0
		System.out.println("value"+value);
		
		//3.처리가 되었으면 게시판 목록으로 이동한다
		
		String spath="";
		if(value==1) {	//성공했을시 이동경로
			spath = request.getContextPath()+"/board/Free.do";
			
		}else {	//실패시
			spath = request.getContextPath()+"/board/schoolWrite2.do";
			
		}
		response.sendRedirect(spath);
	}else if(action.equals("schoolModify2.do")) {	//가상경로 schoolModify.do로 넘어간다
		System.out.println("test");
		//1.넘어온 키값을
		String bidx = request.getParameter("bidx");
		int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
		
		//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
		NoticeVo nv = nd.freeSeletOne(bidx_);
		
		//3.내부이동하는 페이지를 값을 담아서 가져간다
		request.setAttribute("nv", nv);
		
		//실제 내부 페이지로 이동시킨다
		String path = "/board/schoolModify2.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}else if(action.equals("schoolModify2Action.do")) {
		
		String subject = request.getParameter("subject");	//input객체 변수값을 변수에 담는다
		System.out.println("subject"+subject);
		String contnets = request.getParameter("contents");
		String writer = request.getParameter("writer");
		String bidx = request.getParameter("bidx");
		int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		NoticeVo nv = new NoticeVo();
		nv.setSubject(subject);
		nv.setContents(contnets);
		nv.setWriter(writer);
		nv.setIp(ip);
		nv.setBidx(bidx_);
		
		int value = nd.freeUpdate(nv);
		
		String spath="";
		if(value==1) {	//성공했을시 이동경로
			spath = request.getContextPath()+"/board/schoolContents2.do?bidx="+bidx;
			
		}else {	//실패시
			spath = request.getContextPath()+"/board/schoolModify2.do?bidx="+bidx;
			
		}
		response.sendRedirect(spath);
		System.out.println("value"+value);
		
	}else if(action.equals("schoolDelete2.do")) {	//가상경로 schoolDelete.do로 넘어간다
		
		//1.넘어온 키값을
		String bidx = request.getParameter("bidx");
		int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
		
		//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
		NoticeVo bv = nd.freeSeletOne(bidx_);
		
		//3.내부이동하는 페이지를 값을 담아서 가져간다
		request.setAttribute("bidx", bidx);
		
		//실제 내부 페이지로 이동시킨다
		String path = "/board/schoolDelete2.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}else if(action.equals("schoolDelete2Action.do")) {
	
		System.out.println("test");
		String pwd = request.getParameter("pwd");	//input객체 변수값을 변수에 담는다
		System.out.println("pwd"+pwd);
		String bidx = request.getParameter("bidx");
		int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
		
		NoticeVo nv = new NoticeVo();
		nv.setPwd(pwd);
		nv.setBidx(bidx_);
	
		int value = nd.freeDelete(nv);
		//입력성공1 입력실패0
		System.out.println("value"+value);
		
		String spath="";
		if(value==1) {	//성공했을시 이동경로
		spath = request.getContextPath()+"/board/Free.do?bidx="+bidx_;
		
	}else {	//실패시
		spath = request.getContextPath()+"/board/schoolDelete2.do?bidx="+bidx;
		
	}
	response.sendRedirect(spath);
	
	
}else if(action.equals("schoolReply.do")) {	//가상경로 schoolReply.do로 넘어간다
	
	//1.넘어온 키값을
	String bidx = request.getParameter("bidx");
	int bidx_ = Integer.parseInt(bidx);//문자형을 숫자형으로 변환
	String originbidx = request.getParameter("originbidx");
	int originbidx_ = Integer.parseInt(originbidx);//문자형을 숫자형으로 변환
	String depth = request.getParameter("depth");
	int depth_ = Integer.parseInt(depth);//문자형을 숫자형으로 변환
	String lvl = request.getParameter("lvl");
	int lvl_ = Integer.parseInt(lvl);//문자형을 숫자형으로 변환
	
	
	//2. 키값을 통해서 생성된 메소드 호출해서 데이터를 가져온다
	NoticeVo nv = new NoticeVo();
	nv.setBidx(bidx_);
	nv.setOriginbidx(originbidx_);
	nv.setDepth(depth_);
	nv.setLvl(lvl_);
	
	//3.내부이동하는 페이지를 값을 담아서 가져간다
	request.setAttribute("nv", nv);
	
	//실제 내부 페이지로 이동시킨다
	String path = "/board/schoolReply.jsp";
	RequestDispatcher rd = request.getRequestDispatcher(path);
	rd.forward(request, response);
	
}else if(action.equals("schoolReplyAction.do")) {
	
	//1.데이터를 넘겨받는다
	String subject = request.getParameter("subject");
	System.out.println("subject"+subject);
	String Contents = request.getParameter("contents");
	System.out.println("contents"+Contents);
	String Writer = request.getParameter("writer");
	System.out.println("writer"+Writer);
	String ip = InetAddress.getLocalHost().getHostAddress();
	String pwd =request.getParameter("pwd");
	
	String bidx = request.getParameter("bidx");
	int bidx_ = Integer.parseInt(bidx);
	String originbidx = request.getParameter("originbidx");
	int originbidx_ = Integer.parseInt(originbidx);
	String depth = request.getParameter("depth");
	int depth_ = Integer.parseInt(depth);
	String lvl = request.getParameter("lvl");
	int lvl_ = Integer.parseInt(lvl);
	
	
	NoticeVo nv = new NoticeVo();
	nv.setSubject(subject);
	nv.setContents(Contents);
	nv.setWriter(Writer);
	nv.setIp(ip);
	nv.setBidx(bidx_);
	nv.setOriginbidx(originbidx_);
	nv.setDepth(depth_);
	nv.setLvl(lvl_);
	nv.setPwd(pwd);
	
	
	//2.테이블에 입력하는 처리를 진행한다
	int value = nd.boardRelpy(nv); //입력성공1 입력실패0
	System.out.println("value"+value);
	
	//3.처리가 되었으면 게시판 목록으로 이동한다
	
	String spath="";
	if(value==1) {	//성공했을시 이동경로
		spath = request.getContextPath()+"/board/Free.do";
		
	}else {	//실패시
		spath=request.getContextPath()+"/board/schoolReply.do?bidx="+bidx+"&originbidx="+originbidx+"&depth="+depth+"&lvl="+lvl;
		
	}
	response.sendRedirect(spath);
	
}

}
}

