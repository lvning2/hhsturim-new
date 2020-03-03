package rebotstudio.hhsturim.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
public class UserVo {

    private Integer id;

    private String username;

    private String name;

    private String phone;

    private Integer age;

    private Integer state;

    private String address;

    private Integer mark;

    private String idCard;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastLoginTime;

    private String lastLoginIp;

    private String sessionId;

}
