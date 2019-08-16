package com.quiz.web.dto;

import java.util.Date;
import java.util.List;

public class WritingVoteDto {

	private int     writing_no;         //�Խñ� ��ȣ
	private String  user_id;            //����ھ��̵�
	private String  regpe_id;           //�ۼ���
	private String  modpe_id;           //������
	private Date    reg_dts;            //����Ͻ�
	private Date    mod_dts;            //�����Ͻ�
	private Integer vote;               //1�ϰ�� ���� ������, 2�ϰ�� �Ʒ� ������ ��ǥ
	private int     fir_vote_no;
	private int     sec_vote_no;
	private Integer fir_vote_perc;      //ù��° ���� �ۼ�Ʈ
	private Integer sec_vote_perc;      //�ι�° ���� �ۼ�Ʈ
	private List<Integer> voteNoArr;    //컨텐츠 투표 수 리스트
	private List<Integer> votePerc;     //컨텐츠 투표 비율 리스트
	
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
	public Integer getVote() {
		return vote;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
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
	public Integer getFir_vote_perc() {
		return fir_vote_perc;
	}
	public void setFir_vote_perc(Integer fir_vote_perc) {
		this.fir_vote_perc = fir_vote_perc;
	}
	public Integer getSec_vote_perc() {
		return sec_vote_perc;
	}
	public void setSec_vote_perc(Integer sec_vote_perc) {
		this.sec_vote_perc = sec_vote_perc;
	}
	public List<Integer> getVoteNoArr() {
		return voteNoArr;
	}
	public void setVoteNoArr(List<Integer> voteNoArr) {
		this.voteNoArr = voteNoArr;
	}
	public List<Integer> getVotePerc() {
		return votePerc;
	}
	public void setVotePerc(List<Integer> votePerc) {
		this.votePerc = votePerc;
	}
}
