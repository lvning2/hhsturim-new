package rebotstudio.hhsturim.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import rebotstudio.hhsturim.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //HttpSession session = request.getSession();

        Subject subject = SecurityUtils.getSubject();

        Session session = subject.getSession();

        UserVo user = (UserVo)session.getAttribute("user");
        if(user!=null){
            return true;
        }

        //request.getRequestDispatcher("/login").forward(request,response);
        response.sendRedirect("/login.html");
        System.out.println("拦截...");
        return false;



    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
