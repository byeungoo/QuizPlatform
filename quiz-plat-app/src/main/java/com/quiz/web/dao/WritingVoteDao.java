package com.quiz.web.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.WritingVoteDto;

import org.apache.ibatis.session.SqlSession;

@Repository
public class WritingVoteDao {

	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.writingVoteDtlMapper";
    
    public WritingVoteDto getWritingVoteDto(ParamDto paramDto){
        return sqlSession.selectOne(Namespace+".getWritingVoteDto", paramDto);
    }
    
    public void insertWritingVoteDto(ParamDto paramDto){
    	sqlSession.insert(Namespace+".insertWritingVoteDto", paramDto);
    }
    
    public String chekVote(WritingVoteDto writingVoteDto){
    	return sqlSession.selectOne(Namespace+".chekVote", writingVoteDto);
    }
}
