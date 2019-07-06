package com.quiz.web.dto;

import java.util.Date;

public class WritingVoteDto {

	private int    writing_no;         //게시글 번호
	private String user_id;            //사용자아이디
	private String fir_content_vote;   //첫번째내용투표
	private String sec_content_vote;   //두번째내용투표
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFir_content_vote() {
		return fir_content_vote;
	}
	public void setFir_content_vote(String fir_content_vote) {
		this.fir_content_vote = fir_content_vote;
	}
	public String getSec_content_vote() {
		return sec_content_vote;
	}
	public void setSec_content_vote(String sec_content_vote) {
		this.sec_content_vote = sec_content_vote;
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
