package com.quiz.web.dto;

import java.util.Date;

public class CommentDto {
	
	public CommentDto(int writing_no, String comment_content, int recom_no, String regpe_id) {
		this.writing_no = writing_no;
		this.comment_content = comment_content;
		this.recom_no = recom_no;
		this.regpe_id = regpe_id;
	}
	
	private int    writing_no;         //게시글번호
	private int    comment_no;         //댓글번호
	private String comment_content;    //댓글내용
	private int    recom_no;           //추천수
	private String regpe_id;           //작성자
	private String modpe_id;           //수정자
	private Date   reg_dts;            //등록일시
	private Date   mod_dts;            //수정일시
	
	public int getWriting_no() {
		return writing_no;
	}
	public void setWriting_no(int writing_no) {
		this.writing_no = writing_no;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getRecom_no() {
		return recom_no;
	}
	public void setRecom_no(int recom_no) {
		this.recom_no = recom_no;
	}
	public String getRegpe_id() {
		return regpe_id;
	}
	public void setRegpe_id(String regpe_id) {
		this.regpe_id = regpe_id;
	}
	public String getModpe_id() {
		return modpe_id;
	}
	public void setModpe_id(String modpe_id) {
		this.modpe_id = modpe_id;
	}
	public Date getReg_dts() {
		return reg_dts;
	}
	public void setReg_dts(Date reg_dts) {
		this.reg_dts = reg_dts;
	}
	public Date getMod_dts() {
		return mod_dts;
	}
	public void setMod_dts(Date mod_dts) {
		this.mod_dts = mod_dts;
	}
}
