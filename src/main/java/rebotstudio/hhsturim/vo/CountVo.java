package rebotstudio.hhsturim.vo;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CountVo {          // 发布信息统计

    private Integer typeId;

    private String name;

    private Integer value;

}
