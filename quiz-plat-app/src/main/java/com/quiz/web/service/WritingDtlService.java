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
}
