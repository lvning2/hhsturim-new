package rebotstudio.hhsturim.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rebotstudio.hhsturim.entity.Syslog;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.mapper.UserMapper;
import rebotstudio.hhsturim.service.LoginService;
import rebotstudio.hhsturim.service.SyslogService;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.UserVo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@CrossOrigin(maxAge = 3600)
public class LoginController {

    private final LoginService loginService;

    private final SyslogService syslogService;

    private Logger logger= LoggerFactory.getLogger(LoginController.class);

    public LoginController(LoginService loginService,SyslogService syslogService) {
         this.loginService = loginService;
         this.syslogService = syslogService;
    }



    @PostMapping("/login")
    @ResponseBody
    public ResultVo login(String username, String password, HttpServletRequest request, HttpServletResponse response){
//1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //3.执行登录方法
        try {
            subject.login(token);
            User user = loginService.login(username,password,request.getRemoteAddr());



            UserVo userVo = UserMapper.toVo(user);

            userVo.setSessionId(request.getSession().getId());


            subject.getSession().setAttribute("user",userVo);

//            ConcurrentHashMap<Integer, UserVo> map =(ConcurrentHashMap<Integer, UserVo>) request.getSession().getServletContext().getAttribute("map");
////
////            if(!map.containsKey(userVo.getId())){
////                map.put(userVo.getId(),userVo);
////            }

            ConcurrentHashMap<String, HttpSession> sessions =( ConcurrentHashMap<String, HttpSession>) request.getSession().getServletContext().getAttribute("sessions");
            if(!sessions.containsKey(request.getSession().getId())){
                sessions.put(request.getSession().getId(),request.getSession());
            }


            // 记录日志
            logger.info(request.getRemoteAddr()+" "+user.getUsername()+"("+user.getName()+"),login success.");
            //登录成功
            //跳转到main.html

            Syslog syslog=new Syslog(username,"登录成功",request.getRemoteAddr(),new Date());
            syslogService.saveSyslog(syslog);
            Cookie cookie1=new Cookie("userid",userVo.getId()+"");
            Cookie cookie2=new Cookie("username",user.getUsername());
            Cookie cookie3=new Cookie("rid",userVo.getRid()+"");

            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
            return new ResultVo(0,"登录成功",userVo);
        } catch (UnknownAccountException e) {
            //登录失败:用户名不存在
            logger.info(username+",用户名不存在");
            Syslog syslog=new Syslog(username,"用户名不存在或账户不可用",request.getRemoteAddr(),new Date());
            return new ResultVo(1,"用户名不存在或账户不可用",null);
        } catch (IncorrectCredentialsException e) {
            //登录失败:密码错误
            logger.info(username+",密码错误");
            Syslog syslog=new Syslog(username,"密码错误",request.getRemoteAddr(),new Date());
            syslogService.saveSyslog(syslog);
            return new ResultVo(1,"密码错误",null);
        }
    }

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(){
        return "/main.html";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultVo register(@RequestBody User user){

        user.setMark(0);
        user.setRid(1);
        user.setState(0);
        user.setEnable(true);
        System.out.println(user);
        loginService.register(user);
        return new ResultVo(0,"注册成功",null);
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.GET,RequestMethod.POST})
    //@ResponseBody
    public String logout(HttpServletRequest request,HttpServletResponse response){

//        UserVo user =(UserVo) request.getSession().getAttribute("user");
//
//
//        ConcurrentHashMap<Integer, UserVo> map =(ConcurrentHashMap<Integer, UserVo>) request.getSession().getServletContext().getAttribute("map");
//        //request.getSession().invalidate();
//
//
//        if(user!=null){
//            if(map.containsKey(user.getId())){
//                map.remove(user.getId());
//            }
//        }
        Subject subject = SecurityUtils.getSubject();
        //subject.logout();
        subject.getSession().removeAttribute("user");
        ConcurrentHashMap<String, HttpSession> sessions =( ConcurrentHashMap<String, HttpSession>) request.getSession().getServletContext().getAttribute("sessions");
        if(!sessions.containsKey(request.getSession().getId())){
            sessions.remove(request.getSession().getId());
        }

        request.getSession().removeAttribute("user");

        Cookie cookie1=new Cookie("userid",null);
        Cookie cookie2=new Cookie("username",null);
        Cookie cookie3=new Cookie("rid",null);

        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);


//        return new ResultVo(0,"退出成功",null);
        return "redirect:/main.html";
    }


}




