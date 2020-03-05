package rebotstudio.hhsturim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rolename")
    private String rolename;

    @Column(name = "description")
    private String description;



}
