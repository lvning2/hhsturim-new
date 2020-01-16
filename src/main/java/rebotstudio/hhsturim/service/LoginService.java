package rebotstudio.hhsturim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User login(String username,String password){
        User user = userRepository.findByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        if(user!=null){
            boolean flog = bCryptPasswordEncoder.matches(password, user.getPassword());
            if(flog){
                user.setPassword("");
                return user;
            }else {
                return null;
            }

        }
        return null;
    }

}
