package rebotstudio.hhsturim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.service.LoginService;
import rebotstudio.hhsturim.vo.ResultVo;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public ResultVo login(@RequestParam String username, @RequestParam String password){

        ResultVo resultVo=new ResultVo();
        try {
            User login = loginService.login(username, password);
            if (login!=null){
                resultVo.setCode(0);
                resultVo.setData(login);
                resultVo.setMsg("登录成功");
                return resultVo;

            }else {
                resultVo.setCode(1);
                resultVo.setMsg("登录失败");
                return resultVo;
            }

        }catch (Exception e){
            resultVo.setCode(1);
            resultVo.setMsg(e.getMessage());
            resultVo.setMsg("登录成功");
            return resultVo;

        }

    }



}
