package com.quiz.web.dto;

import java.util.Date;
import java.util.List;

public class WritingVoteDto extends BaseDto{

	private int     writing_no;         //게시글 번호
	private Integer vote;               //1: 찬성 투표, 2: 반대 투표
	private int     fir_vote_no;
	private int     sec_vote_no;
	private Integer fir_vote_perc;      //첫번째 투표 비율
	private Integer sec_vote_perc;      //두번쨰 투표 비율
	private List<Integer> voteNoArr;    //컨텐츠 투표 수 리스트
	private List<Integer> votePerc;     //컨텐츠 투표 비율 리스트
	private	int     totalVoteNum;       //총투표수 
	private String  choice1;            //선택1
	private String  choice2;            //선택2
	
	public int getWriting_no() {
		return writing_no;
	}
	public void setWriting_no(int writing_no) {
		this.writing_no = writing_no;
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
	public int getTotalVoteNum() {
		return totalVoteNum;
	}
	public void setTotalVoteNum(int totalVoteNum) {
		this.totalVoteNum = totalVoteNum;
	}
	public String getChoice1() {
		return choice1;
	}
	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}
	public String getChoice2() {
		return choice2;
	}
	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}
}
