package com.quiz.web.service;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;
 
import com.quiz.web.dao.MemberDao;
import com.quiz.web.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
 
    @Inject
    private MemberDao dao;
    
    @Override
    public List<MemberDto> selectMember() throws Exception {
 
        return dao.selectMember();
    }
 
}