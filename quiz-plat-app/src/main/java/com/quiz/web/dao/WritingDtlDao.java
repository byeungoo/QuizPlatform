package com.quiz.web.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;

import common.paging.dto.PagingDto;
import common.paging.dto.WritingDtlPagingDto;

@Repository
public class WritingDtlDao {
	
	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.writingDtlMapper";
    
    //최신순 페이징 리스트 조회
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
    
    //인기순 페이징 리스트 조회
    public List<WritingDtlDto> getHotTextWritingList(PagingDto pagingDto) throws Exception{
        return sqlSession.selectList(Namespace+".getHotTextWritingList", pagingDto);
    }
    
    //나의 활동내역 페이징 리스트 조회
    public List<WritingDtlDto> getMyVote(PagingDto pagingDto) throws Exception{
    	return sqlSession.selectList(Namespace+".getMyVote", pagingDto);
    }
    
    //
    public List<WritingDtlDto> getPopulWritingDtoList(WritingDtlPagingDto writingDtlPagingDto) throws Exception{
    	return sqlSession.selectList(Namespace+".getPopulWritingDtoList", writingDtlPagingDto);
    }
    
}
