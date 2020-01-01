package rebotstudio.hhsturim.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="partA")
    private Integer partya;

    @Column(name="partB")
    private Integer partyb;

    @Column(name="contractStartTime")
    private Date contractStartTime;

    @Column(name = "contractEndTime")
    private Date contractEndTime;

    @Column(name = "state")
    private Integer state;

    @Column(name = "contractContent")
    private String contractContent;


}
