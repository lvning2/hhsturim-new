package rebotstudio.hhsturim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rebotstudio.hhsturim.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByUid(Integer uid);

    Customer findByUidAndAndCustomerId(Integer userid,Integer customerid);

}

