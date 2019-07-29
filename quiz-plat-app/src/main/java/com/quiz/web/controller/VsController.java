package com.quiz.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quiz.web.dto.CommentDto;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;
import com.quiz.web.dto.WritingVoteDto;
import com.quiz.web.service.CommentService;
import com.quiz.web.service.UserService;
import com.quiz.web.service.WritingDtlService;
import com.quiz.web.service.WritingVoteService;
import common.SHA256;
 

@Controller
public class VsController {
    
    private static final Logger logger = LoggerFactory.getLogger(VsController.class);
    
    @Autowired
    private WritingDtlService writingDtlService;
    
    @Autowired
    private WritingVoteService writingVoteService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentService;
        
    /**
     * 인기순 조회(메인)
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) throws Exception{
 
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getHotTextWritingList();
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	
        return "home";
    }
    
    /**
     * 최신순 조회
     */
    @RequestMapping(value = "/latestWriting", method = RequestMethod.GET)
    public String latestWriting(HttpSession session, HttpServletRequest request, Model model) throws Exception{
 
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getTextWritingList();
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	
        return "home";
    }
    
    /**
     * 나의 활동내역 최신순 조회
     */
    @RequestMapping(value = "/getMyVote", method = RequestMethod.GET)
    public String getMyVote(HttpSession session, HttpServletRequest request, Model model) throws Exception{
 
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getMyVote(userDto.getUser_id());
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	
        return "home";
    }
    /*
     ** 湲� �옉�꽦 �럹�씠吏� �씠�룞
     */
    @RequestMapping(value="write", method = RequestMethod.GET)
    public String write(Model model) throws Exception{
    	return "write";
    }
    
    /*
     ** 湲� �옉�꽦
     */
    @Transactional
    @RequestMapping(value="/insert", method = RequestMethod.POST)
    public String insertWrite(HttpServletRequest request, Model model) throws Exception{
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
    	
    	//�옉�꽦湲� �엳�뒗吏� �솗�씤
    	if(userService.chekUserId(session.toString()) == 0) {
    		UserDto userDto = new UserDto();
        	userDto.setUser_id(session.toString());
        	userDto.setRegpe_id(session.toString());
        	userDto.setNickname(userService.getNickname());
        	userDto.setReg_div_cd("20");
        	userService.insertUser(userDto);
        	userService.updateNickname(userDto.getNickname());
    	} 
    	
    	writingDtlService.insertWritingDtl(writingDtlDto);
    	
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getTextWritingList();
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	model.addAttribute("toastOn", "Y");
    	
    	return "redirect:/";
    }
    
    /*
     ** 湲� �긽�꽭 �럹�씠吏� 議고쉶
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model) throws Exception{
    	
    	HttpSession session = request.getSession();
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	
    	Object userDto = session.getAttribute("login");
        if(userDto!=null) {  //로그인 정보 유지 시, 유저 아이디 세팅
        	UserDto user = (UserDto) userDto;
        	paramWritingVoteDto.setWriting_no(writing_no);
        	paramWritingVoteDto.setUser_id(user.getUser_id());
        } else {            //로그인 정보가 없을 경우(비회원), 세션 아이디 세팅
        	paramWritingVoteDto.setWriting_no(writing_no);
        	paramWritingVoteDto.setUser_id(session.toString());
        }
	    	
    	//�씠誘� �닾�몴瑜� �뻽�뒗吏� 寃��궗
    	if(writingVoteService.chekVote(paramWritingVoteDto).equals("Y")) {
    		WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
        	WritingVoteDto writingVoteDto = writingVoteService.getWritingVoteDto(paramWritingVoteDto);
        	List<CommentDto> commentDtoList = commentService.getCommentDtoList(writing_no);
        	
        	model.addAttribute("writingDtlDto", writingDtlDto);
        	model.addAttribute("writingVoteDto", writingVoteDto);
        	model.addAttribute("commentDtoList", commentDtoList);
        	
        	//조회 수 증가
            writingDtlService.updateHits(writing_no);
        	
            return "result";
    	} 
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	
        return "detail";
    }
    
    /*
     ** 寃곌낵�럹�씠吏�
     */
    @Transactional
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String result(HttpServletRequest request, Model model) throws Exception{
    	
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
    	
    	Object userDto = session.getAttribute("login");
        if(userDto!=null) {  //로그인 정보 유지 시, 유저 아이디 세팅
        	UserDto user = (UserDto) userDto;
        	paramWritingVoteDto.setUser_id(user.getUser_id());
        	paramWritingVoteDto.setRegpe_id(user.getUser_id());
        	paramWritingVoteDto.setModpe_id(user.getUser_id());
        } else {            //로그인 정보가 없을 경우(비회원), 세션 아이디 세팅
        	paramWritingVoteDto.setUser_id(session.toString());
        	paramWritingVoteDto.setRegpe_id(session.toString());
        	paramWritingVoteDto.setModpe_id(session.toString());
        }
	
    	if(writingVoteService.chekVote(paramWritingVoteDto).equals("N")) {
    		writingVoteService.insertWritingVoteDto(paramWritingVoteDto);
        	writingDtlService.updateVoteNo(writing_no, paramWritingVoteDto.getFir_content_vote(), paramWritingVoteDto.getSec_content_vote());
    	}
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	WritingVoteDto writingVoteDto = writingVoteService.getWritingVoteDto(paramWritingVoteDto);
    	List<CommentDto> commentDtoList = commentService.getCommentDtoList(writing_no);
    	
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	model.addAttribute("writingVoteDto", writingVoteDto);
    	model.addAttribute("commentDtoList", commentDtoList);
    	
    	//조회 수 증가
        writingDtlService.updateHits(writing_no);
    	
        return "result";
    }
    
