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
     * ����ȭ�� ��ȸ
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Locale locale, Model model) throws Exception{
 
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getWritingDtlList();
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	
        return "home";
    }
    
    /*
     ** �Խñ� �ۼ������� ��ȸ
     */
    @RequestMapping(value="", method = RequestMethod.POST)
    public String write(Locale locale, Model model) throws Exception{
    	return "write";
    }
    
    /*
     ** �Խñ� �ۼ� 
     */
    @RequestMapping(value="/insert/write", method = RequestMethod.POST)
    public String insertWrite(HttpServletRequest request, Locale locale, Model model) throws Exception{
    	
    	//�Է¹��� �� ���ʿ� writingDtlDto�� ���� �� �ְ� �ϰ� ������?
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	WritingDtlDto writingDtlDto = new WritingDtlDto();
    	writingDtlService.insertWritingDtl(writingDtlDto);
    	
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	
    	//�ۼ� �� detail or result?
    	
    	return "detail";
    }
    
    /**
     * �������� ��ȸ
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Locale locale, Model model) throws Exception{
    	
    	HttpSession session = request.getSession();
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	paramWritingVoteDto.setWriting_no(writing_no);
    	paramWritingVoteDto.setUser_id(session.toString());
    	
    	//��ǥ ���� ���� üũ, �̹� �������� ��� ����������� �̵�
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
     * ��������� ��ȸ
     */
    @Transactional
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String result(HttpServletRequest request, Locale locale, Model model) throws Exception{
    	
    	HttpSession session = request.getSession();
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	String fir_content_vote = request.getParameter("fir_content_vote");
    	String sec_content_vote = request.getParameter("sec_content_vote");
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	
    	paramWritingVoteDto.setWriting_no(writing_no);
    	paramWritingVoteDto.setFir_content_vote("N");
    	paramWritingVoteDto.setSec_content_vote("Y");
    	paramWritingVoteDto.setUser_id(session.toString());
    	paramWritingVoteDto.setRegpe_id(session.toString());
    	paramWritingVoteDto.setModpe_id(session.toString());
    	
    	//��ǥ ���� ����
    	writingVoteService.insertWritingVoteDto(paramWritingVoteDto);
    	//��ǥ�� ������Ʈ
    	writingDtlService.updateVoteNo(writing_no, "N", "Y");
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	WritingVoteDto writingVoteDto = writingVoteService.getWritingDtlDto(paramWritingVoteDto);
    	    	
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	model.addAttribute("writingVoteDto", writingVoteDto);
    	
        return "result";
    }
    
}
