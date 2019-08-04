package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.WritingDtlDao;
import com.quiz.web.dto.WritingDtlDto;

import common.PagingDto;

@Service
public class WritingDtlService {
	
    @Autowired
    private WritingDtlDao writingDtlDao;
    
    /*
     ** �ֽ� �Խű� ����Ʈ ��ȸ
     */
    public List<WritingDtlDto> getTextWritingList(PagingDto pagingDto) throws Exception{
    	
    	int start = (pagingDto.getPage_num()-1)*pagingDto.getPage_size();
    	int end = pagingDto.getPage_num()*pagingDto.getPage_size();
    	Integer mainCategory = pagingDto.getMainCategory();
    	
    	List<WritingDtlDto> pagingWritingDtlDtoList;
    
    	pagingDto.setStart(start);
    	pagingDto.setEnd(end);
    	
    	if(mainCategory == 1) { //�α�� ��ȸ
    		pagingWritingDtlDtoList = writingDtlDao.getHotTextWritingList(pagingDto);
    	} else if(mainCategory == 2){ //�ֽż� ��ȸ
    		pagingWritingDtlDtoList = writingDtlDao.getTextWritingList(pagingDto);
    	} else { //���� Ȱ������ ��ȸ(3)
    		pagingWritingDtlDtoList = writingDtlDao.getMyVote(pagingDto);
    	}
    	
    	return pagingWritingDtlDtoList;
    }
    
    /*
     ** �α� �Խñ� ����Ʈ ��ȸ
     */  
    public List<WritingDtlDto> getHotTextWritingList(PagingDto pagingDto) throws Exception{
    	return writingDtlDao.getHotTextWritingList(pagingDto);
    }
    
    /*
     ** ���� ��ǥ ����Ʈ ��ȸ
     */  
    public List<WritingDtlDto> getMyVote(PagingDto pagingDto) throws Exception{
    	return writingDtlDao.getMyVote(pagingDto);
    }
    
    /*
     ** �Խñ� ���� ���� ��ȸ
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
     ** �Խñ� ��ǥ �� ������Ʈ
     */  
    public void updateVoteNo(int writing_no, String fir_content_vote, String sec_content_vote) throws Exception{
    	if(fir_content_vote.equals("Y")) {
    		writingDtlDao.updateFirVoteNo(writing_no);
    	} else if(sec_content_vote.equals("Y")){
    		writingDtlDao.updateSecVoteNo(writing_no);
    	}
    }
    
    /*
     ** �Խñ� �ۼ�  
     */
    public void insertWritingDtl(WritingDtlDto writingDtlDto) throws Exception{
    	writingDtlDao.insertWritingDtl(writingDtlDto);
    }
    
    /*
     ** ��ȸ �� ����
     */
    public void updateHits(int writing_no) throws Exception{
    	writingDtlDao.updateHits(writing_no);
    }
    
    /*
     ** ���� ��ǥ ���� ���� �α� �� �Խù� 4�� ��ȸ
     */
    public List<WritingDtlDto> getPopulWritingDtoList(PagingDto pagingDto) throws Exception{
    	
    	int start = (pagingDto.getPage_num()-1)*pagingDto.getPage_size();
    	int end = pagingDto.getPage_num()*pagingDto.getPage_size();
    	
    	pagingDto.setStart(start);
    	pagingDto.setEnd(end);
    	
    	return writingDtlDao.getPopulWritingDtoList(pagingDto);
    }
    
}
