package com.quiz.web.dao;

import java.util.List;

import com.quiz.web.dto.MemberDto;
 
public interface MemberDao {
    
    public List<MemberDto> selectMember() throws Exception;
}
