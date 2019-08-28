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
    	
    	WritingVoteDto writingVoteDto = new WritingVoteDto();
    	List<Integer> voteNoArr =  new ArrayList<Integer>();
    	List<Integer> votePerc =  new ArrayList<Integer>();
    	
    	try {
    		writingVoteDto = writingVoteDao.getWritingVoteDto(paramDto);
	    	int totalVoteNum = writingVoteDto.getFir_vote_no() + writingVoteDto.getSec_vote_no();
	    	voteNoArr.add(writingVoteDto.getFir_vote_no());
	    	voteNoArr.add(writingVoteDto.getSec_vote_no());
	    	votePerc.add(writingVoteDto.getFir_vote_perc());
	    	votePerc.add(writingVoteDto.getSec_vote_perc());
	    	
	    	writingVoteDto.setVoteNoArr(voteNoArr);
	    	writingVoteDto.setVotePerc(votePerc);
	    	writingVoteDto.setTotalVoteNum(totalVoteNum);
    	}  catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	
    	return writingVoteDto;
    }
    
    public void insertWritingVoteDto(ParamDto paramDto) throws Exception{
    	try {
    		writingVoteDao.insertWritingVoteDto(paramDto);
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    public String chekVote(WritingVoteDto writingVoteDto) throws Exception{
    	
    	String vote = "";
    	
    	try {
    		vote = writingVoteDao.chekVote(writingVoteDto);
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	
    	return vote;
    }
    
}
