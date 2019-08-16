package com.quiz.web.dto;

public class ParamDto {
	
	private int writing_no;  //게시글 번호
	private String user_id;  //사용자아이디
	private Integer vote;    //1일경우 위의 컨텐츠, 2일경우 아래 컨텐츠 투표
	
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
