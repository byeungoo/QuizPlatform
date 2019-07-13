package com.quiz.web.controller;

import java.util.List;

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
     * 메인화면 조회
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) throws Exception{
 
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getWritingDtlList();
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	
        return "home";
    }
    
    /*
     ** 게시글 작성페이지 조회
     */
    @RequestMapping(value="write", method = RequestMethod.GET)
    public String write(Model model) throws Exception{
    	return "write";
    }
    
    /*
     ** 게시글 작성 
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
    	
    	//사용자 아이디 체크 없으면 신규 등록
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
    	
    	List<WritingDtlDto> writingDtlDtoList = writingDtlService.getWritingDtlList();
    	model.addAttribute("writingDtlDtoList", writingDtlDtoList);
    	model.addAttribute("toastOn", "Y");
    	
    	return "home";
    }
    
    /*
     ** 상세페이지 조회
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model) throws Exception{
    	
    	HttpSession session = request.getSession();
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	paramWritingVoteDto.setWriting_no(writing_no);
    	paramWritingVoteDto.setUser_id(session.toString());
    	
    	//투표 참여 여부 체크, 이미 참여했을 경우 결과페이지로 이동
    	if(writingVoteService.chekVote(paramWritingVoteDto).equals("Y")) {
    		WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
        	WritingVoteDto writingVoteDto = writingVoteService.getWritingVoteDto(paramWritingVoteDto);
        	List<CommentDto> commentDtoList = commentService.getCommentDtoList(writing_no);
        	
        	model.addAttribute("writingDtlDto", writingDtlDto);
        	model.addAttribute("writingVoteDto", writingVoteDto);
        	model.addAttribute("commentDtoList", commentDtoList);
        	
            return "result";
    	} 
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	
        return "detail";
    }
    
    /*
     ** 결과페이지 조회
     */
    @Transactional
    @RequestMapping(value = "/result", method = RequestMethod.POST)
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
    	paramWritingVoteDto.setUser_id(session.toString());
    	paramWritingVoteDto.setRegpe_id(session.toString());
    	paramWritingVoteDto.setModpe_id(session.toString());
    	
    	//투표 내용 삽입
    	writingVoteService.insertWritingVoteDto(paramWritingVoteDto);
    	//투표수 업데이트
    	writingDtlService.updateVoteNo(writing_no, paramWritingVoteDto.getFir_content_vote(), paramWritingVoteDto.getSec_content_vote());
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
    	WritingVoteDto writingVoteDto = writingVoteService.getWritingVoteDto(paramWritingVoteDto);
    	List<CommentDto> commentDtoList = commentService.getCommentDtoList(writing_no);
    	
    	model.addAttribute("writingDtlDto", writingDtlDto);
    	model.addAttribute("writingVoteDto", writingVoteDto);
    	model.addAttribute("commentDtoList", commentDtoList);
    	
        return "result";
    }
    
    /*
     ** 댓글 작성 
     */
    @Transactional
    @RequestMapping(value = "writeComment", method = RequestMethod.POST)
    public String writeComment(HttpServletRequest request, Model model) throws Exception{
    	
    	HttpSession session    = request.getSession();
    	    	
    	//사용자 아이디 체크 없으면 신규 등록
    	UserDto userDto = new UserDto();
    	if(userService.chekUserId(session.toString()) == 0) {
        	userDto.setUser_id(session.toString());
        	userDto.setRegpe_id(session.toString());
        	userDto.setNickname(userService.getNickname());
        	userDto.setReg_div_cd("20");
        	userService.insertUser(userDto);
        	userService.updateNickname(userDto.getNickname());
    	} 
    	
    	//댓글 내용 삽입
    	int writing_no = Integer.parseInt(request.getParameter("writing_no"));
    	String comment_content = request.getParameter("comment_content");
    	int like = 0;
    	CommentDto commentDto = new CommentDto();
    	commentDto.setWriting_no(writing_no);
    	commentDto.setComment_content(comment_content);
    	commentDto.setRecom_no(like);
    	commentDto.setRegpe_id(session.toString());
    	commentService.insertComment(commentDto);   	
    	
    	//result view 랜더링
    	WritingVoteDto paramWritingVoteDto = new WritingVoteDto();
    	paramWritingVoteDto.setWriting_no(writing_no);
    	paramWritingVoteDto.setUser_id(session.toString());
    	
    	WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(writing_no);
        WritingVoteDto writingVoteDto = writingVoteService.getWritingVoteDto(paramWritingVoteDto);
        List<CommentDto> commentDtoList = commentService.getCommentDtoList(writing_no);
        
        model.addAttribute("writingDtlDto", writingDtlDto);
        model.addAttribute("writingVoteDto", writingVoteDto);
        model.addAttribute("commentDtoList", commentDtoList);
    	
    	return "result";
    }
 
    /*
     ** 회원가입 화면 조회 
     */
    @RequestMapping(value = "/enrollForm", method = RequestMethod.GET)
    public String enrollForm(Model model) throws Exception{	
        return "";
    }
    
    /*
     ** 회원가입
     */
    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    public String enroll(HttpServletRequest request, Model model) throws Exception{
    	SHA256 sha256 = new SHA256();
    	
    	String user_id = request.getParameter("user_id");
    	String nickname = userService.getNickname();
    	String pwd = sha256.getSHA256(request.getParameter("pwd"));
    	String reg_div_cd = "10";
    	
    	UserDto userDto = new UserDto();
    	userDto.setUser_id(user_id);
    	userDto.setNickname(nickname);
    	userDto.setPwd(pwd);
    	userDto.setReg_div_cd(reg_div_cd);
    	userService.insertUser(userDto);
    	userService.updateNickname(userDto.getNickname());
    	
        return "login";
    }
    
    /*
     ** 로그인 처리
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) throws Exception{
    	SHA256 sha256 = new SHA256();
    	String user_id = request.getParameter("user_id");
    	String pwd = sha256.getSHA256(request.getParameter("pwd"));
    	
    	UserDto userDto = new UserDto();
    	userDto.setUser_id(user_id);
    	userDto.setPwd(pwd);
    	
    	//유저아이디가 없을 경우
    	if(userService.chekOurUser(userDto) == 0) {
    		
    	} else {
    		
    	}
    	
        return "";
    }
    
}
