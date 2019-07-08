package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.web.dao.WritingVoteDao;
import com.quiz.web.dto.WritingVoteDto;

@Service
public class WritingVoteService {
	
    @Autowired
    private WritingVoteDao writingVoteDao;
    
    public WritingVoteDto getWritingDtlDto(int writing_no) throws Exception{
    	return writingVoteDao.getWritingVoteDto(writing_no);
    }
    
}
