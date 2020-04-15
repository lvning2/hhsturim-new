package rebotstudio.hhsturim.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class Comment {          // 评论

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer uid;

    @Column
    private Integer pid;

    @Column
    private String content;

    @Column
    private Date createTime;

}


