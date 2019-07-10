package com.quiz.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
     * 메인화면 조회
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Locale locale, Model model) throws Exception{
 
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getWritingDtlList();
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	
        return "home";
    }
    
    /*
     ** 게시글 작성페이지 조회
     */
    @RequestMapping(value="write", method = RequestMethod.GET)
    public String write(Locale locale, Model model) throws Exception{
    	return "write";
    }
    
    /*
     ** 게시글 작성 
     */
    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public String insertWrite(HttpServletRequest request, Locale locale, Model model) throws Exception{
    	
    	HttpSession   session           = request.getSession();
    	WritingDtlDto writingDtlDto     = new WritingDtlDto();   	
    	String        content 			= request.getParameter("content");
    	String        fir_writ_content  = request.getParameter("fir_writ_content");
    	String        sec_writ_content  = request.getParameter("sec_writ_content");
    	String        ques_type_div_cd  = "10";
    	
    	writingDtlDto.setContent(content);
    	writingDtlDto.setFir_writ_content(fir_writ_content);
    	writingDtlDto.setSec_writ_content(sec_writ_content);
    	writingDtlDto.setQues_type_div_cd(ques_type_div_cd);
    	writingDtlDto.setRegpe_id(session.toString());
    	writingDtlDto.setModpe_id(session.toString());
    	
    	writingDtlService.insertWritingDtl(writingDtlDto);
    	
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	
    	//작성 후 detail or result?
    	
    	return "result";
    }
    
    /**
     * 상세페이지 조회
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Locale locale, Model model) throws Exception{
    	
    	HttpSession session = request.getSession();
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	paramWritingVoteDto.setWriting_no(writing_no);
    	paramWritingVoteDto.setUser_id(session.toString());
    	
    	//투표 참여 여부 체크, 이미 참여했을 경우 결과페이지로 이동
    	if(writingVoteService.chekVote(paramWritingVoteDto).equals("Y")) {
    		WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
        	WritingVoteDto writingVoteDto = writingVoteService.getWritingDtlDto(paramWritingVoteDto);
        	    	
        	model.addAttribute("writingDtlDto", writingDtlDto);
        	model.addAttribute("writingVoteDto", writingVoteDto);
        	
            return "result";
    	} 
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	
        return "detail";
    }
    
    /**
     * 결과페이지 조회
     */
    @Transactional
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String result(HttpServletRequest request, Locale locale, Model model) throws Exception{
    	
    	HttpSession session = request.getSession();
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	String inputState = request.getParameter("inputState");
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	
    	paramWritingVoteDto.setWriting_no(writing_no);
    	if(inputState.equals("before")){
    		paramWritingVoteDto.setFir_content_vote("Y");
        	paramWritingVoteDto.setSec_content_vote("N");
    	}else {
    		paramWritingVoteDto.setFir_content_vote("N");
        	paramWritingVoteDto.setSec_content_vote("Y");
    	}
    	paramWritingVoteDto.setUser_id(session.toString());
    	paramWritingVoteDto.setRegpe_id(session.toString());
    	paramWritingVoteDto.setModpe_id(session.toString());
    	
    	//투표 내용 삽입
    	writingVoteService.insertWritingVoteDto(paramWritingVoteDto);
    	//투표수 업데이트
    	writingDtlService.updateVoteNo(writing_no, paramWritingVoteDto.getFir_content_vote(), paramWritingVoteDto.getSec_content_vote());
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	WritingVoteDto writingVoteDto = writingVoteService.getWritingDtlDto(paramWritingVoteDto);
    	    	
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	model.addAttribute("writingVoteDto", writingVoteDto);
    	
        return "result";
    }
    
}
