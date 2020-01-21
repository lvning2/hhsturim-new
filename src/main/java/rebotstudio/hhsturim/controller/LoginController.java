package rebotstudio.hhsturim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ResultVo login(@RequestBody User user, HttpServletRequest request){

        try {
            User login = loginService.login(user.getUsername(), user.getPassword());
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

    @RequestMapping("/")
    public String index(){
        return "main.html";
    }


}
