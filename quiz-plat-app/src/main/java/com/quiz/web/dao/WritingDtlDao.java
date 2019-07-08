package com.quiz.web.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import com.quiz.web.dto.WritingDtlDto;

@Repository
public class WritingDtlDao {
	
	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.writingDtlMapper";
    
    public List<WritingDtlDto> getTextWritingList() throws Exception {
        return sqlSession.selectList(Namespace+".getTextWritingList");
    }
    
    public WritingDtlDto getTextWriting(int writing_no) throws Exception {
        return sqlSession.selectOne(Namespace+".getTextWriting", writing_no);
    }
}
