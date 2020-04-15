package rebotstudio.hhsturim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "phone")
    private String phone;

    @Column(name = "details")
    private String details;

    @Column(name = "img")
    private String img;

    @Column(name = "price")
    private Float price;

    @Column(name = "type")
    private Integer type;

    @Column(name = "state")
    private Integer state;

    @Column(name = "createTime")
    private Date createTime;

}



