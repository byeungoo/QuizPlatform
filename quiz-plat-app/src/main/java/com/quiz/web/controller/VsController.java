package com.quiz.web.controller;

import java.util.List;
import java.util.Locale;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quiz.web.dto.WritingDtlDto;
import com.quiz.web.dto.WritingVoteDto;
import com.quiz.web.service.WritingDtlService;
import com.quiz.web.service.WritingVoteService;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class VsController {
    
    private static final Logger logger = LoggerFactory.getLogger(VsController.class);
    
    @Autowired
    private WritingDtlService writingDtlService;
    
    @Autowired
    private WritingVoteService writingVoteService;
        
    /**
     * 메인화면
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Locale locale, Model model) throws Exception{

    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getWritingDtlList();
    	List<WritingVoteDto> writingVoteDtoList = writingVoteService.getWritingDtlList();
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	model.addAttribute("writingVoteDtoList", writingVoteDtoList);
    	
        return "home";
    }
    
    /**
     * 상세페이지
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Locale locale, Model model) throws Exception{

    	
    	//List<WritingDtlDto> writingDtlDtoList = writingDtlService.getWritingDtlList();
    	//List<WritingVoteDto> writingVoteDtoList = writingVoteService.getWritingDtlList();
    	//model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	//model.addAttribute("writingVoteDtoList", writingVoteDtoList);
    	
        return "detail";
    }
    
}
