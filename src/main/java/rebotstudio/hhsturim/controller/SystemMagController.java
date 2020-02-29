package rebotstudio.hhsturim.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rebotstudio.hhsturim.service.SyslogService;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;

@RestController
@Api(tags = "系统管理接口")
public class SystemMagController {

    private final SyslogService syslogService;

    SystemMagController(SyslogService syslogService){
        this.syslogService = syslogService;
    }

    @GetMapping("/getAllSyslog")
    @ApiOperation("获取所有系统日志")
    public ResultVo getAllSyslog(){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,syslogService.getAllSyslog());
    }

}
