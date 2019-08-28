package com.quiz.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.controller.MainController;
import com.quiz.web.dao.WritingDtlDao;
import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;

import common.paging.dto.PagingDto;
import common.paging.dto.WritingDtlPagingDto;

@Service
public class WritingDtlService {
	
	private static final Logger logger = LoggerFactory.getLogger(WritingDtlService.class);
	
    @Autowired
    private WritingDtlDao writingDtlDao;
    
    /*
     ** 메인페이지 카테고리에따른 게시글 조회
     */
    public List<WritingDtlDto> getMainPageWritingList(PagingDto pagingDto) throws Exception{
    	
    	int start = (pagingDto.getPage_num()-1)*pagingDto.getPage_size();
    	int end = pagingDto.getPage_num()*pagingDto.getPage_size();
    	Integer mainCategory = pagingDto.getMainCategory();
    	
    	List<WritingDtlDto> pagingWritingDtlDtoList = new ArrayList<WritingDtlDto>();
    
    	pagingDto.setStart(start);
    	pagingDto.setEnd(end);
    	
    	try {
        	if(mainCategory == 0) { 	  //인기글 리스트 조회
        		pagingWritingDtlDtoList = writingDtlDao.getHotTextWritingList(pagingDto);
        	} else if(mainCategory == 1){ //최근글 리스트 조회
        		pagingWritingDtlDtoList = writingDtlDao.getTextWritingList(pagingDto);
        	} else if(mainCategory == 2){ //투표 게시글 조회
        		pagingWritingDtlDtoList = writingDtlDao.getMyVote(pagingDto);
        	} else if(mainCategory == 3) { //투표+댓글단 게시글 조회
        		pagingWritingDtlDtoList = writingDtlDao.getMyCommentWritingList(pagingDto);
        	} else if(mainCategory == 4) { //내가 작성한 게시글 조회
        		pagingWritingDtlDtoList = writingDtlDao.getMyWritingList(pagingDto);
        	}
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	
    	return pagingWritingDtlDtoList;
    }
    
    /*
     ** 게시글 1개 상세 조회
     */
    public WritingDtlDto getWritingDtl(ParamDto paramDto) throws Exception{
    	
    	WritingDtlDto writingDtlDto = new WritingDtlDto();
    	
    	try {
    		writingDtlDto = writingDtlDao.getTextWriting(paramDto);
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	
    	return writingDtlDto;
    }
    
    /*
     ** 게시글 투표 수 업데이트
     */  
    public void updateVote(ParamDto paramDto) throws Exception{
    	try {
        	if(paramDto.getVote() == 1) {
        		writingDtlDao.updateFirVote(paramDto);
        	} else if(paramDto.getVote() == 2) {
        		writingDtlDao.updateSecVote(paramDto);
        	}
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    /*
     ** 게시글 작성
     */
    public WritingDtlDto insertWritingDtl(WritingDtlDto writingDtlDto) throws Exception{
    	try {
    		writingDtlDao.insertWritingDtl(writingDtlDto);
    		writingDtlDto = writingDtlDao.getMainWritingDtlDto(writingDtlDto); //작성한 게시글 정보 조회
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	
    	return writingDtlDto;
    }
    
    /*
     ** 글 조회 수 업데이트
     */
    public void updateHits(int writing_no) throws Exception{
    	try {
    		writingDtlDao.updateHits(writing_no);
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    /*
     ** 인기리스트 페이징 조회
     */
    public List<WritingDtlDto> getPopulWritingDtoList(WritingDtlPagingDto writingDtlPagingDto) throws Exception{
 
    	int start = (writingDtlPagingDto.getPage_num()-1)*writingDtlPagingDto.getPage_size();
    	int end = writingDtlPagingDto.getPage_num()*writingDtlPagingDto.getPage_size();
    	
    	writingDtlPagingDto.setStart(start);
    	writingDtlPagingDto.setEnd(end);
    	
    	List<WritingDtlDto> writingDtlListDto = new ArrayList<WritingDtlDto>();
    	
    	try {
    		writingDtlListDto = writingDtlDao.getPopulWritingDtoList(writingDtlPagingDto);
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	} 
    	
    	return writingDtlListDto;
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
