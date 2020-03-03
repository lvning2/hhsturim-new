package rebotstudio.hhsturim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "age")
    private Integer age;

    @Column(name = "state")
    private Integer state;

    @Column(name = "address")
    private String address;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "idCard")
    private String idCard;

    @Column(name = "lastLoginTime")
    private Date lastLoginTime;

    @Column(name = "lastLoginIp")
    private String lastLoginIp;

}
