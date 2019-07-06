package com.quiz.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.quiz.web.dao.MemberDao;
import com.quiz.web.dto.MemberDto;
import com.quiz.web.dto.WritingDtlDto;

@Service
public class MemberServiceImpl implements MemberService {
 
    @Autowired
    private MemberDao dao;
    
    @Override
    public List<MemberDto> selectMember() throws Exception {
 
        return dao.selectMember();
    }
    
    public List<WritingDtlDto> getWritingDtlList() throws Exception{
    	return dao.getTextWritingList();
    }
 
}