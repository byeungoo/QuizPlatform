package com.quiz.web.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.WritingDtlDao;
import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;

import common.paging.dto.PagingDto;
import common.paging.dto.WritingDtlPagingDto;

@Service
public class WritingDtlService {
	
    @Autowired
    private WritingDtlDao writingDtlDao;
    
    /*
     ** 메인페이지 게시글 조회
     */
    public List<WritingDtlDto> getTextWritingList(PagingDto pagingDto) throws Exception{
    	
    	int start = (pagingDto.getPage_num()-1)*pagingDto.getPage_size();
    	int end = pagingDto.getPage_num()*pagingDto.getPage_size();
    	Integer mainCategory = pagingDto.getMainCategory();
    	
    	List<WritingDtlDto> pagingWritingDtlDtoList;
    
    	pagingDto.setStart(start);
    	pagingDto.setEnd(end);
    	
    	if(mainCategory == 0) { //인기글 리스트 조회
    		pagingWritingDtlDtoList = writingDtlDao.getHotTextWritingList(pagingDto);
    	} else if(mainCategory == 1){ //최근글 리스트 조회
    		pagingWritingDtlDtoList = writingDtlDao.getTextWritingList(pagingDto);
    	} else { //나의 활동 내역 조회
    		pagingWritingDtlDtoList = writingDtlDao.getMyVote(pagingDto);
    	}
    	
    	return pagingWritingDtlDtoList;
    }
    
    /*
     ** 占싸깍옙 占쌉시깍옙 占쏙옙占쏙옙트 占쏙옙회
     */  
    public List<WritingDtlDto> getHotTextWritingList(PagingDto pagingDto) throws Exception{
    	return writingDtlDao.getHotTextWritingList(pagingDto);
    }
    
    /*
     ** 占쏙옙占쏙옙 占쏙옙표 占쏙옙占쏙옙트 占쏙옙회
     */  
    public List<WritingDtlDto> getMyVote(PagingDto pagingDto) throws Exception{
    	return writingDtlDao.getMyVote(pagingDto);
    }
    
    /*
     ** 게시글 1개 상세 조회
     */
    public WritingDtlDto getWritingDtl(ParamDto paramDto) throws Exception{
    	
    	WritingDtlDto writingDtlDto = writingDtlDao.getTextWriting(paramDto);
    	
    	return writingDtlDto;
    }
    
    /*
     ** 게시글 투표 수 업데이트
     */  
    public void updateVote(ParamDto paramDto) throws Exception{
    	
    	if(paramDto.getVote() == 1) {
    		writingDtlDao.updateFirVote(paramDto);
    	} else if(paramDto.getVote() == 2) {
    		writingDtlDao.updateSecVote(paramDto);
    	}
    }
    
    /*
     ** 占쌉시깍옙 占쌜쇽옙  
     */
    public void insertWritingDtl(WritingDtlDto writingDtlDto) throws Exception{
    	writingDtlDao.insertWritingDtl(writingDtlDto);
    }
    
    /*
     ** 글 조회 수 업데이트
     */
    public void updateHits(int writing_no) throws Exception{
    	writingDtlDao.updateHits(writing_no);
    }
    
    /*
     ** 인기리스트 페이징 조회
     */
    public List<WritingDtlDto> getPopulWritingDtoList(WritingDtlPagingDto writingDtlPagingDto) throws Exception{
    	
    	int start = (writingDtlPagingDto.getPage_num()-1)*writingDtlPagingDto.getPage_size();
    	int end = writingDtlPagingDto.getPage_num()*writingDtlPagingDto.getPage_size();
    	
    	writingDtlPagingDto.setStart(start);
    	writingDtlPagingDto.setEnd(end);
    	
    	return writingDtlDao.getPopulWritingDtoList(writingDtlPagingDto);
    }
    
    /*
     ** 게시글 신고 처리
     */
    public boolean reportWriting(HttpSession session, ParamDto paramDto) throws Exception{
    	
    	boolean report = true; //true:신고
    	paramDto.setReport(report);
    	try{
    		writingDtlDao.reportWriting(paramDto); //신고 테이블 insert
    	} catch(Exception e) {
    		report = false;     //게시글 신고 실패
    		System.err.println(e.getMessage());
    	}
    	
    	return report;
    }
}
