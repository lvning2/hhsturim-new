package rebotstudio.hhsturim.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Page<User> getAllUser(PageRequest pageRequest){          //获取所有用户，分页
        return userRepository.findAll(pageRequest);
        //return all.getContent();
    }

    public User getUserById(Integer id){        //根据用户id获取用户信息
        return userRepository.getOne(id);
    }

    public void deleteUserById(Integer id){           //根据用户id删除一个用户
        User one = userRepository.getOne(id);
        userRepository.delete(one);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void updateUserById(Integer id,String name,String Phone,Integer age,String idCard,String address){
        User user = userRepository.getOne(id);
        user.setName(name);
        user.setPhone(Phone);
        user.setAge(age);
        user.setIdCard(idCard);
        user.setAddress(address);
        userRepository.save(user);
    }

    public void setUserEnable(Integer uid,Integer enable){
        User user = userRepository.getOne(uid);
        if (user.getUsername().equals("admin")){
            return;
        }
        if (enable == 1) {
            user.setEnable(true);
        } else {
            user.setEnable(false);
        }
        userRepository.save(user);
    }

}
