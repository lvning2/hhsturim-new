package rebotstudio.hhsturim.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/other")
@CrossOrigin(maxAge = 3600)
@Api
public class OtherController {

    @Value("${hhsturim.uploadfilepath}")
    String UPLOADFILEPATH;

    @PostMapping("/upload")
    @ApiOperation("上传图片")
    public ResultVo imgUpload(@RequestParam("fileName") MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return new ResultVo(StatusCode.SAVE_FAILED.code,StatusCode.SAVE_FAILED.dsc,"请选择文件");
        }
        String oldFilename = multipartFile.getOriginalFilename();
        int i = oldFilename.lastIndexOf(".");
        String newFilename= UUID.randomUUID()+oldFilename.substring(i);

        File file=new File(UPLOADFILEPATH+newFilename);
        multipartFile.transferTo(file);

        return new ResultVo(StatusCode.SAVE_SUCCESS.code,StatusCode.SAVE_SUCCESS.dsc,"imgs/"+newFilename);

    }




}
