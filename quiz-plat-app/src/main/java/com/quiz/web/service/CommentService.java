package com.quiz.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.CommentDao;
import com.quiz.web.dto.CommentDto;
import com.quiz.web.dto.CommentPrefer;
import com.quiz.web.dto.LowCommentDto;
import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;

@Service
public class CommentService {
	
	@Autowired
	CommentDao commentDao;
	
    /*
     ** 댓글 작성
     */
	public void insertComment(CommentDto commentDto) throws Exception{
		try {
			commentDao.insertComment(commentDto);
		}  catch(Exception e) {
    		System.err.println(e.getMessage());
    		throw new RuntimeException(e);
    	} 
	}
	
    /*
     ** 댓글 리스트 조회
     */
	public List<CommentDto> getCommentDtoList (ParamDto paramDto, UserDto userDto) throws Exception{
		
		List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
		
		try {
			
			commentDtoList = commentDao.getCommentDtoList(paramDto);
			
		    paramDto.setDepth(1); //대댓글 조회를 위해 1로세팅
		    //대댓글 값 세팅
		    for(CommentDto tempCommentDto : commentDtoList) {
		    	int sum_prefer = tempCommentDto.getRecom_num() - tempCommentDto.getHate_num();
		    	tempCommentDto.setSum_prefer(sum_prefer);
		    	paramDto.setParent(tempCommentDto.getComment_no());  //대댓글 상위 댓글 번호 세팅
		    	tempCommentDto.setLowCommentDtoList(getLowCommentDtoList(paramDto, userDto));
		    	tempCommentDto.setLow_comment_num(tempCommentDto.getLowCommentDtoList().size());  //대댓글 개수 세팅
		    	
		    	//내가 쓴 댓글인지 판단
			    if(tempCommentDto.getRegpe_id().equals(userDto.getUser_id())) {
			    	tempCommentDto.setMine(true);
			    }
		    	
		    }
		} catch(Exception e) {
    		System.err.println(e.getMessage());
    	} 
	    
		return commentDtoList;
	}
	
    /*
     ** 대댓글 리스트 조회
     */
	public List<LowCommentDto> getLowCommentDtoList(ParamDto paramDto, UserDto userDto) throws Exception{
		
		List<LowCommentDto> lowCommentDtoList = new ArrayList<LowCommentDto>();
		
		try {
			
			lowCommentDtoList = commentDao.getLowCommentDtoList(paramDto);
			
			//좋아요-싫어요 수 세팅
			for(LowCommentDto tempLowCommentDto : lowCommentDtoList) {
				int sum_prefer = tempLowCommentDto.getRecom_num() - tempLowCommentDto.getHate_num();
				tempLowCommentDto.setSum_prefer(sum_prefer);
				
		    	//내가 쓴 댓글인지 판단
			    if(tempLowCommentDto.getRegpe_id().equals(userDto.getUser_id())) {
			    	tempLowCommentDto.setMine(true);
			    }
				
			}
		} catch(Exception e) {
    		System.err.println(e.getMessage());
    	} 
		
		return lowCommentDtoList;
	}
	
    /*
     ** 댓글 1개 조회
     */
	public CommentDto getCommentDto(ParamDto paramDto) throws Exception{
		
		CommentDto commentDto = new CommentDto();
		
		try {
			commentDto = commentDao.getCommentDto(paramDto);
			commentDto.setMine(true);
		} catch(Exception e) {
    		System.err.println(e.getMessage());
    	} 
		
		return commentDto;
	}
	
	/*
	 ** 댓글 좋아요 싫어요 업데이트
	 */
	public CommentPrefer commentPreferUpdate(CommentPrefer commentPrefer) throws Exception{
	
		try {
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
		} catch(Exception e) {
    		System.err.println(e.getMessage());
    		throw new RuntimeException(e);
    	} 
		
		return commentPrefer;
	}
	
	
    /*
    ** 댓글 삭제하기, 사용여부 'N' 변경
    */
    public CommentDto deleteComment(HttpSession session, CommentDto commentDto) throws Exception{
    	

    	try{
    		commentDao.updateCommentUseYn(commentDto); //댓글 사용여부 'N' 변경
    		commentDto.setSuccess(true);
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    		throw new RuntimeException(e);
    	}
    	
    	return commentDto;
    }
    
    public List<CommentDto> getChildCommentList(CommentDto commentDto) throws Exception{
    	
    	try {
    		return commentDao.getChildCommentList(commentDto);
    	} catch(Exception e) {
    		throw new RuntimeException(e);
    	}
    	
    }
}
