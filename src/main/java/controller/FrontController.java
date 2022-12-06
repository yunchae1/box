package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Object response;
	private Object request;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRun(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRun(request,response);
		
	}
	
	private void doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test");
		
		//한글 깨짐 방지
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				
		//가상경로에서 분기하는 코드를 추출한다
		String uri = request.getRequestURI();	//전체 가상주소 경로를 추출
		int leng = request.getContextPath().length();	//프로젝트 루트 이름 자리수
		String str = uri.substring(leng); //.substring 문자열 자르기
		String[] seq = str.split("/");	// /로 구분해서 배열에 담고
		
		//예)/member/memberList.do 추출
		// 	/board/memberWriteAction.do
		
		
		if(seq[1].equals("memberr")) {	//두번째 배열에 담은 값이 member이면
			MemberController mc = new MemberController(seq[2]);
			mc.doGet(request, response);
			
			
		}else if(seq[1].equals("board")) {	//두번째 배열에 담은 값이 board이면
			BoardController bc = new BoardController(seq[2]);	//컨트롤러 생성
			bc.doGet(request, response);
			
		}
		
		}
	
	

}
