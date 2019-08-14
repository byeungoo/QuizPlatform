package com.quiz.web.dto;

import java.util.Date;

public class WritingVoteDto {

	private int    writing_no;         //게시글 번호
	private String user_id;            //사용자아이디
	private String regpe_id;           //작성자
	private String modpe_id;           //수정자
	private Date   reg_dts;            //등록일시
	private Date   mod_dts;            //수정일시
	private Integer vote;              //1일경우 위의 컨텐츠, 2일경우 아래 컨텐츠 투표
	private double fir_vote_perc;      //첫번째 질문 퍼센트
	private double sec_vote_perc;      //두번째 질문 퍼센트
	
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
	public double getFir_vote_perc() {
		return fir_vote_perc;
	}
	public void setFir_vote_perc(double fir_vote_perc) {
		this.fir_vote_perc = fir_vote_perc;
	}
	public double getSec_vote_perc() {
		return sec_vote_perc;
	}
	public void setSec_vote_perc(double sec_vote_perc) {
		this.sec_vote_perc = sec_vote_perc;
	}
	public Integer getVote() {
		return vote;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
	}
}
