package rebotstudio.hhsturim.vo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CommentVo {

    private Long id;

    private Integer uid;

    private Integer pid;

    private String username;

    private String content;

    private Date createTime;



}
