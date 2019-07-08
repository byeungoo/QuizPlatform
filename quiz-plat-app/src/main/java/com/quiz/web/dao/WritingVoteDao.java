package com.quiz.web.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.WritingVoteDto;

import org.apache.ibatis.session.SqlSession;

@Repository
public class WritingVoteDao {

	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.writingVoteDtlMapper";
    
    public WritingVoteDto getWritingVoteDto(WritingVoteDto writingVoteDto) throws Exception {
        return sqlSession.selectOne(Namespace+".getWritingVoteDto", writingVoteDto);
    }
    
    public void insertWritingVoteDto(WritingVoteDto writingVoteDto) throws Exception {
    	sqlSession.insert(Namespace+".insertWritingVoteDto", writingVoteDto);
    }
    
    public String chekVote(WritingVoteDto writingVoteDto) throws Exception {
    	return sqlSession.selectOne(Namespace+".chekVote", writingVoteDto);
    }
}
