package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.CommentDao;
import com.quiz.web.dto.CommentDto;

@Service
public class CommentService {
	
	@Autowired
	CommentDao commentDao;
	
	public void insertComment(CommentDto commentDto) throws Exception{
		commentDao.insertComment(commentDto);
	}
	
	public List<CommentDto> getCommentDtoList (int writing_no) throws Exception{
		return commentDao.getCommentDtoList(writing_no);
	}

}
