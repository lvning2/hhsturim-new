package rebotstudio.hhsturim.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResultVo {

    private Integer code;

    private String msg;

    private long count=0;

    private Object data;

    public ResultVo(){}

    public ResultVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(Integer code, String msg,long count ,Object data) {
        this.code = code;
        this.msg = msg;
        this.count=count;
        this.data = data;
    }

}
