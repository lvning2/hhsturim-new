package rebotstudio.hhsturim.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${hhsturim.uploadfilepath}")
    String UPLOADFILEPATH;


    @GetMapping("/test")
    private Object test(){
        System.out.println(UPLOADFILEPATH);
        return UPLOADFILEPATH;
    }




}
