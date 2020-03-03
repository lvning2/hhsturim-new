package rebotstudio.hhsturim.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.Date;


@ToString
@Data
public class PlaceVo {

    private Integer id;     //地点id

    private String title;   //

    private Integer uid;    //

    private String username;

    private String phone;   //

    private String details; //

    private String img; //

    private String type;   //

    private String state;
    private Float price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



}
