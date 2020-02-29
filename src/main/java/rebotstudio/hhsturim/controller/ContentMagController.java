package rebotstudio.hhsturim.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rebotstudio.hhsturim.entity.Top;
import rebotstudio.hhsturim.service.PlaceService;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;

import java.lang.annotation.Repeatable;

@RestController
@RequestMapping("/content")
@Api(tags = "内容管理接口")
public class ContentMagController {

    private final PlaceService placeService;

    ContentMagController(PlaceService placeService){
        this.placeService = placeService;
    }

    @PostMapping("/deleteTopById")
    @ApiOperation("删除一个top")
    public ResultVo deleteTopById(@RequestParam Integer id){
        placeService.deleteTopById(id);
        return new ResultVo(StatusCode.DELETE_SUCCESS.code,StatusCode.DELETE_SUCCESS.dsc,null);
    }

    @PostMapping("/addTopById")
    @ApiOperation("添加一个top")
    public ResultVo addTopById(@RequestParam Integer id){
        Top top=new Top();
        top.setPid(id);
        placeService.addTop(top);
        return new ResultVo(StatusCode.SAVE_SUCCESS.code,StatusCode.SAVE_SUCCESS.dsc,null);
    }


}
