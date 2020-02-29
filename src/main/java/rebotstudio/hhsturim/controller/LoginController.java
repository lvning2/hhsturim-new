package rebotstudio.hhsturim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rebotstudio.hhsturim.entity.Syslog;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.service.LoginService;
import rebotstudio.hhsturim.service.SyslogService;
import rebotstudio.hhsturim.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@CrossOrigin(maxAge = 3600)
public class LoginController {

    private final LoginService loginService;

    private final SyslogService syslogService;

    public LoginController(LoginService loginService,SyslogService syslogService) {
         this.loginService = loginService;
         this.syslogService = syslogService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultVo login(@RequestBody User user, HttpServletRequest request){

        try {
            User login = loginService.login(user.getUsername(), user.getPassword());
            if (login!=null){
                request.getSession().setAttribute("user",login);
                syslogService.saveSyslog(new Syslog(user.getUsername(),"登录成功",request.getRemoteAddr(),new Date()));
                return new ResultVo(0,"登录成功",login);
            }else {
                syslogService.saveSyslog(new Syslog(user.getUsername(),"登录失败",request.getRemoteAddr(),new Date()));
                return new ResultVo(1,"登录失败",null);
            }
        }catch (Exception e){
            syslogService.saveSyslog(new Syslog(user.getUsername(),"登录失败",request.getRemoteAddr(),new Date()));
            return new ResultVo(1,"登录失败",e.getMessage());
        }
    }

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(){
        return "main.html";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultVo register(@RequestBody User user){
        loginService.register(user);
        return new ResultVo(0,"注册成功",null);
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.GET,RequestMethod.POST})
    public ResultVo logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return new ResultVo(0,"退出成功",null);
    }


}

