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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.web.dto.CommentDto;
import com.quiz.web.dto.CommentPrefer;
import com.quiz.web.dto.ParamDto;
import com.quiz.web.dto.UserDto;
import com.quiz.web.dto.WritingDtlDto;
import com.quiz.web.dto.WritingVoteDto;
import com.quiz.web.service.CommentService;
import com.quiz.web.service.UserService;
import com.quiz.web.service.WritingDtlService;
import com.quiz.web.service.WritingVoteService;

import common.paging.dto.PagingDto;
import common.paging.dto.WritingDtlPagingDto;



/**
 * 게시글 상세 페이지 컨트롤러
 * @ author : GOOHOON
 * @ version 1.0
 */
@Controller
public class DetailController {
	
    private static final Logger logger = LoggerFactory.getLogger(DetailController.class);
	
    @Autowired
    private WritingDtlService writingDtlService;
    
    @Autowired
    private WritingVoteService writingVoteService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentService;
    
    /*
     ** 상세페이지 이동, 클릭한 글과 인기 컨텐츠 데이터 전송
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model) throws Exception{
    	
    	model.addAttribute("writing_no", request.getParameter("writing_no"));

        return "detail";
    }
    
    /*
     ** 상세 페이지 게시글 조회 비동기 처리 
     */
    @CrossOrigin
    @RequestMapping(value = "getWritingDtlDto", method = RequestMethod.GET)
    public @ResponseBody WritingDtlDto getWritingDtlDto(HttpSession session, HttpServletRequest request
    		                  , @RequestParam(value="writingNo") int writing_no) throws Exception{
    	
    	session    = request.getSession();
    	
    	//유저정보 획득
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	
    	ParamDto paramDto = new ParamDto();
    	paramDto.setWriting_no(writing_no);
    	paramDto.setDepth(0);  //댓글조회를 위해 0으로 세팅
    	paramDto.setUser_id(userDto.getUser_id());
    	
        //최종 결과 담을 객체 생성 및 인기컨텐츠 정보로 초기화
	    WritingDtlDto writingDtlDto = writingDtlService.getWritingDtl(paramDto);
	    List<CommentDto> commentDtoList = commentService.getCommentDtoList(paramDto, userDto);  
	    
	    if(writingDtlDto.getRegpe_id().equals(userDto.getUser_id())) {
	    	writingDtlDto.setMine(true);
	    }
	    
	    writingDtlDto.setDetailCommentList(commentDtoList);
    
    	return writingDtlDto;
    }    
    
    /*
     ** 상세 페이지 비동기 처리 페이징 조회
     */
    @CrossOrigin
    @RequestMapping(value = "getDetailDtoList", method = RequestMethod.GET)
    public @ResponseBody List<WritingDtlDto> getDetailDtoList(HttpSession session, HttpServletRequest request
    		                  , @RequestParam(value="page") int page, @RequestParam(value="writing_no") int writing_no) throws Exception{
    	
    	session    = request.getSession();
    	  
    	//유저 정보 조회
    	UserDto user = userService.getUesrSettingDto(session, request);
        
        //페이징 정보 세팅
    	PagingDto pagingDto = new WritingDtlPagingDto();
    	pagingDto.setUser_id(user.getUser_id());
    	pagingDto.setPage_num(page);
    	pagingDto.setPage_size(5);
        
    	WritingDtlPagingDto writingDtlPagingDto = (WritingDtlPagingDto) pagingDto;
    	writingDtlPagingDto.setWriting_no(writing_no);
    	
        //최종 결과 담을 객체 생성 및 인기컨텐츠 정보로 초기화
	    List<WritingDtlDto> detailWritingList = writingDtlService.getPopulWritingDtoList(writingDtlPagingDto);
     
    	ParamDto paramDto = new ParamDto();
    	
	    //해당 글에 대한 댓글 정보 조회 및 추가
	    for(WritingDtlDto detailDto :detailWritingList) {
	    	paramDto.setDepth(0);  //댓글조회를 위해 0으로 세팅
	    	paramDto.setWriting_no(detailDto.getWriting_no());
	    	detailDto.setDetailCommentList(commentService.getCommentDtoList(paramDto, user));  //댓글 세팅
	    	
	    	//내가 쓴 글일경우 isMins True
		    if(detailDto.getRegpe_id().equals(user.getUser_id())) {
		    	detailDto.setMine(true);
		    }
	    	
		    paramDto.setDepth(1); //대댓글 조회를 위해 1로세팅
		    List<CommentDto> commentListDto = detailDto.getDetailCommentList();
		    
		    //대댓글 값 세팅
		    for(CommentDto tempCommentDto : commentListDto) {
		    	paramDto.setParent(tempCommentDto.getComment_no());  //대댓글 상위 댓글 번호 세팅
		    	tempCommentDto.setLowCommentDtoList(commentService.getLowCommentDtoList(paramDto, user));
		    	tempCommentDto.setLow_comment_num(tempCommentDto.getLowCommentDtoList().size()); //대댓글 개수 세팅
		    }
	    }	

    	return detailWritingList;
    }
    
