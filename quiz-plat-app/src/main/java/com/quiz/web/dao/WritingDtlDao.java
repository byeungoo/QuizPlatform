package com.quiz.web.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import com.quiz.web.dto.WritingDtlDto;

import common.PagingDto;

@Repository
public class WritingDtlDao {
	
	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.writingDtlMapper";
    
    public List<WritingDtlDto> getTextWritingList(PagingDto pagingDto) throws Exception{
        return sqlSession.selectList(Namespace+".getTextWritingList", pagingDto);
    }
    
    public WritingDtlDto getTextWriting(int writing_no) throws Exception{
        return sqlSession.selectOne(Namespace+".getTextWriting", writing_no);
    }
    
    public void updateFirVoteNo(int writing_no) throws Exception{
    	sqlSession.selectOne(Namespace+".updateFirVoteNo", writing_no);
    }

    public void updateSecVoteNo(int writing_no) throws Exception{
    	sqlSession.selectOne(Namespace+".updateSecVoteNo", writing_no);
    }
    
    public void insertWritingDtl(WritingDtlDto writingDtlDto) throws Exception{
    	sqlSession.insert(Namespace+".insertWritingDtl", writingDtlDto);
    }
    
    public void updateHits(int writing_no) throws Exception{
    	sqlSession.update(Namespace+".updateHits", writing_no);
    }
    
    public List<WritingDtlDto> getHotTextWritingList() throws Exception{
        return sqlSession.selectList(Namespace+".getHotTextWritingList");
    }
    
    public List<WritingDtlDto> getMyVote(String user_id) throws Exception{
    	return sqlSession.selectList(Namespace+".getMyVote", user_id);
    }
}
