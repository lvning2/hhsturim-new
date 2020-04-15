package rebotstudio.hhsturim.controller;


import rebotstudio.hhsturim.vo.ResultVo;

import java.security.PublicKey;

public interface BaseController<T> {

    public ResultVo addOne(T entity);


}
