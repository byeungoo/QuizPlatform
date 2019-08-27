package com.quiz.web.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;

import common.paging.dto.PagingDto;
import common.paging.dto.WritingDtlPagingDto;

@Repository
public class WritingDtlDao {
	
	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.writingDtlMapper";
    
    public List<WritingDtlDto> getTextWritingList(PagingDto pagingDto) throws Exception{
        return sqlSession.selectList(Namespace+".getTextWritingList", pagingDto);
    }
    
    public WritingDtlDto getTextWriting(ParamDto paramDto) throws Exception{
        return sqlSession.selectOne(Namespace+".getTextWriting", paramDto);
    }
    
    public void insertWritingDtl(WritingDtlDto writingDtlDto) throws Exception{
    	sqlSession.insert(Namespace+".insertWritingDtl", writingDtlDto);
    }
    
    public void updateHits(int writing_no) throws Exception{
    	sqlSession.update(Namespace+".updateHits", writing_no);
    }
    
    public List<WritingDtlDto> getHotTextWritingList(PagingDto pagingDto) throws Exception{
        return sqlSession.selectList(Namespace+".getHotTextWritingList", pagingDto);
    }
    
    public List<WritingDtlDto> getMyVote(PagingDto pagingDto) throws Exception{
    	return sqlSession.selectList(Namespace+".getMyVote", pagingDto);
    }
    
    public List<WritingDtlDto> getPopulWritingDtoList(WritingDtlPagingDto writingDtlPagingDto) throws Exception{
    	return sqlSession.selectList(Namespace+".getPopulWritingDtoList", writingDtlPagingDto);
    }
    
    //첫번째 투표수 업데이트
    public void updateFirVote(ParamDto paramDto) throws Exception{
    	sqlSession.update(Namespace+".updateFirVote", paramDto);
    }
    
    //두번째 투표수 업데이트
    public void updateSecVote(ParamDto paramDto) throws Exception{
    	sqlSession.update(Namespace+".updateSecVote", paramDto);
    }
    
    //게시글 신고
    public void reportWriting(ParamDto paramDto) throws Exception{
    	sqlSession.insert(Namespace+".reportWriting", paramDto);
    }
}
