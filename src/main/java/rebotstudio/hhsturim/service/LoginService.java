package rebotstudio.hhsturim.service;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.entity.Syslog;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.repository.SyslogRepository;
import rebotstudio.hhsturim.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private SyslogRepository syslogRepository;

    @Transactional
    public User login(String username,String password,String ip){
        User user = userRepository.findByUsername(username);

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        if(user!=null){
            boolean flog = bCryptPasswordEncoder.matches(password, user.getPassword());
            if(flog){
                user.setLastLoginIp(ip);
                user.setLastLoginTime(new Date());
                userRepository.save(user);
                return user;
            }else {
                return null;
            }

        }
        return null;
    }

    @Transactional
    public void register(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private Session getSessionBysessionId(Serializable sessionId){
        Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(sessionId));
        return kickoutSession;
    }


}
