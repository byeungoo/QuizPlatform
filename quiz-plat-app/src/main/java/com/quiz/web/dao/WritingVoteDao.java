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
    
    public List<WritingVoteDto> getWritingVoteList() throws Exception {
        return sqlSession.selectList(Namespace+".getWritingVoteList");
    }
	
}
