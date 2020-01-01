package rebotstudio.hhsturim.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResultVo {

    private Integer code;

    private String msg;

    private Integer count;

    private Object data;


}
