package com.quiz.web.service;

import java.util.List;

import com.quiz.web.dto.MemberDto;
import com.quiz.web.dto.WritingDtlDto;
 
public interface MemberService {
    
    public List<MemberDto> selectMember() throws Exception;
    public List<WritingDtlDto> getWritingDtlList() throws Exception;
}
