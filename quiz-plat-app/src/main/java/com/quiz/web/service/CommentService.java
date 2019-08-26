package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.CommentDao;
import com.quiz.web.dto.CommentDto;
import com.quiz.web.dto.CommentPrefer;
import com.quiz.web.dto.LowCommentDto;
import com.quiz.web.dto.ParamDto;

@Service
public class CommentService {
	
	@Autowired
	CommentDao commentDao;
	
	public void insertComment(CommentDto commentDto) throws Exception{
		commentDao.insertComment(commentDto);
	}
	
	public List<CommentDto> getCommentDtoList (ParamDto paramDto) throws Exception{
		
		List<CommentDto> commendDtoList = commentDao.getCommentDtoList(paramDto);
		
	    paramDto.setDepth(1); //대댓글 조회를 위해 1로세팅
	    //대댓글 값 세팅
	    for(CommentDto tempCommentDto : commendDtoList) {
	    	int sum_prefer = tempCommentDto.getRecom_num() - tempCommentDto.getHate_num();
	    	tempCommentDto.setSum_prefer(sum_prefer);
	    	paramDto.setParent(tempCommentDto.getComment_no());  //대댓글 상위 댓글 번호 세팅
	    	tempCommentDto.setLowCommentDtoList(getLowCommentDtoList(paramDto));
	    	tempCommentDto.setLow_comment_num(tempCommentDto.getLowCommentDtoList().size());  //대댓글 개수 세팅
	    }
	    
		return commendDtoList;
	}
	
	public List<LowCommentDto> getLowCommentDtoList(ParamDto paramDto) throws Exception{
		
		List<LowCommentDto> lowCommentDtoList = commentDao.getLowCommentDtoList(paramDto);
		
		//좋아요-싫어요 수 세팅
		for(LowCommentDto tempLowCommentDto : lowCommentDtoList) {
			int sum_prefer = tempLowCommentDto.getRecom_num() - tempLowCommentDto.getHate_num();
			tempLowCommentDto.setSum_prefer(sum_prefer);
		}
		
		return lowCommentDtoList;
	}
	
	public CommentDto getCommentDto(ParamDto paramDto) throws Exception{
		return commentDao.getCommentDto(paramDto);
	}
	
	/*
	 ** 댓글 좋아요 싫어요 업데이트
	 */
	public CommentPrefer commentPreferUpdate(CommentPrefer commentPrefer) throws Exception{
	
		if(commentPrefer.getPrefer().equals("0")) {  //좋아요 업데이트 설정
			commentPrefer.setLike(1);
			commentPrefer.setHate(0);
		} else if(commentPrefer.getPrefer().equals("1")) { //싫어요 업데이트 설정
			commentPrefer.setLike(0);
			commentPrefer.setHate(1);
		}
		commentDao.updateCommentPrefer(commentPrefer); //댓글 총 좋아요, 싫어요 수 업데이트
		
		commentDao.insertCommentPrefer(commentPrefer); //댓글 선호 insert
		commentPrefer = commentDao.getCommentPrefer(commentPrefer);
		
		int sumPrefer = commentPrefer.getRecom_num()-commentPrefer.getHate_num();  //좋아요, 싫어요의 합 설정
		commentPrefer.setSum_prefer(sumPrefer);
		
		return commentPrefer;
	}
}
