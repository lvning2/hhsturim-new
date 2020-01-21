package rebotstudio.hhsturim.vo;

import lombok.Data;
import lombok.ToString;

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
    private Date createTime;

}
