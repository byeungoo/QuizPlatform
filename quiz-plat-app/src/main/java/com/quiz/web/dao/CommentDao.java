package com.quiz.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.CommentDto;
import com.quiz.web.dto.LowCommentDto;
import com.quiz.web.dto.ParamDto;

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
	
	/*
	 ** 댓글 리스트 조회
	 */
	public List<CommentDto> getCommentDtoList(ParamDto paramDto) throws Exception{
		return sqlSession.selectList(Namespace+".getCommentDtoList", paramDto);
	}
	
	/*
	 ** 대댓글 리스트 조회 
	 */
	public List<LowCommentDto> getLowCommentDtoList(ParamDto paramDto) throws Exception{
		return sqlSession.selectList(Namespace+".getLowCommentDtoList", paramDto);
	}
	
	/*
	 ** 댓글 조회 
	 */
	public CommentDto getCommentDto(ParamDto paramDto) throws Exception{
		return sqlSession.selectOne(Namespace+".getCommentDto", paramDto);
	}
}
