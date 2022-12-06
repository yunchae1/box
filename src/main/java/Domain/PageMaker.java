package Domain;

public class PageMaker {

	private int startPage;	//페이지 네비게이션 시작
	private int endPage;	//페이지 네비게이션 끝
	private int totalCount;	//전체게시물 수
	private int displayPageNum = 10;	// 1 2 3 4 5 6 7 8 9 10
	private boolean prev;	//이전버튼
	private boolean next;	//다음버튼
	
	private Criteria cri;	//페이지객체

	public int getStartPage() { //변수의 값을 담아서 사용
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();	//메소드
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void calcData() {
		//시작번호 끝번호 이전 다음 버튼을 보여주기 위한 계산식
		
		//끝번호
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);	//Math.ceil 소수점 올림처리
		System.out.println("endPage"+endPage);
		//시작점 번호
		startPage = (endPage - displayPageNum)+1;
		System.out.println("startPage"+startPage);
		
		//전체 페이지 수
		int tempEndPage = (int)Math.ceil(totalCount/(double)cri.getPerPageNum());
		System.out.println("tempEndPage"+tempEndPage);
		//실제 페이지값이 들어간다
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		//이전 버튼 생략유무
		prev = (startPage == 1 ? false : true);
		//다음 버튼 생략유무
		next = (endPage*cri.getPerPageNum() >= totalCount ? false : true);
		
		
	}
	
	
	
}
