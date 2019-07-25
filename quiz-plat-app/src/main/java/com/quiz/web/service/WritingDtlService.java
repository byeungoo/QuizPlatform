package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.WritingDtlDao;
import com.quiz.web.dto.WritingDtlDto;

@Service
public class WritingDtlService {
	
    @Autowired
    private WritingDtlDao writingDtlDao;
    
    public List<WritingDtlDto> getWritingDtlList() throws Exception{
    	return writingDtlDao.getTextWritingList();
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
    
}
