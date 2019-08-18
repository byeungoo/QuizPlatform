package com.quiz.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.CommentDto;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

@Repository
public class CommentDao {
	
	@Autowired
    private SqlSession sqlSession;
	
    private static final String Namespace = "com.quiz.mapper.commentMapper";
	
	public void insertComment(CommentDto commentDto) throws Exception{
        sqlSession.insert(Namespace+".insertComment", commentDto);

	}
	
	public List<CommentDto> getCommentDtoList(int writing_no) throws Exception{
		return sqlSession.selectList(Namespace+".getCommentDtoList", writing_no);
	}	
}
