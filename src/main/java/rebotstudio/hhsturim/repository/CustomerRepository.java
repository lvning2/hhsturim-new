package rebotstudio.hhsturim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rebotstudio.hhsturim.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


}