    /*
     ** �뙎湲� �옉�꽦
     */
    @Transactional
    @RequestMapping(value = "writeComment", method = RequestMethod.POST)
    public String writeComment(HttpServletRequest request, RedirectAttributes redirect) throws Exception{
    	
    	HttpSession session    = request.getSession();
    	    	
    	UserDto userDto = new UserDto();
    	if(userService.chekUserId(session.toString()) == 0) {
        	userDto.setUser_id(session.toString());
        	userDto.setRegpe_id(session.toString());
        	userDto.setNickname(userService.getNickname());
        	userDto.setReg_div_cd("20");
        	userService.insertUser(userDto);
        	userService.updateNickname(userDto.getNickname());
    	} 
    	
    	//�뙎湲� insert
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
        String fir_content_vote = request.getParameter("fir_content_vote");
        String comment_content = request.getParameter("comment_content");
    	int like = 0;
    	CommentDto commentDto = new CommentDto();
    	commentDto.setWriting_no(writing_no);
    	commentDto.setComment_content(comment_content);
    	commentDto.setRecom_no(like);
    	commentDto.setRegpe_id(session.toString());
    	commentService.insertComment(commentDto);   	
    	
    	//寃곌낵�럹�씠吏� �씠�룞
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	paramWritingVoteDto.setWriting_no(writing_no);
    	paramWritingVoteDto.setUser_id(session.toString());
    	
        redirect.addFlashAttribute("writing_no", writing_no);
        if(fir_content_vote.equals("Y")) {
        	redirect.addFlashAttribute("inputState", "before");
        }else {
        	redirect.addFlashAttribute("inputState", "after");
        }

    	return "redirect:/resultComm";
    }
    
    /*
     ** �뙎湲� redirect
     */
    @RequestMapping(value = "/resultComm", method = RequestMethod.GET)
    public String resultComm(HttpServletRequest request, @ModelAttribute("writing_no") int writing_no, 
     @ModelAttribute("inputState") String inputState, Model model) throws Exception{
    	
    	HttpSession session = request.getSession();
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
    	    	
    	if(writingVoteService.chekVote(paramWritingVoteDto).equals("N")) {
    		writingVoteService.insertWritingVoteDto(paramWritingVoteDto);
        	writingDtlService.updateVoteNo(writing_no, paramWritingVoteDto.getFir_content_vote(), paramWritingVoteDto.getSec_content_vote());
    	}
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	WritingVoteDto writingVoteDto = writingVoteService.getWritingVoteDto(paramWritingVoteDto);
    	List<CommentDto> commentDtoList = commentService.getCommentDtoList(writing_no);
    	
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	model.addAttribute("writingVoteDto", writingVoteDto);
    	model.addAttribute("commentDtoList", commentDtoList);
    	
    	return "redirect:result";
    }
}
