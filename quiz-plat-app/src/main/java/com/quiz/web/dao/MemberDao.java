package com.quiz.web.dao;

import java.util.List;

import com.quiz.web.dto.MemberDto;
import com.quiz.web.dto.WritingDtlDto;
 
public interface MemberDao {
    
    public List<MemberDto> selectMember() throws Exception;
    
    public List<WritingDtlDto> getTextWritingList() throws Exception;
}
