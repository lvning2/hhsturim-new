package rebotstudio.hhsturim.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.vo.UserVo;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class OnlineCount implements HttpSessionListener {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public int count=0;//记录session的数量
    //监听session的创建,synchronized 防并发bug
    public synchronized void sessionCreated(HttpSessionEvent arg0) {

        ConcurrentHashMap<String, HttpSession> sessions =( ConcurrentHashMap<String, HttpSession>) arg0.getSession().getServletContext().getAttribute("sessions");
        sessions.put(arg0.getSession().getId(),arg0.getSession());
        count++;
        arg0.getSession().getServletContext().setAttribute("count", count);
        log.info("count++  增加");
    }
    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent arg0) {//监听session的撤销
        System.out.println("session死亡");

        ConcurrentHashMap<String, HttpSession> sessions =( ConcurrentHashMap<String, HttpSession>) arg0.getSession().getServletContext().getAttribute("sessions");
        sessions.remove(arg0.getSession().getId());

//        UserVo user =(UserVo) arg0.getSession().getAttribute("user");
//
//        ConcurrentHashMap<Integer, UserVo> map =(ConcurrentHashMap<Integer, UserVo>) arg0.getSession().getServletContext().getAttribute("map");
//        if(user!=null){
//            if(map.containsKey(user.getId())){
//                map.remove(user.getId());
//            }
//        }


        count--;
        arg0.getSession().getServletContext().setAttribute("count", count);
        log.info("count++  减少");
    }

}


