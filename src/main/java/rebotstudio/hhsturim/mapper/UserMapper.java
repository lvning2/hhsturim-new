package rebotstudio.hhsturim.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.vo.UserVo;

import java.util.ArrayList;
import java.util.List;


public class UserMapper {

    public static UserVo toVo(User user){
        return CGlibMapper.mapper(user, UserVo.class);
    }

    public static List<UserVo> toVoList(List<User> users){
        List<UserVo> list = new ArrayList<>();
        for (User user:users){
            UserVo userVo = toVo(user);
            list.add(userVo);
        }
        return list;
    }


}
