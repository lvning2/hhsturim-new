package rebotstudio.hhsturim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

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


}
