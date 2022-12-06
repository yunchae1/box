package Domain;

public class Criteria {	
	
	private int page = 1;	//페이지변수 초기값1세팅
	private int perPageNum = 15; 	//화면에 출력되는 리스트 수
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	
	

	
}
