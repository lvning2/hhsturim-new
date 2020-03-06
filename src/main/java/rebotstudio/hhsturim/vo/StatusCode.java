package rebotstudio.hhsturim.vo;

public enum StatusCode {

    LOAD_SUCCESS(0,"查询成功"),
    SUCCESS(0,"成功"),
    FAILED(-1,"失败"),
    LOAD_FAILED(-1,"查询失败"),
    UPDATE_SUCCESS(0,"更新成功"),
    UPDATE_FAILED(1,"更新失败"),
    SAVE_SUCCESS(0,"保存成功"),
    SAVE_FAILED(-1,"保存失败"),
    DELETE_SUCCESS(0,"删除成功"),
    DELETE_FAILED(-1,"删除失败"),
    SYSTEM_ERROR(100, "系统错误");

    StatusCode(int code,String dsc){
        this.code=code;
        this.dsc=dsc;
    }

    public int code;
    public String dsc;

}
