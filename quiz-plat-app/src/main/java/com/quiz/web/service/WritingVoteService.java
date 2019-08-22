package com.quiz.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.WritingVoteDao;
import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.WritingVoteDto;

@Service
public class WritingVoteService {
	
    @Autowired
    private WritingVoteDao writingVoteDao;
    
    public WritingVoteDto getWritingVoteDto(ParamDto paramDto) throws Exception{
    	
    	WritingVoteDto writingVoteDto = writingVoteDao.getWritingVoteDto(paramDto);
    	
    	List<Integer> voteNoArr =  new ArrayList<Integer>();
    	List<Integer> votePerc =  new ArrayList<Integer>();
    	int totalVoteNum = writingVoteDto.getFir_vote_no() + writingVoteDto.getSec_vote_no();
    	voteNoArr.add(writingVoteDto.getFir_vote_no());
    	voteNoArr.add(writingVoteDto.getSec_vote_no());
    	votePerc.add(writingVoteDto.getFir_vote_perc());
    	votePerc.add(writingVoteDto.getSec_vote_perc());
    	
    	writingVoteDto.setVoteNoArr(voteNoArr);
    	writingVoteDto.setVotePerc(votePerc);
    	writingVoteDto.setTotalVoteNum(totalVoteNum);
    	
    	return writingVoteDto;
    }
    
    public void insertWritingVoteDto(ParamDto paramDto) throws Exception{
    	writingVoteDao.insertWritingVoteDto(paramDto);
    }
    
    public String chekVote(WritingVoteDto writingVoteDto) throws Exception{
    	return writingVoteDao.chekVote(writingVoteDto);
    }
    
}
