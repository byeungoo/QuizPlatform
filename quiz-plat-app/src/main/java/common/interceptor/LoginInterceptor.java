package common.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.quiz.web.dto.UserDto;
import com.quiz.web.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
    
    @Autowired
    UserService userService;

    // 기존의 session 정보 초기화
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // session 값
        HttpSession session = request.getSession();
        
        Cookie cookie = WebUtils.getCookie(request, "remember");

        if(cookie!=null) {
        	logger.info("쿠키값: " + cookie.getValue());
        	UserDto userDto = userService.checkLoginBefore(cookie.getValue());

        	if(userDto!=null) {
        		session.setAttribute("login", userDto);
        		logger.info("세션값: " + userDto.toString());
        	}
        }
        
        return true;
    }
}
