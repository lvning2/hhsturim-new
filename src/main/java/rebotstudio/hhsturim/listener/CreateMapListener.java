package rebotstudio.hhsturim.listener;

import rebotstudio.hhsturim.vo.UserVo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class CreateMapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("appplication创建");
        ConcurrentHashMap<Integer, UserVo> map = new ConcurrentHashMap<>();
        sce.getServletContext().setAttribute("map",map);
        ConcurrentHashMap<String, HttpSession> sessions = new ConcurrentHashMap<>();
        sce.getServletContext().setAttribute("sessions",sessions);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
