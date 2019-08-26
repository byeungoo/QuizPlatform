package common.paging.dto;

public class PagingDto {

	private int page_num;         //가지고올 페이지 번호
	private int page_size;        //페이지 사이즈
	private String user_id;        //유저아이디
	private Integer mainCategory; //메인카테고리, 1:인기, 2:최신, 3:나의활동내역
	private int start;            //가지고올 번호 시작 다음
	private int end;              //가지고올 번호 마지막
	
	public PagingDto(){
		this.page_size = 10;
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
