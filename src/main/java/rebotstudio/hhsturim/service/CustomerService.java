package rebotstudio.hhsturim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rebotstudio.hhsturim.entity.Customer;
import rebotstudio.hhsturim.entity.User;
import rebotstudio.hhsturim.repository.CustomerRepository;
import rebotstudio.hhsturim.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public List getAllCustomerById(Integer uid){            //查询用户的所有客户信息

        List list = new  ArrayList();
        List<Customer> customers = customerRepository.findByUid(uid);
        for(Customer x:customers){
            Integer customerId = x.getCustomerId();
            User one = userRepository.getOne(x.getCustomerId());
            one.setPassword("");
            list.add(one);
        }
        return list;
    }

    public void addCustomer(Integer userId, Integer customerId) {        //为用户添加一个客户
        Customer customer=new Customer();
        customer.setUid(userId);
        customer.setCustomerId(customerId);
        customerRepository.save(customer);
    }

    public void delete(Integer userId, Integer customerId) {        //为用户删除一个客户
        Customer byUidAndAndCustomerId = customerRepository.findByUidAndAndCustomerId(userId, customerId);
        customerRepository.delete(byUidAndAndCustomerId);
    }


}
