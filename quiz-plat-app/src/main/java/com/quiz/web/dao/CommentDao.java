package com.quiz.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.CommentDto;
import com.quiz.web.dto.CommentPrefer;
import com.quiz.web.dto.LowCommentDto;
import com.quiz.web.dto.ParamDto;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

@Repository
public class CommentDao {
	
	@Autowired
    private SqlSession sqlSession;
	
    private static final String Namespace = "com.quiz.mapper.commentMapper";
	
	public void insertComment(CommentDto commentDto){
        sqlSession.insert(Namespace+".insertComment", commentDto);

	}
	
	/*
	 ** 댓글 리스트 조회
	 */
	public List<CommentDto> getCommentDtoList(ParamDto paramDto){
		return sqlSession.selectList(Namespace+".getCommentDtoList", paramDto);
	}
	
	/*
	 ** 대댓글 리스트 조회 
	 */
	public List<LowCommentDto> getLowCommentDtoList(ParamDto paramDto){
		return sqlSession.selectList(Namespace+".getLowCommentDtoList", paramDto);
	}
	
	/*
	 ** 댓글 조회 
	 */
	public CommentDto getCommentDto(ParamDto paramDto){
		return sqlSession.selectOne(Namespace+".getCommentDto", paramDto);
	}
	
	/*
	 ** 좋아요, 싫어요 업데이트
	 */
	public void updateCommentPrefer(CommentPrefer commentPrefer){
		sqlSession.update(Namespace+".updateCommentPrefer", commentPrefer);
	}
	
	/*
	 ** 좋아요, 싫어요 업데이트
	 */
	public CommentPrefer getCommentPrefer(CommentPrefer commentPrefer){
		return sqlSession.selectOne(Namespace+".getCommentPrefer", commentPrefer);
	}
	
	/*
	 ** 댓글 선호 좋아요 싫어요 추가
	 */
	public void insertCommentPrefer(CommentPrefer commentPrefer){
		sqlSession.insert(Namespace+".insertCommentPrefer", commentPrefer);
	}
	
	
	/*
	 ** 댓글 사용여부 'N' 변경
	 */
	public void updateCommentUseYn(CommentDto commentDto){
		sqlSession.update(Namespace+".updateCommentUseYn", commentDto);
	}
	
	public List<CommentDto> getChildCommentList(CommentDto commentDto){
		return sqlSession.selectList(Namespace+".getChildCommentList", commentDto);
	}
}
