package rebotstudio.hhsturim.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.service.LoginService;
import rebotstudio.hhsturim.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(maxAge = 3600)
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultVo login(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        try {
            User login = loginService.login(username, password);
            if (login!=null){
                request.getSession().setAttribute("user",login);
                return new ResultVo(0,"登录成功",login);
            }else {
                return new ResultVo(1,"登录失败",null);
            }
        }catch (Exception e){
            return new ResultVo(1,"登录失败",e.getMessage());
        }
    }




}
