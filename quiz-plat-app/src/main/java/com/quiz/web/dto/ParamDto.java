package com.quiz.web.dto;

public class ParamDto {
	
	private int     writing_no;  //게시글번호
	private String  user_id;     //유저아이디
	private Integer vote;        //1: 첫번쨰 컨텐츠 투표, 2: 두번째 컨텐츠 투표
	private int     depth;       //0: 댓글, 1: 대댓글
	private Integer parent;      //대댓글 상위 댓글번호
	
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
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
}
