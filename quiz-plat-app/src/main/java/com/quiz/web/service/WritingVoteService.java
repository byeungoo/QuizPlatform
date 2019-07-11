package com.quiz.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.WritingVoteDao;
import com.quiz.web.dto.WritingVoteDto;

@Service
public class WritingVoteService {
	
    @Autowired
    private WritingVoteDao writingVoteDao;
    
    public WritingVoteDto getWritingDtlDto(WritingVoteDto writingVoteDto) throws Exception{
    	return writingVoteDao.getWritingVoteDto(writingVoteDto);
    }
    
    public void insertWritingVoteDto(WritingVoteDto writingVoteDto) throws Exception{
    	writingVoteDao.insertWritingVoteDto(writingVoteDto);
    }
    
    public String chekVote(WritingVoteDto writingVoteDto) throws Exception{
    	return writingVoteDao.chekVote(writingVoteDto);
    }
    
}
