package rebotstudio.hhsturim.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.mapper.UserMapper;
import rebotstudio.hhsturim.service.UserService;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getAllUser")
    public ResultVo getAllUser(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        PageRequest of = PageRequest.of(page-1, size);
        Page<User> allUser = userService.getAllUser(of);
        List<User> users =allUser.getContent();
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,allUser.getTotalElements(),UserMapper.toVoList(users));
    }

    @GetMapping("/getUserById")
    public ResultVo getUserById(@RequestParam Integer id){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,UserMapper.toVo(userService.getUserById(id)));
    }

    @PostMapping("/deleteUserById")
    public ResultVo deleteById(Integer id){
        userService.deleteUserById(id);
        return new ResultVo(StatusCode.DELETE_SUCCESS.code,StatusCode.DELETE_SUCCESS.dsc,null);
    }


}
