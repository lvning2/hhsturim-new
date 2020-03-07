package rebotstudio.hhsturim.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import rebotstudio.hhsturim.entity.Place;
import rebotstudio.hhsturim.service.PlaceService;
import rebotstudio.hhsturim.vo.PlaceVo;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;
import rebotstudio.hhsturim.vo.UserVo;


import java.util.List;

@RestController
@RequestMapping("/place")
@CrossOrigin(maxAge = 3600)
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/getAllPlace")
    @ApiOperation("获取所有地点信息")
    public ResultVo getAllPlace(){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,placeService.getAll());
    }

    @GetMapping("/getPlaceById/{id}")
    @ApiOperation("根据id获取地点信息")
    public ResultVo getPlaceById(@PathVariable Integer id){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,placeService.getOne(id));
    }
    @GetMapping("/getPlaceById2")
    @ApiOperation("根据id获取地点信息--控制台页面使用")
    public ResultVo getPlaceById2(Integer id){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,placeService.getById(id));
    }


    @GetMapping("/getPlaceTop")
    @ApiOperation("获取top地点信息")
    public ResultVo getPlaceTop(){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,placeService.getTop());
    }

    @GetMapping("/getPlaceBorder")
    @ApiOperation("获取top地点信息")
    public ResultVo getPlaceBorder(){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,placeService.getBorder());
    }

    @GetMapping("/getPlaceByType")
    @ApiOperation("根据类型获取地点信息")
    public ResultVo getPlaceByType(@ApiParam("类型") @RequestParam Integer type){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,placeService.getPlaceByType(type));
    }

    @GetMapping("/getPlaceByPrice")
    @ApiOperation("根据类型获取地点信息")
    public ResultVo getPlaceByPrice(@ApiParam("价格") @RequestParam Float start,@ApiParam("价格") @RequestParam Float end){
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,placeService.getPlaceByPrice(start,end));
    }

    @PostMapping("/save")
    @ApiOperation("保存一个地点信息")
    public ResultVo savePlace(@RequestBody Place place){
        placeService.savePlace(place);
        return new ResultVo(StatusCode.SAVE_SUCCESS.code,StatusCode.SAVE_SUCCESS.dsc,null);
    }

    @GetMapping("/getPlaceByUserId")
    @ApiOperation("根据用户id获取发布的信息")
    public ResultVo getPlaceByUserId(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UserVo user =(UserVo) session.getAttribute("user");
        List<PlaceVo> list = placeService.getPlaceByUserId(user.getId());
        return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,list.size(),list);
    }

    @PostMapping("/deletePlaceById")
    public ResultVo deletePlaceById(Integer id){
        placeService.deletePlaceById(id);
        return new ResultVo(StatusCode.DELETE_SUCCESS.code,StatusCode.DELETE_SUCCESS.dsc,null);
    }

    @PostMapping("/updatePlaceById")
    public ResultVo updatePlaceById(Integer id,String title,String phone,String details,Integer type,Float price){
        placeService.updatePlaceById(id,title,phone,details,type,price);
        return new ResultVo(StatusCode.UPDATE_SUCCESS.code,StatusCode.UPDATE_SUCCESS.dsc,null);
    }

}


