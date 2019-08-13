package common.paging.dto;

public class PagingDto {

	private int page_num;         //페이지 번호
	private int page_size;        //한번에 처리할 페이지 개수
	private String user_id;        //사용자 아이디
	private Integer mainCategory; //메인페이지 인기,신규,활동 카테고리 값
	private int start;            //페이징 시작 번호
	private int end;              //페이징 종료 번호
	
	public PagingDto(){
		this.page_size = 15;
	}

	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Integer getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(Integer mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
