package rebotstudio.hhsturim.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import rebotstudio.hhsturim.entity.Syslog;

public interface SyslogRepository extends JpaRepository<Syslog,Integer> {


}
