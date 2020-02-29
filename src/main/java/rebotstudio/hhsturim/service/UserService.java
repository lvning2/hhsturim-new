package rebotstudio.hhsturim.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(PageRequest pageRequest){          //获取所有用户，分页
        Page<User> all = userRepository.findAll(pageRequest);
        return all.getContent();
    }

    public User getUserById(Integer id){        //根据用户id获取用户信息
        return userRepository.getOne(id);
    }

    public void deleteUserById(Integer id){           //根据用户id删除一个用户
        User one = userRepository.getOne(id);
        userRepository.delete(one);
    }


}
