package rebotstudio.hhsturim.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResultVo {

    private Integer code;

    private String msg;

    private Object data;

    public ResultVo(){}

    public ResultVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
