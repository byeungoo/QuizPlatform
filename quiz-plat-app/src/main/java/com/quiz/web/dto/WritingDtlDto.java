package com.quiz.web.dto;

import java.util.Date;

public class WritingDtlDto {
	
	private int    writing_no;        //게시글번호
	private String ques_type_div_cd;  //질문유형구분코드, 10: 텍스트, 20: 이미지
	private String content;           //본문
	private String fir_writ_content;  //비교 질문 첫번째 작성내용 
	private String sec_writ_content;  //비교 질문 두번째 작성내용
	private String fir_writ_img_path; //비교 질문 첫번째 작성 이미지 경로
	private String sec_writ_img_path; //비교 질문 두번째 작성 이미지 경로
	private int    fir_vote_no;       //첫번째 투표 수
	private int    sec_vote_no;       //두번째 투표 수
	private String regpe_id;          //작성자
	private String modpe_id;          //수정자
	private Date   reg_dts;           //등록일시
	private Date   mod_dts;           //수정일시
	private int    hits;              //조회수
	private int    sum_vote;
	
	public int getWriting_no() {
		return writing_no;
	}
	public void setWriting_no(int writing_no) {
		this.writing_no = writing_no;
	}
	public String getQues_type_div_cd() {
		return ques_type_div_cd;
	}
	public void setQues_type_div_cd(String ques_type_div_cd) {
		this.ques_type_div_cd = ques_type_div_cd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFir_writ_content() {
		return fir_writ_content;
	}
	public void setFir_writ_content(String fir_writ_content) {
		this.fir_writ_content = fir_writ_content;
	}
	public String getSec_writ_content() {
		return sec_writ_content;
	}
	public void setSec_writ_content(String sec_writ_content) {
		this.sec_writ_content = sec_writ_content;
	}
	public String getFir_writ_img_path() {
		return fir_writ_img_path;
	}
	public void setFir_writ_img_path(String fir_writ_img_path) {
		this.fir_writ_img_path = fir_writ_img_path;
	}
	public String getSec_writ_img_path() {
		return sec_writ_img_path;
	}
	public void setSec_writ_img_path(String sec_writ_img_path) {
		this.sec_writ_img_path = sec_writ_img_path;
	}
	public int getFir_vote_no() {
		return fir_vote_no;
	}
	public void setFir_vote_no(int fir_vote_no) {
		this.fir_vote_no = fir_vote_no;
	}
	public int getSec_vote_no() {
		return sec_vote_no;
	}
	public void setSec_vote_no(int sec_vote_no) {
		this.sec_vote_no = sec_vote_no;
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getSum_vote() {
		return sum_vote;
	}
	public void setSum_vote(int sum_vote) {
		this.sum_vote = sum_vote;
	}

}
