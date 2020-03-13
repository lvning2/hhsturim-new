package rebotstudio.hhsturim.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rebotstudio.hhsturim.entity.Syslog;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.service.SyslogService;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;
import rebotstudio.hhsturim.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Api(tags = "系统管理接口")
@CrossOrigin(maxAge = 3600)
public class SystemMagController {


    private final SyslogService syslogService;

    SystemMagController(SyslogService syslogService){
        this.syslogService = syslogService;
    }

    @GetMapping("/getAllSyslog")
    @ApiOperation("获取所有系统日志")
    public ResultVo getAllSyslog(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        PageRequest of = PageRequest.of(page-1, size, Sort.by("createTime").descending());
        Page<Syslog> allSyslog = syslogService.getAllSyslog(of);
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,allSyslog.getTotalElements(),allSyslog.getContent());
    }

    @GetMapping("/getOnlineCount")
    @ApiOperation("获取在线的用户个数")
    public ResultVo getUserOnline(HttpServletRequest request){
        int count = (int)request.getSession().getServletContext().getAttribute("count");
        return new ResultVo(0,"查询成功",count);
    }

    @GetMapping("/getUserOnline")
    @ApiOperation("获取在线的用户信息")
    public ResultVo getOnlineUser(HttpServletRequest request){
        System.out.println("-----------");
        ArrayList list =new ArrayList<>();
//        ConcurrentHashMap<Integer, UserVo> map =(ConcurrentHashMap<Integer, UserVo>) request.getSession().getServletContext().getAttribute("map");
//        Iterator<Map.Entry<Integer, UserVo>> iterator = map.entrySet().iterator();


        ConcurrentHashMap<String, HttpSession> sessions =( ConcurrentHashMap<String, HttpSession>) request.getSession().getServletContext().getAttribute("sessions");
        Iterator<Map.Entry<String, HttpSession>> iterator = sessions.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, HttpSession> next = iterator.next();
            if (next.getValue().getAttribute("user")!=null){
                list.add(next.getValue().getAttribute("user"));
            }

        }

        return new ResultVo(0,"获取成功",list);

    }

    @GetMapping("/kickout")
    public ResultVo kickout(String sessionId,HttpServletRequest request){

        ConcurrentHashMap<String, HttpSession> sessions =( ConcurrentHashMap<String, HttpSession>) request.getSession().getServletContext().getAttribute("sessions");

        HttpSession session = sessions.get(sessionId);
        session.removeAttribute("user");



        return new ResultVo(0,"踢出成功",null);
    }




}
