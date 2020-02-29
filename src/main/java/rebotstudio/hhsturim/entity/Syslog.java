package rebotstudio.hhsturim.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class Syslog {

    public Syslog(){

    }

    public Syslog(String username, String operation, String clientIp, Date createTime){
        this.username = username;
        this.operation = operation;
        this.clientIp = clientIp;
        this.createTime = createTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;        //登录名

    @Column(name = "operation")
    private String operation;       //操作

    @Column(name = "clientIp")
    private String clientIp;        //客户端ip

    @Column(name = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;        //操作时间


}
