package rebotstudio.hhsturim.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rebotstudio.hhsturim.service.CustomerService;
import rebotstudio.hhsturim.vo.ResultVo;

import java.util.List;

@RestController
@Api(tags = "客户相关接口")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAllCustomerByUid")
    @ApiOperation("获取一个用户的所有客户信息")
    public ResultVo getAll(@ApiParam("uid") @RequestParam Integer uid){
        ResultVo resultVo=new ResultVo();

        try {

            List allCustomerById = customerService.getAllCustomerById(uid);
            resultVo.setCode(0);
            resultVo.setCount(allCustomerById.size());
            resultVo.setData(allCustomerById);
            return resultVo;

        }catch (Exception e){
            resultVo.setCode(1);
            resultVo.setMsg(e.getMessage());
            return resultVo;
        }
    }

    @PostMapping("/deleteCustomer")
    @ApiOperation("删除一个用户的一个客户")
    public ResultVo deleteCustomerBy(@ApiParam("uid") @RequestParam Integer uid,@ApiParam("customerId") @RequestParam Integer customerId){
        ResultVo resultVo=new ResultVo();

        try {

         customerService.delete(uid,customerId);
            resultVo.setCode(0);
            resultVo.setMsg("删除成功");
            return resultVo;

        }catch (Exception e){
            resultVo.setCode(1);
            resultVo.setMsg(e.getMessage());
            return resultVo;
        }
    }


}
