package rebotstudio.hhsturim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rebotstudio.hhsturim.service.PlaceService;
import rebotstudio.hhsturim.vo.PlaceVo;

import java.util.List;

@Controller()
@RequestMapping("download")
public class DownloadController {

    @Autowired
    private PlaceService placeService;

    @GetMapping("/downloadPlace")
    @ResponseBody
    public List<PlaceVo> downloadPlace(){
        List<PlaceVo> all = placeService.getAll();

        return all;
    }



}
