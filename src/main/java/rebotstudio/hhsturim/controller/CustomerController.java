package rebotstudio.hhsturim.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import rebotstudio.hhsturim.service.CustomerService;
import rebotstudio.hhsturim.vo.ResultVo;
import rebotstudio.hhsturim.vo.StatusCode;


@RestController
@RequestMapping("/customer")
@Api(tags = "客户相关接口")
@CrossOrigin(maxAge = 3600)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAllCustomerByUid")
    @ApiOperation("获取一个用户的所有客户信息")
    public ResultVo getAll(@ApiParam("uid") @RequestParam Integer uid){
        try {
            return new ResultVo(StatusCode.LOAD_SUCCESS.code,StatusCode.LOAD_SUCCESS.dsc,customerService.getAllCustomerById(uid));
        }catch (Exception e){
            return new ResultVo(StatusCode.LOAD_FAILED.code,StatusCode.LOAD_FAILED.dsc,e.getMessage());
        }

    }

    @PostMapping("/deleteCustomer")
    @ApiOperation("删除一个用户的一个客户")
    public ResultVo deleteCustomerBy(@ApiParam("uid") @RequestParam Integer uid,@ApiParam("customerId") @RequestParam Integer customerId){
        try {
            customerService.delete(uid,customerId);
            return new ResultVo(StatusCode.DELETE_SUCCESS.code,StatusCode.DELETE_SUCCESS.dsc,null);
        }catch (Exception e){
            return new ResultVo(StatusCode.DELETE_FAILED.code,StatusCode.DELETE_FAILED.dsc,e.getMessage());
        }
    }


}
