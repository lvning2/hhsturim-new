package rebotstudio.hhsturim.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import rebotstudio.hhsturim.entity.Place;
import rebotstudio.hhsturim.service.PlaceService;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;

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
    public ResultVo savePlace(Place place){

        return new ResultVo();
    }



}
