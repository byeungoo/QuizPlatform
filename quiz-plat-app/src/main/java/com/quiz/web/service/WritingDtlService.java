package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.WritingDtlDao;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;

import common.PagingDto;

@Service
public class WritingDtlService {
	
    @Autowired
    private WritingDtlDao writingDtlDao;
    
    /*
     ** 최신 게신글 리스트 조회
     */
    public List<WritingDtlDto> getTextWritingList(PagingDto pagingDto) throws Exception{
    	
    	int start = (pagingDto.getPage_num()-1)*pagingDto.getPage_size();
    	int end = pagingDto.getPage_num()*pagingDto.getPage_size();
    	Integer mainCategory = pagingDto.getMainCategory();
    	
    	List<WritingDtlDto> pagingWritingDtlDtoList;
    
    	pagingDto.setStart(start);
    	pagingDto.setEnd(end);
    	
    	if(mainCategory == 1) { //인기순 조회
    		pagingWritingDtlDtoList = writingDtlDao.getHotTextWritingList(pagingDto);
    	} else if(mainCategory == 2){ //최신순 조회
    		pagingWritingDtlDtoList = writingDtlDao.getTextWritingList(pagingDto);
    	} else { //나의 활동내역 조회(3)
    		pagingWritingDtlDtoList = writingDtlDao.getMyVote(pagingDto);
    	}
    	
    	return pagingWritingDtlDtoList;
    }
    
    /*
     ** 인기 게시글 리스트 조회
     */  
    public List<WritingDtlDto> getHotTextWritingList(PagingDto pagingDto) throws Exception{
    	return writingDtlDao.getHotTextWritingList(pagingDto);
    }
    
    /*
     ** 나의 투표 리스트 조회
     */  
    public List<WritingDtlDto> getMyVote(PagingDto pagingDto) throws Exception{
    	return writingDtlDao.getMyVote(pagingDto);
    }
    
    /*
     ** 게시글 세부 내용 조회
     */
    public WritingDtlDto getWritingDtl(int writing_no) throws Exception{
    	
    	WritingDtlDto writingDtlDto = writingDtlDao.getTextWriting(writing_no);
    	
    	int total = writingDtlDto.getSum_vote();
    	double fir_vote_perc = Math.round((double)((double)writingDtlDto.getFir_vote_no()/(double)total) * 100);
    	double sec_vote_perc = Math.round((double)((double)writingDtlDto.getSec_vote_no()/(double)total) * 100);
    	writingDtlDto.setFir_vote_perc(fir_vote_perc);
    	writingDtlDto.setSec_vote_perc(sec_vote_perc);
    	
    	return writingDtlDto;
    }
    
    /*
     ** 게시글 투표 수 없데이트
     */  
    public void updateVoteNo(int writing_no, String fir_content_vote, String sec_content_vote) throws Exception{
    	if(fir_content_vote.equals("Y")) {
    		writingDtlDao.updateFirVoteNo(writing_no);
    	} else if(sec_content_vote.equals("Y")){
    		writingDtlDao.updateSecVoteNo(writing_no);
    	}
    }
    
    /*
     ** 게시글 작성  
     */
    public void insertWritingDtl(WritingDtlDto writingDtlDto) throws Exception{
    	writingDtlDao.insertWritingDtl(writingDtlDto);
    }
    
    /*
     ** 조회 수 증가
     */
    public void updateHits(int writing_no) throws Exception{
    	writingDtlDao.updateHits(writing_no);
    }
    
    /*
     ** 내가 투표 하지 않은 인기 순 게시물 4개 조회
     */
    public List<WritingDtlDto> getPopulWritingDtoList(PagingDto pagingDto) throws Exception{
    	
    	int start = (pagingDto.getPage_num()-1)*pagingDto.getPage_size();
    	int end = pagingDto.getPage_num()*pagingDto.getPage_size();
    	
    	pagingDto.setStart(start);
    	pagingDto.setEnd(end);
    	
    	return writingDtlDao.getPopulWritingDtoList(pagingDto);
    }
    
}
