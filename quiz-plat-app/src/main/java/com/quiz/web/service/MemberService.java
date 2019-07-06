package com.quiz.web.service;

import java.util.List;

import com.quiz.web.dto.MemberDto;
 
public interface MemberService {
    
    public List<MemberDto> selectMember() throws Exception;
}
