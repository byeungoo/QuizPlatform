package com.quiz.web.dto;

public class ParamDto {
	
	private int writing_no;  //�Խñ� ��ȣ
	private String user_id;  //����ھ��̵�
	private Integer vote;    //1�ϰ�� ���� ������, 2�ϰ�� �Ʒ� ������ ��ǥ
	
	public int getWriting_no() {
		return writing_no;
	}
	public void setWriting_no(int writing_no) {
		this.writing_no = writing_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Integer getVote() {
		return vote;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
	}
	
}
