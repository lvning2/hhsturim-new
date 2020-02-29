package rebotstudio.hhsturim.service;

import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.entity.Syslog;
import rebotstudio.hhsturim.repository.SyslogRepository;

import java.util.List;

@Service
public class SyslogService {

    private final SyslogRepository syslogRepository;

    SyslogService(SyslogRepository syslogRepository){
        this.syslogRepository = syslogRepository;
    }

    public List<Syslog> getAllSyslog(){             //获取所有的登录日志
        return syslogRepository.findAll();
    }

    public void saveSyslog(Syslog syslog){          //保存
        syslogRepository.save(syslog);
    }

}
