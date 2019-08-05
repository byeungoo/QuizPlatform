package com.quiz.web.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.web.dto.CommentDto;
import com.quiz.web.dto.DetailDto;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;
import com.quiz.web.dto.WritingVoteDto;
import com.quiz.web.service.CommentService;
import com.quiz.web.service.UserService;
import com.quiz.web.service.WritingDtlService;
import com.quiz.web.service.WritingVoteService;

import common.PagingDto;
 

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
 
    	PagingDto pagingDto = new PagingDto();
    	pagingDto.setPage_num(1);
    	pagingDto.setMainCategory(1);
  	
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getTextWritingList(pagingDto);
    	List<WritingDtlDto> writingPopulDtoList = writingDtlService.getHotTextWritingList(pagingDto);
    	List<WritingDtlDto> writingMyVoteDtoList = writingDtlService.getMyVote(pagingDto);
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	model.addAttribute("writingPopulDtoList", writingPopulDtoList);
    	model.addAttribute("writingMyVoteDtoList", writingMyVoteDtoList);
    	
        return "home";
    }
    
    /*
     ** 최신순 조회 
     */
    @RequestMapping(value = "/latestWriting", method = RequestMethod.GET) 
    public String latestWriting(HttpSession session, HttpServletRequest request, Model model) throws Exception{
    	
    	PagingDto pagingDto = new PagingDto();
    	pagingDto.setPage_num(1);
    	pagingDto.setMainCategory(2);
    	
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getTextWritingList(pagingDto);
    	model.addAttribute(writingDtlDtoList);
        return "home";
    }
    
    /**
     * 나의 활동내역 최신순 조회
     */
    @RequestMapping(value = "/getMyVote", method = RequestMethod.GET)
    public String getMyVote(HttpSession session, HttpServletRequest request, Model model) throws Exception{
 
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	
    	PagingDto pagingDto = new PagingDto();
    	pagingDto.setPage_num(1);
    	pagingDto.setMainCategory(3);
    	pagingDto.setUser_id(userDto.getUser_id());
    	
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getTextWritingList(pagingDto);
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	
        return "home";
    }
    
    /*
     ** 메인페이지 비동기 페이징 처리
     */
    @RequestMapping(value = "/getPaigingList", method = RequestMethod.GET)
    public @ResponseBody List<WritingDtlDto> getPaigingList(HttpSession session, HttpServletRequest request
    		          , @RequestParam(value="page") String page, @RequestParam(value="mainCategory") String mainCategory) throws Exception{
    	
    	//유저정보 획득
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	
    	//parameter로 줄 pagingDto 값 세팅
    	PagingDto pagingDto = new PagingDto();
    	pagingDto.setPage_num(Integer.parseInt(page));
    	pagingDto.setUser_id(userDto.getUser_id());
    	pagingDto.setMainCategory(Integer.parseInt(mainCategory));
    	
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getTextWritingList(pagingDto);

        return writingDtlDtoList;
    }
    
    /*
     ** 글작성 페이지 이동
     */
    @RequestMapping(value="write", method = RequestMethod.GET)
    public String write(Model model) throws Exception{
    	return "write";
    }
    
    /*
     ** 투표 작성
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
    	
    	PagingDto pagingDto = new PagingDto();
    	pagingDto.setPage_num(1);
    	
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getTextWritingList(pagingDto);
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	model.addAttribute("toastOn", "Y");
    	
    	return "redirect:/";
    }
    
    /*
     ** 상세페이지 이동, 클릭한 글과 인기 컨텐츠 4개를 뿌려줌
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model) throws Exception{
    	
    	HttpSession session = request.getSession();
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	
    	//유저 정보 조회
    	UserDto user = userService.getUesrSettingDto(session, request);
        paramWritingVoteDto.setUser_id(user.getUser_id());
        paramWritingVoteDto.setRegpe_id(user.getUser_id());
        paramWritingVoteDto.setModpe_id(user.getUser_id());
        
        //페이징 정보 세팅
    	PagingDto pagingDto = new PagingDto();
    	pagingDto.setUser_id(user.getUser_id());
    	pagingDto.setPage_num(1);
    	pagingDto.setPage_size(5);
        
        //최종 결과 담을 객체 생성 및 인기컨텐츠 정보로 초기화
        DetailDto detailDto = new DetailDto();
	    List<WritingDtlDto> detailWritingList = writingDtlService.getPopulWritingDtoList(pagingDto);
	    //List<WritingVoteDto> detailWritingVoteList = new ArrayList();
	    HashMap<Integer, List<CommentDto>> detailCommentList = new HashMap<Integer,  List<CommentDto>>();
	    
	    //해당 글에 대한 댓글 정보 조회 및 추가
	    for(WritingDtlDto tempDto :detailWritingList) {
	    	detailCommentList.put(tempDto.getWriting_no() , commentService.getCommentDtoList(tempDto.getWriting_no()));
	    }
	    
        //클릭한 상세 글정보 
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	//클릭한 상세 투표정보
    	WritingVoteDto writingVoteDto = writingVoteService.getWritingVoteDto(paramWritingVoteDto);
    	//클릭한 상세 댓글 정보
    	List<CommentDto> commentDtoList = commentService.getCommentDtoList(writing_no);
    	
    	//클릭한 글 정보 추가
    	detailWritingList.add(0, writingDtlDto);
    	//detailWritingVoteList.add(0, writingVoteDto);
    	detailCommentList.put(writingDtlDto.getWriting_no() , commentDtoList);
    	

    	detailDto.setDetailCommentList(detailCommentList);
    	detailDto.setDetailWritingList(detailWritingList);
    	
    	model.addAttribute("detailDto", detailDto);
    	
    	//model.addAttribute("writingDtlDto", writingDtlDto);
    	//model.addAttribute("writingVoteDto", writingVoteDto);
    	//model.addAttribute("commentDtoList", commentDtoList);
    	
    	//조회 수 증가
        writingDtlService.updateHits(writing_no);
    	
        return "detail";
    }
    
    /*
     ** 결과페이지
     */
    /*
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
    	//유저 정보 조회
    	UserDto user = userService.getUesrSettingDto(session, request);
        paramWritingVoteDto.setUser_id(user.getUser_id());
        paramWritingVoteDto.setRegpe_id(user.getUser_id());
        paramWritingVoteDto.setModpe_id(user.getUser_id());
	
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
        writingDtlService.updateHits(writing_no, userDto);
    	
        return "result";
    }
    */
    
    /*
     ** 댓글 작성
     */
    @Transactional
    @RequestMapping(value = "writeComment", method = RequestMethod.GET)
    public @ResponseBody CommentDto writeComment(HttpSession session, HttpServletRequest request, @RequestParam(value="replytx") String replytx, @RequestParam(value="writingNo") String writingNo) throws Exception{
    	logger.info("writeComment호출");
    	session    = request.getSession();
    	    	
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	if(userDto.getReg_div_cd().equals("10")) {
        	
    	} else if(userService.chekUserId(session.toString()) == 0) {  //비회원 작성 시 회원테이블에 세션값없으면 등록
        	userDto.setNickname(userService.getNickname());
        	userService.insertUser(userDto);
        	userService.updateNickname(userDto.getNickname());
    	}
    	
    	//comment -> 비동기로 받아올 수 있도록 처리
    	//int writing_no = Integer.parseInt(request.getParameter("writing_no"));
        int writing_no = Integer.parseInt((writingNo));
    	String comment_content = replytx;
    	int like = 0;
    	CommentDto commentDto = new CommentDto();
    	commentDto.setWriting_no(writing_no);
    	commentDto.setComment_content(comment_content);
    	commentDto.setRecom_no(like);
    	commentDto.setRegpe_id(userDto.getUser_id());
    	commentDto.setNickname(userDto.getNickname());
    	commentService.insertComment(commentDto);   	

    	return commentDto;
    }
    
    /*
     ** 댓글작성 redirect
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
