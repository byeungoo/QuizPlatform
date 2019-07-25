package common.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN = "login";

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    // �α��� ó�� �� session ���� ����
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        // session ��
        HttpSession session = request.getSession();
        // model�� ����� ���� userVO�� ����
        ModelMap modelMap = modelAndView.getModelMap();
        Object userVO = modelMap.get("userVO");
        if (userVO != null) {
            logger.info("new login success");
            // session�� �α����� ����� ������ ����
            session.setAttribute(LOGIN, userVO);
            // �α��� �������� �����ϸ�
            if (request.getParameter("useCookie") != null) {
                logger.info("remember me....");
                // �α��� ��Ű ��ü ����
                Cookie loginCookie = new Cookie("loginCookie", session.getId());
                // ��� ��ο��� ���� �����ϰ� ó��
                loginCookie.setPath("/");
                // ��Ű ��ȿ �Ⱓ
                loginCookie.setMaxAge(60 * 60 * 24 * 7);
                // ��Ű ����
                response.addCookie(loginCookie);
            }
            //response.sendRedirect("/");
            // �α��� ������ ���� ���� ������
            Object destination = session.getAttribute("destination");
            // ���� �����ڷ� ������������ �������� ������ ������������ �̵�
            response.sendRedirect(destination != null ? (String) destination : "/");
        }
    }

    // ������ session ���� �ʱ�ȭ
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // session ��
        HttpSession session = request.getSession();
        // ���� session login ���� �����ϸ�
        if (session.getAttribute(LOGIN) != null) {
            logger.info("clear login data before");
            // ����
            session.removeAttribute(LOGIN);
        }
        return true;
    }
}
