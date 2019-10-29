package com.quiz.web.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class CommentDto extends BaseDto{
	
	private int    writing_no;         //게시글번호
	private int    comment_no;         //댓글번호
	private String comment_content;    //댓글내용
	private int    recom_num;          //좋아요수
	private int    hate_num;           //싫어요수
	private String nickname;           //닉네임
	private int    depth;              //0: 댓글, 1:대댓글
	private Integer parent;            //대댓글 상위 댓글 번호
	private Integer vote;              //1:첫번째 투표, 2: 두번째 투표
	private List<LowCommentDto> lowCommentDtoList; //대댓글 리스트
	private int    low_comment_num;    //대댓글 개수
	private int    sum_prefer;         //좋아요 수 - 싫어요 수
	private boolean isMine;            //내가 쓴 댓글일 경우 true, 아니면 false
	private String prefer;             //0:댓글좋아요, 1:댓글싫어요, null: 선호 선택x
	private String use_yn;             //댓글 사용여부
	private String user_id;            //유저아이디
	private boolean isSuccess;         //댓글 삭제 성공 여부
	
	public Integer getVote() {
		return vote;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
	}
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public List<LowCommentDto> getLowCommentDtoList() {
		return lowCommentDtoList;
	}
	public void setLowCommentDtoList(List<LowCommentDto> lowCommentDtoList) {
		this.lowCommentDtoList = lowCommentDtoList;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public int getLow_comment_num() {
		return low_comment_num;
	}
	public void setLow_comment_num(int low_comment_num) {
		this.low_comment_num = low_comment_num;
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
	public int getSum_prefer() {
		return sum_prefer;
	}
	public void setSum_prefer(int sum_prefer) {
		this.sum_prefer = sum_prefer;
	}
	public boolean isMine() {
		return isMine;
	}
	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
	public String getPrefer() {
		return prefer;
	}
	public void setPrefer(String prefer) {
		this.prefer = prefer;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
