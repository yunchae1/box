package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Domain.BoardVo;
import Domain.MemberVo;
import Domain.SchoolVo;
import service.MemberDao;
import service.SchoolDao;


@WebServlet("/MemberController")	//xml을 안만들어도됌
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//전역변수로 MemberDao 객체를 쓰게 한다
	private MemberDao md;
	private SchoolDao sd;
	private String action;

	private char[] memberId;
	
	public MemberController(String url) {
		md = new MemberDao();	//객체 자동 생성
		sd = new SchoolDao();
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
		
		
		if(action.equals("memberJoinAction.do")) {
			
			String memberId = request.getParameter("memberId");
			String memberPW = request.getParameter("memberPW");
			String memberName = request.getParameter("memberName");
			String memberBirth = request.getParameter("memberBirth");
			String memberGender = request.getParameter("memberGender");
			String memberAddr = request.getParameter("memberAddr");
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");
			String memberIp = InetAddress.getLocalHost().getHostAddress();
			
	
			
			MemberVo mv = new MemberVo();
			mv.setMemberId(memberId);
			mv.setMemberPW(memberPW);
			mv.setMemberName(memberName);
			mv.setMemberBirth(memberBirth);
			mv.setMemberGender(memberGender);
			mv.setMemberAddr(memberAddr);
			mv.setMemberPhone(memberPhone);
			mv.setMemberEmail(memberEmail);
			mv.setIp(memberIp);
			
			
			md.memberInsert(mv);
			
			//모든 처리가 끝났으면 외부주소를 이동시킨다
			String spath = request.getContextPath()+"/memberr/memberList.do";
			response.sendRedirect(spath);
			
		}else if(action.equals("memberJoin.do")) {
			System.out.println("memberJoin입니다.");
			
			//meberjoin.do로 넘어오면 내부페이지 이동
			String path = "/memberr/memberJoin.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("memberList.do")) {	//가상경로 memberList.do로 넘어간다
			
			//db의 회원목록을 가져올 dao메소드를 호출해서 데이터를 가져온다
			ArrayList<MemberVo> alist = md.memberSelectAll();
			
			request.setAttribute("alist",alist);	//alist이름으로 alist 객체를 담는다
			
			
			//실제 내부 페이지로 이동시킨다
			String path = "/memberr/memberList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("memberLogin.do")) {	//가상경로 memberLogin.do로 넘어간다
			System.out.println("memberLogin입니다.");
			
			//meberjoin.do로 넘어오면 내부페이지 이동
			String path = "/memberr/memberLogin.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);	//내부페이지만 그대로 이동
			
		}else if (action.equals("memberLoginAction.do")) {
			System.out.println("memberLoginAction입니다.");
			//1.파라미터로 넘어온 값을 받는다
			String memberId = request.getParameter("memberId");
			
			String memberPw = request.getParameter("memberPw");	
			
			//2.로그인 체그 메소드를 만들어서 호출
			//객체 안의 메소드 호출
			MemberVo mv = md.memberLogin(memberId, memberPw);			
			//System.out.println("회원이름?"+mv.getMemberName());
			//System.out.println("회원아이디?"+mv.getMemberId());
			
			//3.로그인이 되있다면 메인페이지도 이동
			String spath = "";
			if (mv == null) {				
				spath=request.getContextPath()+"/memberr/memberLogin.do";	
				
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("mv", mv);
				
				spath=request.getContextPath()+"/";							
			}	
			response.sendRedirect(spath);	
			
		}else if (action.equals("memberLogOut.do")) {
			System.out.println("memberLogOut입니다.");
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			String spath = "";
			spath=request.getContextPath()+"/";	
			response.sendRedirect(spath);
			
		}else if(action.equals("memberIdCheck.do")) {
			
			String memberId = request.getParameter("memberId");
			System.out.println("memberId"+memberId);
			
			int value = md.memberIdCheck(memberId);	//결과값
			
			
			PrintWriter out = response.getWriter();
			String result = "{ \"idYn\": \"  "+value+"\" }";	//재이슨 파일형태로 출력
			out.println(result);
		}
	
		
		
		System.out.println("action-->"+action);
			if(action.equals("schoolJoinAction.do")) {
				
			
			String memberId = request.getParameter("memberId");
			System.out.println(memberId);
			String memberPW = request.getParameter("memberPW");
			String memberName = request.getParameter("memberName");
			String memberBirth = request.getParameter("memberBirth");
			String memberAddr = request.getParameter("memberAddr");
			String memberPhone = request.getParameter("memberPhone");
			String memberEmail = request.getParameter("memberEmail");
			String memberIp = InetAddress.getLocalHost().getHostAddress();
			
	
			
			SchoolVo sv = new SchoolVo();
			sv.setMemberId(memberId);
			sv.setMemberPW(memberPW);
			sv.setMemberName(memberName);
			sv.setMemberBirth(memberBirth);
			sv.setMemberAddr(memberAddr);
			sv.setMemberPhone(memberPhone);
			sv.setMemberEmail(memberEmail);
			sv.setIp(memberIp);
			
			
			sd.memberInsert(sv);
			
			//모든 처리가 끝났으면 외부주소를 이동시킨다
			String spath = request.getContextPath()+"/memberr/schoolList.do";
			response.sendRedirect(spath);
			
		}else if(action.equals("schoolJoin.do")) {
			System.out.println("schoolJoin입니다.");
			
			//meberjoin.do로 넘어오면 내부페이지 이동
			String path = "/memberr/schoolJoin.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("schoolList.do")) {	//가상경로 schoolList.do로 넘어간다
			System.out.println("schoolList입니다.");
			//db의 회원목록을 가져올 dao메소드를 호출해서 데이터를 가져온다
			ArrayList<SchoolVo> alist = sd.schoolSelectAll();
			
			request.setAttribute("alist",alist);	//alist이름으로 alist 객체를 담는다
			
			
			//실제 내부 페이지로 이동시킨다
			String path = "/memberr/schoolList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("schoolLogin.do")) {	//가상경로 memberLogin.do로 넘어간다
			
			//meberjoin.do로 넘어오면 내부페이지 이동
			String path = "/memberr/schoolLogin.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if (action.equals("schoolLoginAction.do")) {
			System.out.println("schoolLoginAction입니다.");
			//1.파라미터로 넘어온 값을 받는다
			String memberId = request.getParameter("memberId");
			
			String memberPw = request.getParameter("memberPw");	
			
			//2.로그인 체그 메소드를 만들어서 호출
			//객체 안의 메소드 호출
			SchoolVo mv = sd.memberLogin(memberId, memberPw);			
			System.out.println("회원이름?"+mv.getMemberName());
			System.out.println("회원아이디?"+mv.getMemberId());
			
			//3.로그인이 되있다면 메인페이지도 이동
			String spath = "";
			if (mv == null) {				
				spath=request.getContextPath()+"/memberr/schoolLogin.do";								
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("mv", mv);
				
				spath=request.getContextPath()+"/memberr/schoolLogin.do";							
			}	
			response.sendRedirect(spath);	
			
		}else if(action.equals("schoolIntroduce.do")) {
			
			//meberjoin.do로 넘어오면 내부페이지 이동
			String path = "/memberr/introduce.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		}else if(action.equals("schoolNotice.do")) {
		System.out.println("schoolNotice입니다.");
		
		//meberjoin.do로 넘어오면 내부페이지 이동
		String path = "/memberr/notice.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		}
			
			
			
			
			
			
	}
}