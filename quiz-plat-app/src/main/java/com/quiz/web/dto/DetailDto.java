package com.quiz.web.dto;

import java.util.HashMap;
import java.util.List;

public class DetailDto {

	private List<WritingDtlDto> detailWritingList;
	private List<WritingVoteDto> detailWritingVoteList;
	private HashMap<Integer, List<CommentDto>> detailCommentList;
	
	public List<WritingDtlDto> getDetailWritingList() {
		return detailWritingList;
	}
	public void setDetailWritingList(List<WritingDtlDto> detailWritingList) {
		this.detailWritingList = detailWritingList;
	}
	public List<WritingVoteDto> getDetailWritingVoteList() {
		return detailWritingVoteList;
	}
	public void setDetailWritingVoteList(List<WritingVoteDto> detailWritingVoteList) {
		this.detailWritingVoteList = detailWritingVoteList;
	}
	public HashMap<Integer, List<CommentDto>> getDetailCommentList() {
		return detailCommentList;
	}
	public void setDetailCommentList(HashMap<Integer, List<CommentDto>> detailCommentList) {
		this.detailCommentList = detailCommentList;
	}
}