    /*
     ** 상세페이지 투표 비동기 처리
     */
    @CrossOrigin
    @RequestMapping(value = "vote", method = RequestMethod.POST)
    public @ResponseBody WritingVoteDto vote(HttpSession session, HttpServletRequest request, @RequestParam(value="voteNum") int voteNum , @RequestParam(value="writingNo") int writingNo) throws Exception{

    	session = request.getSession();
    	WritingVoteDto writingVoteDto = new WritingVoteDto();
    	
    	try {
	    	UserDto userDto = userService.getUesrSettingDto(session, request);
	    
	    	ParamDto paramDto = new ParamDto();
	    	paramDto.setWriting_no(writingNo);
	    	paramDto.setVote(voteNum);
	    	paramDto.setUser_id(userDto.getUser_id());
	    	    	
	    	//작성글 투표수 증가 
	    	writingDtlService.updateVote(paramDto); 	
	    	
	    	//게시글 투표 정보 추가
	    	writingVoteService.insertWritingVoteDto(paramDto);
	    	
	    	//투표 정보 조회 및 반환
	    	writingVoteDto = writingVoteService.getWritingVoteDto(paramDto);
    	} catch(Exception e) {
    		System.err.println(e.getMessage());
    	}
    	return writingVoteDto;
    } 
    
    /*
     ** 댓글 작성 비동기 처리
     */
    @CrossOrigin
    @RequestMapping(value = "writeComment", method = RequestMethod.POST)
    public @ResponseBody CommentDto writeComment(HttpSession session, HttpServletRequest request, @RequestParam(value="replytx") String replytx, @RequestParam(value="writingNo") int writing_no
    		                                     , @RequestParam(value="depth") int depth, @RequestParam(value="parent") Integer parent) throws Exception{

    	session    = request.getSession();
    	    	
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	
    	//댓글 저장 후 반환
    	String comment_content = replytx;
    	int recom_num = 0;
    	CommentDto commentDto = new CommentDto();
    	commentDto.setWriting_no(writing_no);
    	commentDto.setComment_content(comment_content);
    	commentDto.setRecom_num(recom_num);
    	commentDto.setRegpe_id(userDto.getUser_id());
    	commentDto.setNickname(userDto.getNickname());
    	commentDto.setDepth(depth);
    	commentDto.setParent(parent);
    	commentService.insertComment(commentDto);  //INSERT 후 COMMENT_NO 자동세팅
    
    	//댓글, 대댓글 조회 파라미터 설정
    	ParamDto paramDto = new ParamDto();
    	paramDto.setWriting_no(writing_no);
    	paramDto.setComment_no(commentDto.getComment_no());
    	
    	commentDto = commentService.getCommentDto(paramDto);
    	
    	return commentDto;
    }    
    
    /*
     ** 댓글 좋아요, 싫어요 업데이트
     */
    @CrossOrigin
    @RequestMapping(value = "commentPreferUpdate", method = RequestMethod.POST)
    public @ResponseBody CommentPrefer commentPrefer(HttpSession session, HttpServletRequest request, @RequestParam(value="comment_no") int comment_no, @RequestParam(value="prefer") String prefer
    		                                     , @RequestParam(value="writing_no") int writing_no) throws Exception{

    	session    = request.getSession();
    	
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	String  userId  = userDto.getUser_id();
    	CommentPrefer commentPrefer = new CommentPrefer();
    	commentPrefer.setWriting_no(writing_no);
    	commentPrefer.setComment_no(comment_no);
    	commentPrefer.setPrefer(prefer);
    	commentPrefer.setUser_id(userId);
    	
    	try {
	    	commentPrefer = commentService.commentPreferUpdate(commentPrefer);
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	return commentPrefer;
    }
    
    /*
     ** 신고하기 업데이트
     */
    @CrossOrigin
    @RequestMapping(value = "reportWriting", method = RequestMethod.POST)
    public @ResponseBody boolean reportWriting(HttpSession session, HttpServletRequest request, ParamDto paramDto) throws Exception{
    	
    	session = request.getSession(); 
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	paramDto.setUser_id(userDto.getUser_id());
    	
    	boolean report = writingDtlService.reportWriting(session, paramDto); //게시글 신고
    	
    	return report;
    }
    
    /*
     ** 게시글 삭제하기, 사용여부만 'N'으로 설정
     */
    @CrossOrigin
    @RequestMapping(value = "deleteWriting", method = RequestMethod.POST)
    public @ResponseBody boolean deleteWriting(HttpSession session, HttpServletRequest request, WritingDtlDto writingDtlDto) throws Exception{
    	
    	session = request.getSession(); 
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	writingDtlDto.setUser_id(userDto.getUser_id());
    	
    	boolean report = writingDtlService.deleteWriting(session, writingDtlDto);
    	
    	return report;
    }
    
    /*
     ** 댓글 삭제하기, 사용여부 'N' 변경
     */
    @CrossOrigin
    @RequestMapping(value = "deleteComment", method = RequestMethod.POST)
    public @ResponseBody CommentDto deleteComment(HttpSession session, HttpServletRequest request, CommentDto commentDto) throws Exception{
    	
    	session = request.getSession(); 
    	UserDto userDto = userService.getUesrSettingDto(session, request);
    	commentDto.setModpe_id(userDto.getUser_id());
    	commentDto = commentService.deleteComment(session, commentDto);
    	
    	return commentDto;
    }
    
}
