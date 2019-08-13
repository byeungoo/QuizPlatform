package com.quiz.web.dto;

import java.util.Date;
import java.util.List;

public class WritingDtlDto {
	
	private int    writing_no;        //�Խñ۹�ȣ
	private String ques_type_div_cd;  //�������������ڵ�, 10: �ؽ�Ʈ, 20: �̹���
	private String content;           //����
	private String fir_writ_content;  //�� ���� ù��° �ۼ����� 
	private String sec_writ_content;  //�� ���� �ι�° �ۼ�����
	private String fir_writ_img_path; //�� ���� ù��° �ۼ� �̹��� ���
	private String sec_writ_img_path; //�� ���� �ι�° �ۼ� �̹��� ���
	private int    fir_vote_no;       //ù��° ��ǥ ��
	private int    sec_vote_no;       //�ι�° ��ǥ ��
	private String regpe_id;          //�ۼ���
	private String modpe_id;          //������
	private Date   reg_dts;           //����Ͻ�
	private Date   mod_dts;           //�����Ͻ�
	private int    hits;              //��ȸ��
	private int    sum_vote;          //�� ��ǥ ��
	private int    sum_comment;       //�� ��� ��
	private double popularity;        //�α⵵
	private double fir_vote_perc;     //ù��° ��ǥ ����
	private double sec_vote_perc;     //�ι��� ��ǥ ����
	private List<CommentDto> detailCommentList; //댓글리스트

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
	public int getSum_comment() {
		return sum_comment;
	}
	public void setSum_comment(int sum_comment) {
		this.sum_comment = sum_comment;
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
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	public List<CommentDto> getDetailCommentList() {
		return detailCommentList;
	}

	public void setDetailCommentList(List<CommentDto> detailCommentList) {
		this.detailCommentList = detailCommentList;
	}
}
