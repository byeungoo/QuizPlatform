package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.WritingDtlDao;
import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.WritingDtlDto;

import common.paging.dto.PagingDto;
import common.paging.dto.WritingDtlPagingDto;

@Service
public class WritingDtlService {
	
    @Autowired
    private WritingDtlDao writingDtlDao;
    
    /*
     ** 占쌍쏙옙 占쌉신깍옙 占쏙옙占쏙옙트 占쏙옙회
     */
    public List<WritingDtlDto> getTextWritingList(PagingDto pagingDto) throws Exception{
    	
    	int start = (pagingDto.getPage_num()-1)*pagingDto.getPage_size();
    	int end = pagingDto.getPage_num()*pagingDto.getPage_size();
    	Integer mainCategory = pagingDto.getMainCategory();
    	
    	List<WritingDtlDto> pagingWritingDtlDtoList;
    
    	pagingDto.setStart(start);
    	pagingDto.setEnd(end);
    	
    	if(mainCategory == 0) { //占싸깍옙占� 占쏙옙회
    		pagingWritingDtlDtoList = writingDtlDao.getHotTextWritingList(pagingDto);
    	} else if(mainCategory == 1){ //占쌍신쇽옙 占쏙옙회
    		pagingWritingDtlDtoList = writingDtlDao.getTextWritingList(pagingDto);
    	} else { //占쏙옙占쏙옙 활占쏙옙占쏙옙占쏙옙 占쏙옙회(3)
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
     ** 占쌉시깍옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙회
     */
    public WritingDtlDto getWritingDtl(int writing_no) throws Exception{
    	
    	WritingDtlDto writingDtlDto = writingDtlDao.getTextWriting(writing_no);
    	
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
    
}
