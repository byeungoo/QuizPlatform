package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.CommentDao;
import com.quiz.web.dto.CommentDto;
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
		return commentDao.getCommentDtoList(paramDto);
	}
	
	public List<LowCommentDto> getLowCommentDtoList(ParamDto paramDto) throws Exception{
		return commentDao.getLowCommentDtoList(paramDto);
	}
	
	public CommentDto getCommentDto(ParamDto paramDto) throws Exception{
		return commentDao.getCommentDto(paramDto);
	}

}
