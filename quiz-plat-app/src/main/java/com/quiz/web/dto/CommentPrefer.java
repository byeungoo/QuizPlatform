package com.quiz.web.dto;

public class CommentPrefer {
	private int    writing_no;
	private int    comment_no;
	private int    recom_num;
	private int    hate_num;
	private String user_id;
	private String prefer;
	private int    sum_prefer;
	private int    like;
	private int    hate;
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getHate() {
		return hate;
	}
	public void setHate(int hate) {
		this.hate = hate;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getRecom_num() {
		return recom_num;
	}
	public void setRecom_num(int recom_num) {
		this.recom_num = recom_num;
	}
	public int getHate_num() {
		return hate_num;
	}
	public void setHate_num(int hate_num) {
		this.hate_num = hate_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPrefer() {
		return prefer;
	}
	public void setPrefer(String prefer) {
		this.prefer = prefer;
	}
	public int getSum_prefer() {
		return sum_prefer;
	}
	public void setSum_prefer(int sum_prefer) {
		this.sum_prefer = sum_prefer;
	} 
	public int getWriting_no() {
		return writing_no;
	}
	public void setWriting_no(int writing_no) {
		this.writing_no = writing_no;
	}
}
