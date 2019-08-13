package common.paging.dto;

public class PagingDto {

	private int page_num;         //������ ��ȣ
	private int page_size;        //�ѹ��� ó���� ������ ����
	private String user_id;        //�����? ���̵�
	private Integer mainCategory; //���������� �α�,�ű�,Ȱ�� ī�װ� ��
	private int start;            //����¡ ���� ��ȣ
	private int end;              //하이하이
	
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
