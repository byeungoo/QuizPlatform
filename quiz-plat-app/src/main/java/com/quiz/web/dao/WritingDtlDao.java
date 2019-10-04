package com.quiz.web.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.WritingDtlDto;

import common.paging.dto.PagingDto;
import common.paging.dto.WritingDtlPagingDto;

@Repository
public class WritingDtlDao {
	
	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.quiz.mapper.writingDtlMapper";
    
    //최근 게시글 리스트 조회
    public List<WritingDtlDto> getTextWritingList(PagingDto pagingDto) throws Exception{
        return sqlSession.selectList(Namespace+".getTextWritingList", pagingDto);
    }
    
    public WritingDtlDto getTextWriting(ParamDto paramDto) throws Exception{
        return sqlSession.selectOne(Namespace+".getTextWriting", paramDto);
    }
    
    //게시글 작성
    public void insertWritingDtl(WritingDtlDto writingDtlDto) throws Exception{
    	sqlSession.insert(Namespace+".insertWritingDtl", writingDtlDto);
    }
    
    //조회수 업데이트
    public void updateHits(int writing_no) throws Exception{
    	sqlSession.update(Namespace+".updateHits", writing_no);
    }
    
    //인기글 리스트 조회
    public List<WritingDtlDto> getHotTextWritingList(PagingDto pagingDto) throws Exception{
        return sqlSession.selectList(Namespace+".getHotTextWritingList", pagingDto);
    }
    
    //사용자가 투표한 게시글 리스트 조회
    public List<WritingDtlDto> getMyVote(PagingDto pagingDto) throws Exception{
    	return sqlSession.selectList(Namespace+".getMyVote", pagingDto);
    }
   
    //사용자가 댓글단 게시글 리스트 조회
    public List<WritingDtlDto> getMyCommentWritingList(PagingDto pagingDto) throws Exception{
    	return sqlSession.selectList(Namespace+".getMyCommentWritingList", pagingDto);
    }

    //사용자가 작성한 게시글 리스트 조회
    public List<WritingDtlDto> getMyWritingList(PagingDto pagingDto) throws Exception{
    	return sqlSession.selectList(Namespace+".getMyWritingList", pagingDto);
    }
    
    //인기 게시글 리스트 조회
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
    
    //메인 카드 게시글 조회
    public WritingDtlDto getMainWritingDtlDto(WritingDtlDto writingDtlDto) throws Exception{
    	return sqlSession.selectOne(Namespace+".getMainWritingDtlDto", writingDtlDto);
    }
    
    //게시글 사용여부 'N' 변경
    public void updateWritingUseYn(WritingDtlDto writingDtlDto) throws Exception{
    	sqlSession.update(Namespace+".updateWritingUseYn", writingDtlDto);
    }
    
    //신고 게시글 사용여부 'N' 일괄 변경
    public void reportWritingApply() throws Exception{
    	sqlSession.update(Namespace+".reportWritingApply");
    }
    
    //게시글 이미지 파일 추가
    public void insertWritingImgFile(WritingDtlDto writingDtlDto) throws Exception{
    	sqlSession.insert(Namespace+".insertWritingImgFile", writingDtlDto);
    }
    
}
