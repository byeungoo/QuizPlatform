package com.quiz.web.service;

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
    	return writingVoteDao.getWritingVoteDto(paramDto);
    }
    
    public void insertWritingVoteDto(ParamDto paramDto) throws Exception{
    	writingVoteDao.insertWritingVoteDto(paramDto);
    }
    
    public String chekVote(WritingVoteDto writingVoteDto) throws Exception{
    	return writingVoteDao.chekVote(writingVoteDto);
    }
    
}
